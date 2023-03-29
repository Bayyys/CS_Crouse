import sys
import test, grid

from PyQt5.QtWidgets import *
from PyQt5 import QtCore
import sys


class LambdaTest(QMainWindow):
    def __init__(self):
        super(LambdaTest, self).__init__()
        self.UIinit()
    
    def UIinit(self):
        layout = QVBoxLayout()
        self.okButton = QPushButton("OK", self)
        self.okButton.setObjectName("okButton")
        self.cancelButton = QPushButton("Cancel", self)
        self.cancelButton.setObjectName("cancelButton")
        QtCore.QMetaObject.connectSlotsByName(self)
        
        frame = QWidget()
        layout.addWidget(self.okButton)
        layout.addWidget(self.cancelButton)
        frame.setLayout(layout)

        self.setCentralWidget(frame)

    @QtCore.pyqtSlot()
    def on_okButton_clicked(self):
        print("OK")

    @QtCore.pyqtSlot()
    def on_cancelButton_clicked(self):
        print("Cancel")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = LambdaTest()
    mainWindow.show()
    sys.exit(app.exec_())