'''

多窗口交互（1）：不使用信号与槽

Win1

Win2

'''

import sys
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from DateDialog import DateDialog
import time

class tT(QThread):
    up_date = pyqtSignal(str)

    def run(self):
        while True:
            data = QDateTime.currentDateTime()
            currentTime = data.toString("yyyy-MM-dd hh:mm:ss")
            self.up_date.emit(str(currentTime))
            time.sleep(1)

class MultiWindow1(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("多窗口交互（1）：不使用信号与槽")

        self.lineEdit = QLineEdit(self)
        self.button1 = QPushButton('弹出对话框1')
        self.button1.clicked.connect(self.onButton1Click)

        self.button2 = QPushButton('弹出对话框2')
        self.button2.clicked.connect(self.onButton2Click)

        self.text = QLineEdit(self)
        self.tT = tT()
        self.tT.up_date.connect(self.showTime)
        self.tT.start()

        gridLayout = QGridLayout()
        gridLayout.addWidget(self.lineEdit)
        gridLayout.addWidget(self.button1)
        gridLayout.addWidget(self.button2)
        gridLayout.addWidget(self.text)

        self.setLayout(gridLayout)
    
    def showTime(self, time):
        self.text.setText(time)


    def onButton1Click(self):
        dialog = DateDialog()
        result = dialog.exec()
        if result == QDialog.Accepted:
            date = dialog.dateTime()
            self.lineEdit.setText(date.date().toString())
        else:
            ...
            # self.lineEdit.setText('')
        dialog.destroy()

    def onButton2Click(self):
        date,time,result = DateDialog.getDateTime()

        if result == QDialog.Accepted:
            self.lineEdit.setText(date.toString())
            print('点击确定按钮')
        else:
            print('单击取消按钮')
if __name__ == "__main__":
    app = QApplication(sys.argv)
    form = MultiWindow1()
    form.show()
    sys.exit(app.exec_())
