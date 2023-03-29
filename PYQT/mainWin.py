from PyQt5.QtWidgets import *
import sys
from  countWin import NewDateDialog

class MainWin(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()
    
    def initUI(self):
        layout = QVBoxLayout()

        self.numShow = QLineEdit(self)
        self.numShow.setText("0")

        self.btn_start = QPushButton("开始计数")
        self.btn_start.clicked.connect(self.startCount)

        layout.addWidget(self.numShow)
        layout.addWidget(self.btn_start)
        self.setLayout(layout)
    
    def startCount(self):
        dialog = NewDateDialog()
        dialog.count_thread.count_signal.connect(self.numShow.setText)
        dialog.exec_()

if __name__ == "__main__":
    app = QApplication(sys.argv)
    mainWin = MainWin()
    mainWin.show()
    sys.exit(app.exec_())