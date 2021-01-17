from Student import *
from FileHandler import *

# emre=Student()
# emre.setStudentEmail("rock_emrbrky")
# print(emre.getStudentEmail())



fileHandler=FileHandler()
fileHandler.readStudentFile("python-iteration1\CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readPollFile("python-iteration1\CSE3063_20201116_Mon_zoom_PollReport.csv")
fileHandler.readAnswerSheet("python-iteration1\QA2.csv")
fileHandler.getQuizPollList()[1].setAnswerSheetList(fileHandler.getAnswerSheetList())
fileHandler.getQuizPollList()[1].differantiateStudents(fileHandler.getQuizStudentList())
fileHandler.getQuizPollList()[1].getQuizStudents()
fileHandler.getQuizPollList()[1].calculatePoints()
fileHandler.getQuizPollList()[1].getReportForQuestions()
fileHandler.getQuizPollList()[1].getCharts()