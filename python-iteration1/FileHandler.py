import xlrd
#from .Student import Student

class FileHandler(object):

    def __index__(self,studentList=[],pollList=[]):
        self.__studentList=studentList
        self.__pollList=pollList

    def setStudentList(self,studentList):
        self.__studentList=studentList
        print(self.__studentList)

    def setPollList(self,pollList):
        self.__pollList=pollList
        print(self.__pollList)

    def readPollFile(self,filename):
        import csv
        with open(filename, encoding='utf-8') as csvfile:  # Open the CSV file
            readCSV = csv.reader(csvfile, delimiter=',')
            for row in readCSV:                             # Read each row in the file
                if row[4] == "Are you attending this lecture?":       #
                    print(row)

                rowNumber = row[0]
                userName = row[1]
                userEmail = row[2]
                dateTime = row[3]

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