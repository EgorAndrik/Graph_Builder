import socket
import time


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Conect with server')
sock.connect(('localhost', 3030))
mes = 'openServer'
print('Welcome, Your Honor.')
while mes != 'stop':
    mes = '_ ' + input('your order sir ----> ')
    sock.sendall(mes.encode('utf-8'))
    if mes[2:] == 'stop server adminServer':
        break

    if 'x' in mes:
        data = sock.recv(1048576)
        fileGraph = open('gr.png', 'wb')
        fileGraph.write(data)

    sock.close()
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(('localhost', 3030))
sock.close()
print('Sign Out')
print('Goodbye, sir')
