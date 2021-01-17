from Student import *
from FileHandler import *

fileHandler=FileHandler()
fileHandler.readStudentFile("python-iteration1\CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readPollFile("python-iteration1\CSE3063_20201116_Mon_zoom_PollReport.csv")
fileHandler.readAnswerSheet("python-iteration1\QA2.csv")
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
    