import socket
import jpysocket
from buildgraph import GraphBuil


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('localhost', 3030))
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    sock.listen(5)
    sock.setblocking(1)
    builder = GraphBuil()
    print('SERVER TURN ON')
    print('----------------------------------------')
    while True:
        print("Socket Is Listening....")
        client, addr = sock.accept()
        print("Connected To ", addr)
        client.setblocking(1)
        data = client.recv(1024)
        if data.decode('utf-8') == 'stop server adminServer'\
                or jpysocket.jpydecode(data) == 'stop server adminServer':
            print("Vladyko destroyed the server")
            break
        if 'x' in data.decode('utf-8') or 'x' in jpysocket.jpydecode(data):
            builder.buildG(jpysocket.jpydecode(data))

    client.close()
    print('----------------------------------------')
    print('SERVER TURN OFF')
