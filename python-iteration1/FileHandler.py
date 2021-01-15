import xlrd
import csv
#from .Student import Student

class FileHandler(object):

    def __init__(self):
        self.__studentList=[]
        self.__pollList=[]

    def setStudentList(self,studentList):
        self.__studentList.append(studentList)

    def setPollList(self,pollList):
        self.__pollList.append(pollList)

    def getStudentList(self):
        return self.__studentList

    def getPollList(self):
        return self.__pollList

    def readPollFile(self,filename):
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV:                             # Read each row in the file
                if row[4] == "Are you attending this lecture?":
                    print(row)

    def readStudentFile(self,filename):
        wb = xlrd.open_workbook(filename)
        sheet = wb.sheet_by_index(0)

        for row in range(13, sheet.nrows):
            if ((sheet.cell_value(row, 2)).isnumeric()):
                print(sheet.row_values(row))
                #student = Student(sheet.cell_value(row, 2), sheet.cell_value(row, 4), sheet.cell_value(row, 6), 'null')

    def writeAttendence(self):
        pass

    def writePollResult(self):
        pass

    def writeStatistic(self):
        pass

    def writeGlobalStatistic(self):
        pass



file=FileHandler()
file.readStudentFile("CES3063_Fall2020_rptSinifListesi.XLS")
file.readPollFile("CSE3063_20201124_Tue_zoom_PollReport.csv")