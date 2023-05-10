import socket


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Conect with server')
s.connect(('localhost', 3030))
mes = 'openServer'
print('Welcome, Your Honor.')
while mes != 'stop':
    mes = '_ ' + input('your order sir ----> ')
    s.sendall(mes.encode('utf-8'))
    if mes[2:] == 'stop server adminServer':
        break
    s.close()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(('localhost', 3030))
s.close()
print('Sign Out')
print('Goodbye, sir')
