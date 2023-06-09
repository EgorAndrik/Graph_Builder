from pathlib import Path
from shutil import rmtree


class Search_and_save_graph_in_Dir:
    def __init__(self, path):
        self.path = path

    def removeFolder(self):
        for path in Path(self.path).iterdir():
            if path.is_dir():
                rmtree(path)
            else:
                path.unlink()
