from Poll import *
from FileHandler import *
from xlwt import Workbook
import matplotlib.pyplot as plt
import numpy as np

class QuizPoll(Poll):
    def __init__(self):
        super().__init__()
        self.__questionList = []
        self.__answerSheet = None
        self.__quizStudents=[]
    def addQuestions(self, question):
        self.__questionList=question

    def getQuestions(self):
        return self.__questionList

    def setQuizStudents(self,student):
        self.__quizStudents.append(student)
    
    def getQuizStudents(self):
        return self.__quizStudents

    def setAnswerSheetList(self,answerSheetList):
        self.__answerSheet = answerSheetList

    def getAnswerSheetList(self):
        return self.__answerSheet


    def calculatePoints(self):
        questionAnswers={}
        for i in self.getAnswerSheetList().getQuestionList():
            questionAnswers[i.getQuestionText()] = i.getQuestionRightAnswer()
        for i in self.getQuizStudents():
            print(i)
            for eq in i.getQuestionList():
                questionText=eq.getQuestionText()
                print(questionText)
                print(questionAnswers)
                for ea in eq.getAnswers():
                    if (len(ea.getAnswers()) == 1 and (ea.getAnswers()[0]) == questionAnswers[questionText][0]):
                        eq.setIsTrue(True)
                    elif (len(ea.getAnswers()) == 1 and (ea.getAnswers()[0]) != questionAnswers[questionText][0]):
                        eq.setIsTrue(False)
                    else:
                        eq.setIsTrue(False)
    
    def getReportForQuestions(self):
        questionAnswers={}
        count=1
        for i in self.getAnswerSheetList().getQuestionList():
            questionAnswers[i.getQuestionText()] = count
            count+=1

        print (questionAnswers)
        wb = Workbook()
        sheet1 = wb.add_sheet('Sheet 1')
        cols=[" "]
        for i in range(len(self.__answerSheet.getQuestionList())):
            cols.append("Q"+ (str(i+1)))

        cols.append("Number of Questions")
        cols.append("Success Rate")
        cols.append("Success Percentage")

        for i in range(1,len(self.__quizStudents)+1):
            sheet1.write(i,0,(self.__quizStudents[i-1].getStudentName()+" "+self.__quizStudents[i-1].getStudentSurname() ))
        
        for i in range(0,len(cols)):
            sheet1.write(0,i,cols[i])

        for i in range(0,len(self.__quizStudents)):
            truecount=0
            falsecount=0
            for eachQuestion in range(0,len(self.__quizStudents[i].getQuestionList())):
                truefalse=0
                if self.__quizStudents[i].getQuestionList()[eachQuestion].getIsTrue() == True:
                    truefalse=1
                    truecount+=1
                else:
                    truefalse=0
                    falsecount+=1
                sheet1.write(i+1, questionAnswers[self.__quizStudents[i].getQuestionList()[eachQuestion].getQuestionText()], truefalse)
            sheet1.write(i+1,12, str(count-1)+"Questions " + str(truecount) + " true")
            sheet1.write(i+1,13,str(truecount/(count-1)*100))
        sheet1.write(1,11,len(cols)-4)

        wb.save(self.getPollName()+".xls")            
