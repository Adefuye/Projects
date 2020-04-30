import time;
from selenium import webdriver;

#time to refresh page (seconds)
Timer = 2

#youtube link
link = 'https://youtu.be/yDjmdbEnsVY'

#number of views
views = 1000

driver = webdriver.Chrome()
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
