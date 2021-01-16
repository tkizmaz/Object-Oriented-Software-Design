from Student import *
from FileHandler import *

# emre=Student()
# emre.setStudentEmail("rock_emrbrky")
# print(emre.getStudentEmail())

fileHandler=FileHandler()
fileHandler.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")
fileHandler.readAnswerSheet("QA2.csv")
fileHandler.readPollFile("CSE3063_20201116_Mon_zoom_PollReport.csv - CSE3063_20201116_Mon_zoom_PollReport.csv.csv")
