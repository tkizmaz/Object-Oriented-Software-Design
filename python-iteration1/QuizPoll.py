from Poll import *
class QuizPoll(Poll):
    def __init__(self):
        super().__init__()

    def addQuestions(self, question):
        self.__questionList.append(question)

    def getQuestions(self):
        return self.__questionList
