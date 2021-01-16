import pandas
class Poll:

    def __init__(self):
        self.__pollName = ""
        self.__studentList=[]
    def setPollName(self,pollName):
        self.__pollName = pollName

    def getPollName(self):
        return self.__pollName

    def setStudentList(self,student):
        self.__studentList.append(student)

    def getStudentList(self):
        return  self.__studentList
