#Selenium Webdriver must be installed for this to work

import time;
from selenium import webdriver;

#time to refresh page (seconds)
Timer = 35

#youtube link
link =https://www.youtube.com/watch?v=vxhJw8-VV2E
  

#number of views
views = 100

driver = webdriver.Chrome()
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
  
