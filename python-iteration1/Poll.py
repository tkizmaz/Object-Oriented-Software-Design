import pandas
class Poll:

    def __init__(self):
        self.__pollName = ""
        self.__questionList = []
    
    def setPollName(self,pollName):
        self.__pollName = pollName

    def getPollName(self):
        return self.__pollName

    def addQuestions(self,question):
        self.__questionList.append(question)
    
    def getQuestions(self):
        return self.__questionList

    def getHello(self):
        print ("Helloo")