from Poll import *
class AttendancePoll(Poll):
    
    def __init__(self):
        super().__init__()
        self.__student_list = []

    def addStudents(self,student):
        self.__student_list.append(student)