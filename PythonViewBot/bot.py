import time;
from selenium import webdriver;

#time to refresh page (seconds)
Timer = 2

#youtube https://www.youtube.com/watch?v=pFL7I3QK5-Ylink
link = 'https://www.youtube.com/watch?v=pFL7I3QK5-Y

#number of views
views = 500

driver = webdriver.Chrome()
driver.get(link)

for i in range(views):
    time.sleep(Timer)
    driver.refresh()
    print(i)
