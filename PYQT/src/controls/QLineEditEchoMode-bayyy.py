"""

QLineEdit控件与回显模式



基本功能：输入单行的文本



EchoMode(回显模式）

4种回显模式

1. Normal
2. NoEcho
3. Password
4. PasswordEchoOnEdit

"""

import sys

from PyQt5.QtWidgets import *


class QLineEditEchoMode(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle("文本输入框的回显模式")

        formLayout = QFormLayout()

        naomalLineEdit = QLineEdit()
        noEchoLineEdit = QLineEdit()
        passwordLineEdit = QLineEdit()
        passwordEchoOnEditLineEdit = QLineEdit()
