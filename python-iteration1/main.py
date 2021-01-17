from Student import *
from FileHandler import *

fileHandler=FileHandler()
fileHandler.readStudentFile("python-iteration1\CES3063_Fall2020_rptSinifListesi.XLS")

pollFileInput = input("Please import poll file")
answerSheetInput = input("Please import answersheet")

fileHandler.readPollFile(pollFileInput)
fileHandler.readAnswerSheet(answerSheetInput)
for i in fileHandler.getQuizPollList():
    try:
        i.setAnswerSheetList(fileHandler.getAnswerSheetList())
        i.differantiateStudents(fileHandler.getQuizStudentList())
        i.getQuizStudents()
        i.calculatePoints()
        i.getReportForQuestions()
        i.drawCharts()
    except AttributeError:
        print("no")
    