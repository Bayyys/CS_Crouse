"""

树控件（QTreeWidget）的基本用法



"""

import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import QIcon, QBrush, QColor
from PyQt5.QtCore import Qt


class BasicTreeWidget(QMainWindow):
    def __init__(self, parent=None):
        super(BasicTreeWidget, self).__init__(parent)
        self.setWindowTitle("树控件（QTreeWidget）的基本用法")

        self.tree = QTreeWidget()
        # 为树控件指定列数
        self.tree.setColumnCount(2)

        # 指定列标签
        self.tree.setHeaderLabels(["Key", "Value"])

        root2 = QTreeWidgetItem(self.tree)
        root2.setText(0, "根节点2")
        root2.setIcon(0, QIcon("./images/root.png"))

        root = QTreeWidgetItem(self.tree)
        root.setText(0, "根节点")
        root.setIcon(0, QIcon("./images/root.png"))
        self.tree.setColumnWidth(0, 160)

        # 添加子节点1
        child1 = QTreeWidgetItem(root)
        child1.setText(0, "子节点1")
        child1.setText(1, "子节点1的数据")
        child1.setIcon(0, QIcon("./images/bao3.png"))
        child1.setCheckState(0, Qt.Checked)

        # 添加子节点2
        child2 = QTreeWidgetItem(root)
        child2.setText(0, "子节点2")
        child2.setIcon(0, QIcon("./images/bao6.png"))

        # 为child2添加一个子节点
        child3 = QTreeWidgetItem(child2)
        child3.setText(0, "子节点2-1")
        child3.setText(1, "2-1 新的值")
        child3.setIcon(0, QIcon("./images/music.png"))

        child4 = QTreeWidgetItem(child3)
        child4.setText(0, "子节点2-1-1")
        child4.setText(1, "2-1-1 新的值")

        child5 = QTreeWidgetItem(child4)
        child5.setText(0, "子节点2-1-1-1")

        self.tree.expandAll()
        # self.tree.expandToDepth(0)
        # root.setExpanded(True)
        root.setExpanded(False)
        self.tree.doubleClicked.connect(self.onTreeDoubleClick)
        self.tree.setContextMenuPolicy(Qt.CustomContextMenu)
        self.tree.customContextMenuRequested.connect(self.onRightClick)
        # self.tree.mousePressEvent = self.onRightClick

        self.setCentralWidget(self.tree)

    def onTreeDoubleClick(self, index):
        item = self.tree.currentItem()
        print(index.row())
        print("key=%s,value=%s" % (item.text(0), item.text(1)))

    def onRightClick(self, index):
        print("右键点击")
        item = self.tree.currentItem()
        print("key=%s,value=%s" % (item.text(0), item.text(1)))

if __name__ == "__main__":
    app = QApplication(sys.argv)
    tree = BasicTreeWidget()
    tree.show()
    sys.exit(app.exec_())
