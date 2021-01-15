import xlrd
import csv
from Student import *
from Question import *
from QuizPoll import *

class FileHandler(object):

    def __init__(self):
        self.__studentList=[]
        self.__pollList=[]

    def setStudentList(self,studentList):
        self.__studentList.append(studentList)

    def setPollList(self,pollList):
        self.__pollList.append(pollList)

    def getStudentList(self):
        return self.__studentList

    def getPollList(self):
        return self.__pollList

    def readPollFile(self,filename):
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV:                             # Read each row in the file

                if row[4] == "Are you attending this lecture?":
                    name=""
                    studentnamelength = len(row[1].upper().split(" "))
                    for z in range(studentnamelength-1):
                        if(studentnamelength-1==0):
                            name+=row[1].upper().split(" ")[z].upper()
                        else:
                            name+=row[1].upper().split(" ")[z].upper()+" "
                    surname=row[1].upper().split(" ")[studentnamelength-1].upper()
                    pollStudents[name] = surname
        
        for key in pollStudents:
            for stname in range(len(self.__studentList)):
                name=self.__studentList[stname].getStudentName()
                surname=self.__studentList[stname].getStudentSurname()
                print("nameinsd "+name)
                if(key.strip(" ") in name.strip(" ") and pollStudents[key].strip(" ") in surname.strip(" ")):
                    print(key+" "+pollStudents[key])
                    pass


    def readAnswerSheet(self,filename):
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=';')
            for row in readCSV:
                if len(row)==1: #Poll Name
                    poll = QuizPoll()
                    poll.setPollName(row[0].split("\\")[-1].split(".")[0])
                    self.setPollList(poll)
                    print(poll.getPollName())

                else:
                    question=Question()
                    question.setQuestionText(row[0])
                    question.setQuestionRightAnswer(row[1])
                    print(question.getQuestionText())
                    print(question.getQuestionRightAnswer())
                    print("\n")
                    poll.addQuestions(question)



    def readStudentFile(self,filename):
        wb = xlrd.open_workbook(filename)
        sheet = wb.sheet_by_index(0)

        for row in range(13, sheet.nrows):
            if ((sheet.cell_value(row, 2)).isnumeric()):
                print(sheet.row_values(row))
                student = Student()
                student.setStudentId(sheet.cell_value(row, 2))
                student.setStudentName(sheet.cell_value(row, 4))
                student.setStudentSurname(sheet.cell_value(row, 7))
                self.setStudentList(student)
    

    def writeAttendence(self):
        pass

    def writePollResult(self):
        pass

    def writeStatistic(self):
        pass

    def writeGlobalStatistic(self):
        pass

