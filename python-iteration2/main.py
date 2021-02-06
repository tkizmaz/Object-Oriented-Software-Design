from Student import *
from FileHandler import *
import os

fileHandler=FileHandler()
fileHandler.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readAnswerSheet("D:\GitHub\CSE3063\python-iteration2\CSE3063 OOSD Weekly Session 1 - Monday Quizzes ANSWER KEY.txt")

entries = os.listdir('Polls')
for entry in entries:
     print(entry)
     fileHandler.readPollFile("Polls/"+entry)

# for poll in pollList:


fileHandler.writeAttendence()