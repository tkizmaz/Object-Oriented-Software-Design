from Student import *
from FileHandler import *
import os

fileHandler=FileHandler()
fileHandler.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")

# pollFileInput = input("Please import poll file")
# # answerSheetInput = input("Please import answersheet")
#
# fileHandler.readPollFile(pollFileInput)

# fileHandler.readAnswerSheet(answerSheetInput)
# for i in fileHandler.getQuizPollList():
#     try:
#         i.setAnswerSheetList(fileHandler.getAnswerSheetList())
#         i.differantiateStudents(fileHandler.getQuizStudentList())
#         i.getQuizStudents()
#         i.calculatePoints()
#         i.getReportForQuestions()
#         i.drawCharts()
#     except AttributeError:
#         print("no")

entries = os.listdir('Polls')
for entry in entries:
     # print(entry)
     fileHandler.readPollFile("Polls/"+entry)

# for poll in pollList:
fileHandler.writeAttendence()