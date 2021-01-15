class Answer:
    def __init__(self, student, question):
        self.student=student
        self.question=question
        self.isTrue=False
    
    def setStudent(self,st):
        self.student=st
        
    def getStudent(self):
        return self.student
    
    def setQuestion(self,quest):
        self.question=quest

    def getQuestion(self):
        return self.question
    
    def checkIsTrue(self):
        if self.isTrue:
            return True
        else: 
            return False
    
    def getIstTure(self):
        return self.isTrue
    