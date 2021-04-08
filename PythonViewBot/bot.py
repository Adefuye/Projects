#Selenium Webdriver must be installed for this to work

import time;
from selenium import webdriver;

#time to refresh page (seconds)
Timer = 3


#youtube link
link = 'https://www.youtube.com/watch?v=JXDdRha8cyk'

#number of views
views = 9999

driver = webdriver.Chrome('webdrivers\chromedriver.exe')
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
  
