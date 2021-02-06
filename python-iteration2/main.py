from Student import *
from FileHandler import *
import os

fileHandler=FileHandler()
fileHandler.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readAnswerSheet("CSE3063 OOSD Weekly Session 1 - Monday Quizzes ANSWER KEY.txt")

entries = os.listdir('Polls')
for entry in entries:
     print(entry)
     fileHandler.readPollFile("Polls/"+entry)

fileHandler.writeAttendence()


## The only output we get the overall attendance. You can find the result in the AttendanceOutput.xls file.


