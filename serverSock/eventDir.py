import os


class Search_and_save_graph_in_Dir:
    def __init__(self, path):
        self.path = path

    def search_and_save_graph(self):
        if os.path.exists(self.path):
            if os.path.isdir(self.path):
                data = os.listdir(self.path)
                return len(data)
        else:
            return "Not FOUND ERROR 404"
