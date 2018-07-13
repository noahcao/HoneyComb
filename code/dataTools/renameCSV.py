#-*- coding:utf-8 -*-
import os
import csv

def countline(filename):
    f = open(filename, 'r')
    reader = csv.reader(f)
    line = 0
    for item in reader:
        line += 1
    return line

def renamepathcsv(path):
    filedir = os.listdir(path)
    for filename in filedir:
    begin = int(filename.split('_')[0])
    file_path = path + filename
    lines = countline(file_path)
    end = begin + lines
    newfilename = "%d_%d.csv" % (begin,end)
    newfilepath = path + newfilename
    os.rename(file_path, newfilepath)
    print(file_path, begin, lines)

def 

if __name__=='__main__':
    path = './data'
    