import xlrd
from Poll import *

for i in range (10):
    newPoll=Poll()
    print(newPoll.getPollName())
    newPoll.setPollName("Taha")
    print(newPoll.getPollName())