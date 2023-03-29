'''

QTreeView 控件与系统定制模式

QTreeWidget

Model/View 框架

QDirModel

'''

from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import sys


class TreeView(QMainWindow):
    def __init__(self, parent=None):
        super(TreeView, self).__init__(parent)
        self.setWindowTitle('树结构的系统定制')
        self.resize(600, 500)
        model = QDirModel()
        self.tree = QTreeView()
        self.tree.setModel(model)
        self.tree.expandToDepth(0)
        self.setCentralWidget(self.tree)



if __name__ == '__main__':
    app = QApplication(sys.argv)
    tree = TreeView()
    tree.show()
    sys.exit(app.exec_())
