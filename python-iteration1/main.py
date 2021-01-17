from Student import *
from FileHandler import *

# emre=Student()
# emre.setStudentEmail("rock_emrbrky")
# print(emre.getStudentEmail())



fileHandler=FileHandler()
fileHandler.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readPollFile("CSE3063_20201123_Mon_zoom_PollReport.csv")
fileHandler.writeAttendence()
# fileHandler.readAnswerSheet("QA2.csv")
# fileHandler.getPollList()[1].setAnswerSheetList(fileHandler.getAnswerSheetList())
# print(fileHandler.getPollList()[1].getPollName())

