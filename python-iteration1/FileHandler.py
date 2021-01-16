import xlrd
import csv
from Student import *
from Question import *
from QuizPoll import *
import re
from AnswerSheet import *
from AttendancePoll import *
#coding:utf8

class FileHandler(object):

    def __init__(self):
        self.__studentList=[]
        self.__pollList=[]
        self.__quizPollList=[]
        self.__answerSheetList=[]
    def setStudentList(self,studentList):
        self.__studentList.append(studentList)

    def setPollList(self,pollList):
        self.__pollList.append(pollList)

    def setAnswerSheetList(self,answerSheet):
        self.__answerSheetList.append(answerSheet)

    def getAnswerSheetList(self):
        return self.__answerSheetList

    def getStudentList(self):
        return self.__studentList

    def getPollList(self):
        return self.__pollList

    def readPollFile(self,filename):
        allPolls=[]
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            count = 0
            poll = {}
            pollstudent={}

            for row in readCSV:
                pollname = row[1].split(" ")
                fullname = pollname[0:-1]
                surname =pollname[-1]
                if "somemail.com" in row[1]:
                    continue
                elif len(fullname) == 1 :
                    fullname = (''.join([x for x in fullname[0] if not x.isdigit()])).upper()

                elif pollname[0].isnumeric():
                    pollname[0:-1] = [" ".join(pollname[1:-1])]
                    fullname = pollname[0].upper()

                elif len(fullname[0:]) > 1:
                    fullname[0:] = [" ".join(fullname[0:])]
                    fullname = fullname[0].upper()
                surname = surname.upper()
                for student in self.getStudentList():
                    if surname in student.getStudentSurname():
                        if fullname.split(" ")[0] in student.getStudentName():
                            count = count +1
                            break        

            for row in readCSV: # Read each row in the fil
                if (len(row[4]) > 5 and row[4] != "Are you attending this lecture?"):
                    eachPoll=[]
                    for i in range(4,len(row)-2,2):
                        if(row[i] not in eachPoll):
                            eachPoll.append(row[i].rstrip())
                            if(len(eachPoll)==int((len(row)-5)/2) and (eachPoll not in allPolls)):
                                allPolls.append(eachPoll)


        for i in range(0,len(allPolls)):
            questionPoll = QuizPoll()
            for p in(allPolls[i]):
                questionPoll.addQuestions(p)
            self.setPollList(questionPoll)

        for i in self.getPollList():
            print(i)


    def readAnswerSheet(self,filename):
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=';')
            for row in readCSV:
                if len(row)==1: #Poll Name
                    answerSheet = AnswerSheet()
                    answerSheet.setPollName(row[0].split("\\")[-1].split(".")[0])
                    self.setAnswerSheetList(answerSheet)
                else:
                    question=Question()
                    question.setQuestionText(row[0])
                    for i in range(len(row[1].split(";"))):
                        question.setQuestionRightAnswer(row[1].split(";")[i])
                    answerSheet.addQuestions(question)




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
    
            if ((sheet.cell_value(row, 2)).isnumeric()):
                student = Student()
                #.setStudentId(sheet.cell_value(row, 2))
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