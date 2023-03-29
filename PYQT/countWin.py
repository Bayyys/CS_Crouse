from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
import sys

class Count(QThread):
    count_signal = pyqtSignal(str)

    def run(self):
        count = 0
        while(True):
            count += 1
            self.count_signal.emit(str(count))
            self.sleep(1)
        

class NewDateDialog(QDialog):
    count_signal = pyqtSignal(str)

    def __init__(self):
        super().__init__()
        self.setWindowTitle('计数ing...')
        # 在布局中添加部件
        layout = QVBoxLayout(self)

        self.count = QLineEdit(self)

        layout.addWidget(self.count)

        self.btn_start = QPushButton("开始计数")
        self.btn_start.clicked.connect(self.startCount)

        layout.addWidget(self.btn_start)

        # 使用两个button(ok和cancel)分别连接accept()和reject()槽函数
        buttons = QDialogButtonBox(
            QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
            Qt.Horizontal, self)
        buttons.accepted.connect(self.accept)
        buttons.rejected.connect(self.reject)

        layout.addWidget(buttons)

        self.count_thread = Count()
        self.count_thread.count_signal.connect(self.count.setText)

    def startCount(self):

        self.count_thread.start()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    form = NewDateDialog()
    form.show()
    sys.exit(app.exec_())


