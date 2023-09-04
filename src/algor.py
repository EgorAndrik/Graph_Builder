import matplotlib.pyplot as plt
import numpy as np
import sys
from sympy import *
from math import *


def handler(arifmEx):
    arifmEx = arifmEx.replace(' ', '')
    if '^' in arifmEx:
        arifmEx = arifmEx.replace('^', '**')
    if len(arifmEx[:arifmEx.find('x**')]) == 0:
        arifmEx = "1*" + arifmEx
    return arifmEx


def check(arifmEx):
    varsFunc = ['cos', 'sin', 'abs', 'sqrt', 'tan', 'ctg']
    return any([i in arifmEx for i in varsFunc])


function = sys.argv
function[1] = handler(function[1])
fig, ax = plt.subplots()
ax.set_title('Graph function')
ax.set_xlabel('x')
ax.set_ylabel('y')
plt.grid(True)
if not check(function[1]):
    roots = []

    if '/' in function[1]:
        arifmEx = function[1].split('/')
        x = symbols('x', real=True)
        roots = solve(Eq(eval(arifmEx[0]), 0), x) + solve(Eq(eval(arifmEx[1]), 0), x)

    if '/' not in function[1]:
        x = symbols('x', real=True)
        roots = solve(Eq(eval(function[1]), 0), x)

        if len(roots) == 0 and 'x**2' in function[1] and 'x' in function[1][function[1].find('x**') + 1:]:
            terms = list(map(lambda x: x.replace('**2', '').replace('*', ''), function[1].split('x')))
            a = float(terms[0])
            b = float(terms[1])
            c = float(terms[-1]) if len(terms) > 2 else 0
            discr = b ** 2 - 4 * a * c
            roots.append(-b / (2 * a))

    if len(roots) == 0:
        roots = [0]

    elif len(roots) > 0 and \
            any(([str(type(i)) in ["<class 'sympy.core.numbers.NegativeOne'>", "<class 'sympy.core.add.Add'>",
                                         "<class 'sympy.core.power.Pow'>", "<class 'sympy.core.mul.Mul'>",
                                         "<class 'sympy.core.numbers.Rational'>"]
                  for i in roots])):
        roots = [0]

    X_min = float(min(roots) - 4.0)
    X_max = float(max(roots) + 4.0)

    x = np.linspace(X_min, X_max, 100)
    y = eval(function[1])
    ax.plot(x, y)

    plt.show()

else:
    function = eval(f"lambda x: {function}")

    x = np.linspace(0, 4 * np.pi, 100)
    y = [function(i) for i in x]
    ax.plot(x, y)

    plt.show()
