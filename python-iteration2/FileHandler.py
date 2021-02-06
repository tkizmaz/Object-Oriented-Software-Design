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
        self. __pollDateList=[]

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

    def setPollDateList(self,date):
        self.__pollDateList.append(date)

    def getPollDateList(self):
        return self.__pollDateList

    def readPollFile(self,filename):
        currentPollDate=""
        attencePoll = AttendancePoll()

        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV: # Read each row in the fil,

                if "CSE3063 OOSD" in row[0]:  # check for course title
                    currentPollDate=row[2]

                    if currentPollDate not in self.getPollDateList():     # check there is any other poll on the same date/time # if no, add the new poll to pollList
                        self.setPollDateList(currentPollDate)
                        attencePoll.setDateTime(currentPollDate)
                        attencePoll.setPollName("Attendance Poll (" + currentPollDate + ")")
                        self.setPollList(attencePoll)
                        self.setAttencePolls(attencePoll)

                    else:
                        break    # if yes, pass to next file



                elif len(row) == 7 and (row[5]=="Yes" or row[5]=="No"):
                    std = self.findStudent(row[1])
                    if std == None:
                        attencePoll.setUnmatchedStudents(row)
                        continue
                    attencePoll.setStudentList(std)

                elif (len(row) > 5):
                    student = self.findStudent(row[1])
                    if student == None:
                        attencePoll.setUnmatchedStudents(row)
                        continue
                    elif student not in attencePoll.getStudentList():
                        attencePoll.setStudentList(student)

                    rowQueList=[]
                    rowAnsList=[]

                    for i in range(4,len(row)-1,2):
                        rowQueList.append(row[i])
                        rowAnsList.append(row[i+1])

                    for quiz in self.getQuizPollList():
                        quizQuestionList=[]
                        quizAnswerList=[]

                        for ques in quiz.getQuestions():
                            quizQuestionList.append(ques.getQuestionText())
                            quizAnswerList.append((ques.getQuestionRightAnswer()))

                        if(all(item in quizQuestionList for item in rowQueList)):
                            quiz.setQuizStudents(student)
                            answer=Answer()
                            x=0
                            for stdque in rowQueList:
                                indexOfQue = quizQuestionList.index(stdque)  #if student answer the questşion, it returns index of it
                                answer.addAnswer(rowAnsList[x])
                                answer.setStudent(Student)

                                qu=[qu  for qu in quiz.getQuestions() if stdque == qu.getQuestionText()]
                                qu[0].AddAnswer(answer)
                                student.setQuestionList(qu)

                                # if quizAnswerList[indexOfQue][0].getAnswers()[0] == rowAnsList[x]:      # if student gives the right answer
                                #     print(student.getStudentName())

                                x+=1
                            break
                else:
                    continue  ## For the intro rows



    def findStudent(self,row):
        count=0
        pollstudentname = []


        pollname = row.split(" ")
        fullname = pollname[0:-1]
        surname = pollname[-1]
        if "@" in row:   # if the name contains "@" hamiorak@marun.edu.tr

            return

        elif len(fullname) == 1 and any(char.isdigit() for char in fullname[0]):        # For the names that contain student number unified with name 150118504MehmetEtka Uzun

            fullname = (''.join([x for x in fullname[0] if not x.isdigit()]))
            for i in range(1, len(fullname)):
                if fullname[i].isupper():
                    fullname=(fullname[:i]+" "+fullname[i:]).upper()
                    break

        elif len(fullname)==1:
            fullname = pollname[0].upper()

        elif pollname[0].isnumeric():       #  For the names that contain student number 150117017 Efe Berke Erkeskin

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
                        # count = count + 1
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
            questionCount = 0
            for row in readCSV:
                if(len(row) > 0 and "Poll" in row[0]):
                    newQuizPoll = QuizPoll()
                    newAnswerSheet = AnswerSheet()
                    questionCount = row[0].split("\t")[1].split(" ")[0]

                elif len(row) > 0 and row[0][0].isnumeric():
                    for i in range(int(questionCount)):
                        if(int((row[0].split(" "))[0].strip(".")) == int(i+1)):
                            answerCount = 0
                            newQuestion = Question()
                            newLine=""
                            if(" ( Single Choice)" in row[0]):
                                newLine=(row[0].strip(str(i + 1)).strip(". ").replace(" ( Single Choice)" , ""))
                            elif(" ( Multiple Choice)" in row[0]):
                                newLine=(row[0].strip(str(i + 1)).strip(". ").replace(" ( Multiple Choice)" , ""))
                            newQuestion.setQuestionText(newLine)
                            newAnswerSheet.addQuestions(newQuestion)

                elif len(row) > 0 and "Answer" in row[0]:
                    answerCount = answerCount+1
                    newAnswer = Answer()
                    newAnswer.addAnswer(row[0].split(":")[1].strip(" "))
                    if newQuestion:
                        newQuestion.setQuestionRightAnswer(newAnswer)
                    if len(newAnswerSheet.getQuestionList()) == int(questionCount) and len(newQuestion.getQuestionRightAnswer()) == answerCount:
                        self.__answerSheetList.append(newAnswerSheet)
                        newQuizPoll.addQuestions(newAnswerSheet.getQuestionList())
                        self.__quizPollList.append(newQuizPoll)

        print(len(self.__answerSheetList))
        print(len(self.__quizPollList))


    def readStudentFile(self,filename):
        wb = xlrd.open_workbook(filename)
        sheet = wb.sheet_by_index(0)
        for row in range(13, sheet.nrows):
            if ((sheet.cell_value(row, 2)).isnumeric()):
                student = Student()
                student.setStudentId(sheet.cell_value(row, 2))
                student.setStudentName(sheet.cell_value(row, 4))
                student.setStudentSurname(sheet.cell_value(row, 7))
                self.setStudentList(student)


    def writeAttendence(self):
        wb = Workbook()

        print(self.__attendancePolls[0].getDateTime())
        sheet1 = wb.add_sheet('Sheet 1')
        sheet1.write(0, 0, "Poll Date Time")
        sheet1.write(1,0, "Student ID")
        sheet1.write(1,1, "Name")
        sheet1.write(1,2, "Surname")

        rowNo=2
        for stu in (self.getStudentList()):
            sheet1.write(rowNo, 0, stu.getStudentId())
            sheet1.write(rowNo, 1, stu.getStudentName())
            sheet1.write(rowNo, 2, stu.getStudentSurname())
            rowNo+=1


        columnNo=0
        for attenPollItem in self.getAttendancePolls():
            sheet1.write(0, columnNo + 3, " ")
            sheet1.write(1, columnNo+3, attenPollItem.getDateTime())
            absentStdList=[x for x in self.getStudentList() if x not in attenPollItem.getStudentList()]

            for stdInPoll in attenPollItem.getStudentList():
                i = 2
                for im in self.getStudentList():
                    try:
                        if stdInPoll == im:
                            sheet1.write(i, columnNo+3, "True")
                        i+=1

                    except:
                        continue

            for x in absentStdList:
                for i in range(len(self.getStudentList())):
                    try:
                        sheet1.write(i+2, columnNo+3, "False")
                        break

                    except:
                        continue
            columnNo+=1
        wb.save('AttendanceOutput.xls')


    def writeGlobalStatistic(self):
        pass