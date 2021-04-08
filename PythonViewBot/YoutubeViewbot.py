#Selenium Webdriver must be installed for this to work

import time;
from PyQt5 import QtWidgets
from PyQt5 import QtGui
from PyQt5.QtWidgets import QApplication, QMainWindow
import sys
from selenium import webdriver;

#application
def window():
    def runBot():
        #time to refresh page (seconds)
        Timer = 30

        #youtube link
        https://youtu.be/dCVadmaZVvo

        #number of views
        views = 500
        

        driver = webdriver.Chrome('webdrivers\chromedriver.exe')
        driver.get(link)

        for i in range(views):
            time.sleep(Timer)
            driver.refresh()
        
#------WINDOW------------
    app = QApplication(sys.argv)
    win = QMainWindow()
    win.setGeometry(200, 200, 300, 150)
    win.setWindowTitle("YouTube ViewBot")

    linkLabel = QtWidgets.QLabel(win)
    linkLabel.setText("Video Link")
    linkLabel.move(15, 20)
    linkInput = QtWidgets.QLineEdit(win)
    linkInput.setGeometry(110, 10, 191, 20)
    linkInput.move(100, 25)

    runBotBtn = QtWidgets.QPushButton(win)
    runBotBtn.setGeometry(60, 270, 180, 40)
    runBotBtn.move(65, 80)       
    runBotBtn.setText("RUN VIEWBOT")
    runBotBtn.clicked.connect(runBot)

    win.show()
    sys.exit(app.exec_())



window()

