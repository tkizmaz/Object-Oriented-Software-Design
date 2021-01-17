import xlrd
import csv
from Student import *
from Question import *
from QuizPoll import *
import re
from AnswerSheet import *
from AttendancePoll import *
from Answer import *
#coding:utf8
import xlwt
from xlwt import Workbook

class FileHandler(object):

    def __init__(self):
        self.__studentList=[]
        self.__pollList=[]
        self.__quizPollList=[]
        self.__answerSheetList=[]
        self.__attendancePolls=[]
        self.__quizStudentList=[]
    def setStudentList(self,studentList):
        self.__studentList.append(studentList)

    def setPollList(self,pollList):
        self.__pollList.append(pollList)
    
    def setQuizPollList(self,pollList):
        self.__quizPollList.append(pollList)

    def getQuizPollList(self):
        return self.__quizPollList

    def setAnswerSheetList(self,answerSheet):
        self.__answerSheetList.append(answerSheet)

    def getAnswerSheetList(self):
        return self.__answerSheetList

    def setQuizStudentList(self,student):
        self.__quizStudentList.append(student)
    
    def getQuizStudentList(self):
        return self.__quizStudentList

    def getStudentList(self):
        return self.__studentList

    def getPollList(self):
        return self.__pollList

    def setAttencePolls(self,attencePoll):
        self.__attendancePolls.append(attencePoll)

    def getAttendancePolls(self):
        return self.__attendancePolls


    def readPollFile(self,filename):
        allPolls = []
        attencePoll=AttendancePoll()
        self.setAttencePolls(attencePoll)
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV: # Read each row in the fil,
                if row[4]=="Are you attending this lecture?":
                    date=row[3].split(" ")
                    date[0:]=[" ".join(date[0:-1])]
                    # if(date[0])
                    # print(date)
                    if len(row[3].split(" ")) < 4:  # basligi siliyor
                        continue
                    if int(row[3].split(" ")[3].split(":")[0]) > 9:
                        ten = row[4:]  # saat 10 ve sonrasi yapilan poll
                        for row in ten:
                            print(row)
                    if int(row[3].split(" ")[3].split(":")[0]) == 9:
                        nine = row[4:]  # 9 ve sonrasi yapilan poll

                    attencePoll.setStudentList(std)
                    attencePoll.setPollName(date[0])



                elif (len(row[4]) > 5 and row[4] != "Are you attending this lecture?"):
                    eachPoll=[]
                    for i in range(4,len(row)-2,2):
                        if(row[i] not in eachPoll):
                            eachPoll.append(row[i].rstrip())
                            if(len(eachPoll)==int((len(row)-5)/2) and (eachPoll not in allPolls)):
                                allPolls.append(eachPoll)

        list(dict.fromkeys(allPolls[0]))
        list(dict.fromkeys(allPolls[1]))
        print(allPolls[0])
        print(allPolls[1])

        for i in range(0,len(allPolls)):
            questionPoll = QuizPoll()
            for p in(allPolls[i]):
                newQuestion=Question()
                newQuestion.setQuestionText(p)
                questionPoll.addQuestions(newQuestion)
            self.setQuizPollList(questionPoll)

        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV: # Read each row in the fil,
                if (len(row[4]) > 5 and row[4] != "Are you attending this lecture?"):
                    quizStudent=Student()
                    try:
                        quizStudent.setStudentName(self.findStudent(row[1]).getStudentName())
                        quizStudent.setStudentSurname(self.findStudent(row[1]).getStudentSurname())
                        for i in range(4,len(row)-1,2):
                            eachQuestion=Question()
                            eachQuestion.setQuestionText(row[i])
                            newAnswer=Answer()
                            if ";" in row[i+1]:
                                answerList=row[i+1].split(";")
                                for ea in answerList:
                                    newAnswer.addAnswer(ea)
                            else:
                                newAnswer.addAnswer(row[i+1])
                            eachQuestion.AddAnswer(newAnswer)
                            quizStudent.setQuestionList(eachQuestion) 
                        self.__quizStudentList.append(quizStudent)
                    except AttributeError:
                        print("")
                    
                    


    def findStudent(self,row):
        # print("Row:",row)
        count=0
        pollstudentname = []
        counter1 = 0
        returnVal=""

        pollname = row.split(" ")
        fullname = pollname[0:-1]
        surname = pollname[-1]
        if "somemail.com" in row:   #adını yazmayanlar
            return

        elif len(fullname) == 1 and any(char.isdigit() for char in fullname[0]):        # adını numara ile birleşik yazan kişiler
            fullname = (''.join([x for x in fullname[0] if not x.isdigit()]))
            for i in range(1, len(fullname)):
                if fullname[i].isupper():
                    fullname=(fullname[:i]+" "+fullname[i:]).upper()
                    break

        elif len(fullname)==1:
            fullname = pollname[0].upper()

        elif pollname[0].isnumeric():       #adında numarası olanlar
            pollname[0:-1] = [" ".join(pollname[1:-1])]
            fullname = pollname[0].upper()

        elif len(fullname[0:]) > 1:
            fullname[0:] = [" ".join(fullname[0:])]
            fullname = fullname[0].upper()
        surname = surname.upper()
        pollstudentname.append(fullname)
        pollstudentname.append(surname)

        # print("Len pol studen", len(pollstudentname))
        for i in range(0, len(pollstudentname)):
            pollstudentname[i] = self.changeTurkishChar(pollstudentname[i])

        for student in self.getStudentList():
            # print(" \nRegistred", student.getStudentName(), student.getStudentSurname())
            for i in range(0, len(pollstudentname), 2):
                a = self.changeTurkishChar(student.getStudentSurname())
                if pollstudentname[i + 1] in a:
                    # print("Surname: ", pollstudentname[i + 1], a)
                    b = self.changeTurkishChar((student.getStudentName()))
                    if pollstudentname[i] in b:
                        # print("Name: ", pollstudentname[i], b)
                        # print(student.getStudentName(), student.getStudentSurname(), "attended.")
                        count = count + 1
                        # return (student.getStudentName(), student.getStudentSurname())            #isim d
                        return student



    def changeTurkishChar(self, strList):
        tr_chars = {'Ç': 'C', 'Ğ': 'G', 'İ': 'I', 'Ö': 'O', 'Ş': 'S', 'Ü': 'U'}
        for src, target in tr_chars.items():
            strList = strList.replace(src, target)
        return strList


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
                #print(sheet.row_values(row))
                student = Student()
                student.setStudentId(sheet.cell_value(row, 2))
                student.setStudentName(sheet.cell_value(row, 4))
                student.setStudentSurname(sheet.cell_value(row, 7))
                self.setStudentList(student)


    def writeAttendence(self):
        wb = Workbook()
        sheet1 = wb.add_sheet('Sheet 1')

        for attenPollItem in self.getAttendancePolls():
            remaning=[x for x in  self.getStudentList() if x not in attenPollItem.getStudentList() ]
            for stdInPoll in attenPollItem.getStudentList():
                i = 0

                for im in self.getStudentList():
                    # print("\n all:: ", im.getStudentName(), im.getStudentSurname())
                    if stdInPoll == im:
                        sheet1.write(i, 0,im.getStudentId())
                        sheet1.write(i, 1, im.getStudentName())
                        sheet1.write(i, 2, im.getStudentSurname())
                        sheet1.write(i, 3, "True")
                    i+=1

            for x in remaning:
                for i in range(len(self.getStudentList())):
                    try:
                        sheet1.write(i, 0, x.getStudentId())
                        sheet1.write(i, 1,  x.getStudentName())
                        sheet1.write(i, 2,  x.getStudentSurname())
                        sheet1.write(i, 3, "False")
                        break

                    except:
                        continue
        wb.save('AttendanceOutput.xls')


    def writeGlobalStatistic(self):
        pass