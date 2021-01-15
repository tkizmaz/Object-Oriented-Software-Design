import pandas
class Poll:

    def __init__(self):
        self.__pollName = ""
    
    def setPollName(self,pollName):
        self.__pollName = pollName

    def getPollName(self):
        return self.__pollName

