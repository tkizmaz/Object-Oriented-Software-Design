class QuizPoll(Poll):
    def __init__(self, pollName,questionList):
        super().__init__(pollName,questionList)

    def setPollName(self,pollName):
        self.__pollName=pollName

    def addQuestions(self, question):
        self.__questionList.append(question)

    def getQuestions(self):
        return self.__questionList
