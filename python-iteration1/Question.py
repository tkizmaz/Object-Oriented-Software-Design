class Question


    def __init__(self):
        self.question_text = " "
        self.question_right_answer = " "
        self.answerList = [ ]

    def getAnswers(self):
        return self.answerList
        
    def setQuestionText(self, questionText ):
        self.question_text = questionText

    def getQuestionText(self):
        return self.question_text

    def setQuestionRightAnswer(self, questionRightAnswer):
        self.question_right_answer = questionRightAnswer
    
    def getQuestionRightAnswer(self):
        return self.question_right_answer

    def AddAnswer(self, Answer):
        self.answerList.append(Answer)


    
