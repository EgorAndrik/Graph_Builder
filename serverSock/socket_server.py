import socket
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
    cntElemDir = dirGraphs.search_and_save_graph()
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
            tmpCnt = cntElemDir
            while tmpCnt == cntElemDir:
                tmpCnt = dirGraphs.search_and_save_graph()
            cntElemDir = tmpCnt

            fileGraph = open(f'graphDir\Graph{cntElemDir}.png', 'rb')
            client.sendfile(fileGraph)
            fileGraph.close()


    client.close()
    print('----------------------------------------')
    print('SERVER TURN OFF')
