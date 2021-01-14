class Student:
    def __init__(self):
        self.__studentID = ""
        self.__studentName = ""
        self.__studentSurname = ""
        self.__studentEmail = ""
        self.__studentAttandanceRate = 0
        self.__studentAttandancePercentage = 0

    def setStudentId(self,studentID):
        self.__student_id = studentID
    def setStudentName(self,studentName):
        self.__student_name = studentName
    def setStudentSurname(self,studentSurname):
        self.__student_surname = studentSurname
    def setStudentEmail(self,studentEmail):
        self.__student_email = studentEmail
    def setStudentAttandanceRate(self,studentAttadanceRate):
        self.__student_attandance_rate = studentAttadanceRate
    def setStudentAttandancePercentage(self,studentAttandancePercentage):
        self.__student_attandance_percentage = studentAttandancePercentage




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