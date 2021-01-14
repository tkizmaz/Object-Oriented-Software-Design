class Student:
    def __init__(self):
        self.__student_id = ""
        self.__student_name = ""
        self.__student_surname = ""
        self.__student_email = ""
        self.__student_attandance_rate = ""
        self.__student_attandance_percentage = ""

    def setStudentId(self,student_id):
        self.__student_id = student_id
    def setStudentName(self,student_name):
        self.__student_name = student_name
    def setStudentSurname(self,student_surname):
        self.__student_surname = student_surname
    def setStudentEmail(self,student_email):
        self.__student_email = student_email
    def setStudentAttandanceRate(self,student_attadance_rate):
        self.__student_attandance_rate = student_attadance_rate
    def setStudentAttandancePercentage(self,student_attandance_percentage):
        self.__student_attandance_percentage = student_attandance_percentage




    def getStudentId(self):
        return self.__student_id
    def getStudentName(self):
        return self.__student_name
    def getStudentSurname(self):
        return self.__student_surname
    def getStudentEmail(self):
        return self.__student_email
    def getStudentAttandanceRate(self):
        return self.__student_attandance_rate
    def getStudentAttandancePercentage(self):
        return self.__student_attandance_percentage