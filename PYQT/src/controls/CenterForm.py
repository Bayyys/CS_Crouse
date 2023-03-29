import sys
from PyQt5.QtWidgets import QApplication, QMainWindow,QDesktopWidget
from PyQt5.QtGui import QIcon

class CenterForm(QMainWindow):
    def __init__(self, parent=None):
        super(CenterForm, self).__init__(parent)

        # 设置主窗口的标题
        self.setWindowTitle("让窗口居中")

        # 设置窗口的尺寸
        self.resize(400, 300)

        self.center()

    def center(self):
        # 获取屏幕坐标系
        screen = QDesktopWidget().screenGeometry()
        # 获取窗口坐标系
        size = self.geometry()
        newLeft = (screen.width() - size.width()) // 2
        newTop = (screen.height() - size.height()) // 2

        self.move(newLeft, newTop)

if __name__ == '__main__':
    app = QApplication(sys.argv)

    # 设置窗口的图标，引用当前目录下的图片
    app.setWindowIcon(QIcon('./images/Dragon.ico'))
    main = CenterForm()
    main.show()

    sys.exit(app.exec_())