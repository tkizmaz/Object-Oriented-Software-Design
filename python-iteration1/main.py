from Student import *
from FileHandler import *

# emre=Student()
# emre.setStudentEmail("rock_emrbrky")
# print(emre.getStudentEmail())

fileHandler=FileHandler()
#fileHandler.readStudentFile("python-iteration1\CES3063_Fall2020_rptSinifListesi.XLS")
#fileHandler.readPollFile("CSE3063_20201123_Mon_zoom_PollReport.csv")
fileHandler.readAnswerSheet("QA2.csv")