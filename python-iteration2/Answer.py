class Answer:
    def __init__(self):
        self.__answer=[]
        self.__student=""
    
    def addAnswer(self,answer):
        self.__answer.append(answer)
    
    def getAnswers(self):
        return self.__answer

    def setStudent(self,student):
        self.__student=student

    def getStudent(self):
        return  self.__student

    