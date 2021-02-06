import pandas
class Poll:

    def __init__(self):
        self.__pollName = ""
        self.__studentList=[]
        self.__dateTime=""
        self.__unmatchedStudents=[]


    def setPollName(self,pollName):
        self.__pollName = pollName

    def getPollName(self):
        return self.__pollName

    def setStudentList(self,student):
        self.__studentList.append(student)

    def getStudentList(self):
        return  self.__studentList

    def setDateTime(self,dateTime):
        self.__dateTime=dateTime

    def getDateTime(self):
        return self.__dateTime

    def setUnmatchedStudents(self,unmatchedStudent):
        self.__unmatchedStudents.append(unmatchedStudent)

    def getUnmatchedStudents(self):
        return self.__unmatchedStudents
