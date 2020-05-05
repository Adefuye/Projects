import time;
from selenium import webdriver;

#time to refresh page (seconds)
Timer = 2

#youtube link
link = 'https://www.youtube.com/watch?v=hW_WFUs3hfQ'

#number of views
views = 10

driver = webdriver.Chrome()
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
    print(i)
