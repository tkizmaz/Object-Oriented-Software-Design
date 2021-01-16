class Question(object):

    def __init__(self):
        self.__question_text = ""
        self.__question_right_answer = []
        self.__answerList = []  ## For the students

    def getAnswers(self):
        return self.__answerList
        
    def setQuestionText(self, questionText ):
        self.__question_text = questionText

    def getQuestionText(self):
        return self.__question_text

    def setQuestionRightAnswer(self, questionRightAnswer):
        self.__question_right_answer.append(questionRightAnswer)
    
    def getQuestionRightAnswer(self):
        return self.__question_right_answer

    def AddAnswer(self, Answer):
        self.__answerList.append(Answer)


    
