import socket
import os
import jpysocket
from buildgraph import GraphBuil
from eventDir import Search_and_save_graph_in_Dir


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
    sock.bind(('localhost', 3030))
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    sock.listen(5)
    sock.setblocking(1)
    builder = GraphBuil()
    dirGraphs = Search_and_save_graph_in_Dir('graphDir')
    dirGraphs.removeFolder()
    cntElemDir = len(os.listdir('graphDir'))
    print('SERVER TURN ON')
    print('----------------------------------------')
    while True:
        print("Socket Is Listening....")
        client, addr = sock.accept()
        print("Connected To ", addr)
        client.setblocking(1)
        data = client.recv(2048)
        if jpysocket.jpydecode(data) == 'stop server adminServer':
            print("Vladyko destroyed the server")
            break
        if 'x' in jpysocket.jpydecode(data):
            builder.buildG(jpysocket.jpydecode(data))
            while cntElemDir == 0:
                cntElemDir = len(os.listdir('graphDir'))

            fileGraph = open(f'graphDir\Graph.png', 'rb')
            bt = fileGraph.read()
            client.sendall(bt)

    client.close()
    print('----------------------------------------')
    print('SERVER TURN OFF')
