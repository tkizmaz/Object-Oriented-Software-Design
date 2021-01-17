class Answer:
    def __init__(self):
        self.__answer=[]
    
    def addAnswer(self,answer):
        self.__answer.append(answer)
    
    def getAnswers(self):
        return self.__answer

    