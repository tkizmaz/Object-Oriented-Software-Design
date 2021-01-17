from Poll import *
from FileHandler import *
from xlwt import Workbook
class QuizPoll(Poll):
    def __init__(self):
        super().__init__()
        self.__questionList = []
        self.__answerSheet = None
        self.__quizStudents=[]
    def addQuestions(self, question):
        self.__questionList.append(question)

    def getQuestions(self):
        return self.__questionList

    def setQuizStudents(self,student):
        self.__quizStudents.append(student)
    
    def getQuizStudents(self):
        return self.__quizStudents

    def setAnswerSheetList(self,answerSheetList):
        questionListInPoll=[]
        for eachQ in self.__questionList:
            questionListInPoll.append(eachQ.getQuestionText())
        for answerSheet in answerSheetList:
            eachAnswerSheetQuestions=[]
            for eachQuestion in answerSheet.getQuestionList():
                eachAnswerSheetQuestions.append(eachQuestion.getQuestionText())
            if(eachAnswerSheetQuestions == questionListInPoll):
                self.__answerSheet = answerSheet
        self.setPollName(self.__answerSheet.getPollName())

    def getAnswerSheetList(self):
        return self.__answerSheet

    def differantiateStudents(self,students):
        questionListInPoll=[]
        for eachQ in self.__questionList:
            questionListInPoll.append(eachQ.getQuestionText())
        for i in students:
            eachStudentQuestions=[]
            for eachQuestion in i.getQuestionList():
                eachStudentQuestions.append(eachQuestion.getQuestionText())
            if(eachStudentQuestions == questionListInPoll):
                self.setQuizStudents(i)

    def calculatePoints(self):
        questionAnswers={}
        for i in self.getAnswerSheetList().getQuestionList():
            questionAnswers[i.getQuestionText()] = i.getQuestionRightAnswer()
        for i in self.getQuizStudents():
            for eq in i.getQuestionList():
                questionText=eq.getQuestionText()
                for ea in eq.getAnswers():
                    if (len(ea.getAnswers()) == 1 and (ea.getAnswers()[0]) == questionAnswers[questionText][0]):
                        eq.setIsTrue(True)
                    elif (len(ea.getAnswers()) == 1 and (ea.getAnswers()[0]) != questionAnswers[questionText][0]):
                        eq.setIsTrue(False)
                    else:
                        eq.setIsTrue(False)
    
    def getReportForQuestions(self):

        wb = Workbook()
        sheet1 = wb.add_sheet('Sheet 1')
        cols=[" "]
        for i in range(len(self.__answerSheet.getQuestionList())):
            cols.append("Q"+ (str(i+1)))
        print (cols)

        
                   
