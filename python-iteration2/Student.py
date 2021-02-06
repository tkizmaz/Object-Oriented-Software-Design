class Student:
    def __init__(self):
        self.__studentID = ""
        self.__studentName = ""
        self.__studentSurname = ""
        self.__studentEmail = ""
        self.__studentAttandanceRate = 0
        self.__studentAttandancePercentage = 0
        self.__questionList=[]
    def setStudentId(self,studentID):
        self.__studentID = studentID
    def setStudentName(self,studentName):
        self.__studentName = studentName
    def setStudentSurname(self,studentSurname):
        self.__studentSurname = studentSurname
    def setStudentEmail(self,studentEmail):
        self.__studentEmail = studentEmail
    def setStudentAttandanceRate(self,studentAttadanceRate):
        self.__studentAttandanceRate = studentAttadanceRate
    def setStudentAttandancePercentage(self,studentAttandancePercentage):
        self.__studentAttandancePercentage = studentAttandancePercentage

    def setQuestionList(self,question):
        self.__questionList = question
    def getQuestionList(self):
        return self.__questionList

    def getStudentId(self):
        return self.__studentID
    def getStudentName(self):
        return self.__studentName
    def getStudentSurname(self):
        return self.__studentSurname
    def getStudentEmail(self):
        return self.__studentEmail
    def getStudentAttandanceRate(self):
        return self.__studentAttandanceRate
    def getStudentAttandancePercentage(self):
        return self.__studentAttandancePercentage