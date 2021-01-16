from Poll import *
class QuizPoll(Poll):
    def __init__(self):
        super().__init__()
        self.__questionList = []
        self.__answerSheet = None

    def addQuestions(self, question):
        self.__questionList.append(question)

    def getQuestions(self):
        return self.__questionList

    def setAnswerSheetList(self,answerSheetList):
        for answerSheet in answerSheetList:
            eachAnswerSheetQuestions=[]
            for eachQuestion in answerSheet.getQuestionList():
                eachAnswerSheetQuestions.append(eachQuestion.getQuestionText())
            if(eachAnswerSheetQuestions == self.__questionList):
                self.__answerSheet = answerSheet
        self.setPollName(self.__answerSheet.getPollName())

    def getAnswerSheetList(self):
        return self.__answerSheet
