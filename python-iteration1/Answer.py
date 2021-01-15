class Answer:
    def __init__(self, student, question):
        self.__student=student
        self.__question=question
        self.__isTrue=False
    
    def setStudent(self,st):
        self.__student=st
        
    def getStudent(self):
        return self.__student
    
    def setQuestion(self,quest):
        self.__question=quest

    def getQuestion(self):
        return self.__question
    
    def checkIsTrue(self):
        if self.__isTrue:
            return True
        else: 
            return False
    
    def getIstTure(self):
        return self.__isTrue
    