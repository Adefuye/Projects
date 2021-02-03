#Selenium Webdriver must be installed for this to work

import time
from selenium import webdriver

#time to refresh page (seconds)
Timer = 35

#youtube link
link = 'https://youtu.be/9v8VD11-Zpw'

#number of views
views = 9999

driver = webdriver.Chrome()
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
  
