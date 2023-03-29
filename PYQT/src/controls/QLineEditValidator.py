'''

限制QLineEdit控件的输入(校验器)

QLineEdit控件与众不同的地方在于,它可以设置校验器,用于限制输入的内容.

'''

import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import QIntValidator, QDoubleValidator, QRegExpValidator
from PyQt5.QtCore import QRegExp

class QLineEditValidator(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('校验器')

        # 创建表单布局
        formLayout = QFormLayout()

        intLineEdit = QLineEdit()
        doubleLineEdit = QLineEdit()
        validatorLineEdit = QLineEdit()

        formLayout.addRow('整数类型', intLineEdit)
        formLayout.addRow('浮点类型', doubleLineEdit)
        formLayout.addRow('数字和字母', validatorLineEdit)

        # 设置校验器
        # QIntValidator 整数校验器
        intValidator = QIntValidator(self)
        intValidator.setRange(1, 99)

        # QDoubleValidator 浮点数校验器
        # 参数1: 小数点前的位数
        # 参数2: 小数点后的位数
        # 参数3: 浮点数的精度
        doubleValidator = QDoubleValidator(self)
        doubleValidator.setRange(-360, 360)
        doubleValidator.setNotation(QDoubleValidator.StandardNotation)
        doubleValidator.setDecimals(2)

        # QRegExpValidator 正则表达式校验器
        reg = QRegExp('[a-zA-Z0-9]+$')
        regValidator = QRegExpValidator(self)
        regValidator.setRegExp(reg)

        # 设置校验器
        intLineEdit.setValidator(intValidator)
        doubleLineEdit.setValidator(doubleValidator)
        validatorLineEdit.setValidator(regValidator)

        self.setLayout(formLayout)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = QLineEditValidator()
    main.show()
    sys.exit(app.exec_())
