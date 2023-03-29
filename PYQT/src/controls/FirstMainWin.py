import sys
from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5.QtGui import QIcon

class FirstMainWin(QMainWindow):
    def __init__(self, parent=None):
        super(FirstMainWin, self).__init__(parent)

        # 设置主窗口的标题
        self.setWindowTitle("First Main Window")

        # 设置窗口的尺寸
        self.resize(400, 300)

        # 状态栏
        self.status = self.statusBar()

        self.status.showMessage("Only test, and show 5s", 5000)

if __name__ == '__main__':
    app = QApplication(sys.argv)

    # 设置窗口的图标，引用当前目录下的图片
    app.setWindowIcon(QIcon('./images/Dragon.ico'))
    main = FirstMainWin()
    main.show()

    sys.exit(app.exec_())