## Author: Tyler Mante
# Library for opening url and creating requests
from codecs import ignore_errors
from tkinter import Y
import urllib.request
 
# pretty-print python data structures
from pprint import pprint
 
# for parsing all the tables present on the website
from html_table_parser.parser import HTMLTableParser
 
# for converting the parsed data in a pandas dataframe
import pandas as pd

# Opens a website and reads its contents (HTTP Response Body)
def url_get_contents(url):
 
    # Opens a website and read its
    # binary contents (HTTP Response Body)
 
    # making request to the website
    req = urllib.request.Request(url=url)
    f = urllib.request.urlopen(req)
 
    #reading contents of the website
    return f.read()
 
# defining the html contents of a URL.
import sqlite3
import openpyxl
import xlsxwriter

# dictionary to pull teams from user input. Web URL requires team abbreviation, user may not know exact abbreviation
# NOTE: Several teams may result in 404 Error due to team not existing at requested year!
Abrev = {
    "diamondbacks" : "ARI",
    "braves": "ATL",
    "orioles" :"BAL",
    "red sox" :"BOS", 
    "cubs" : "CHC" ,
    "white sox": "CHW",
    "reds": "CIN",
    "guardians" : "CLE",
    "tigers" : "DET",
    "astros" : "HOU",
    "royals" : "KC", 
    "angels" : "LAA",
    "dodgers" : "LAD",
    "marlins" : "MIA",
    "brewers" : "MIL",
    "twins" : "MIN", 
    "mets" : "NYM", 
    "yankees" : "NYY", 
    "athletics" : "OAK",
    "phillies" : "PHI", 
    "pirates" : "PIT", 
    "padres" : "SD", 
    "mariners" : "SEA",
    "giants" : "SF",
    "cardinals": "STL",
    "rays" : "TB",
    "rangers" : "TEX",
    "blue jays" : "TOR",
    "nationals" : "WSN"}

# allocating an array with 30 locations in case user wants to sample all MLB teams
teams = [29] 

# Prompting user for number of teams and team names
numTeams = int(input("How many teams will you be sampling data from?:\n"))
s = input("Enter the first team name you'd like data tables on: (Ex: For Cleveland Guardians, type 'Guardians'):")

# Insert the abbreviation of team name inputted (s) as first element of array
teams[0] = Abrev[s]

# Can uncomment for console output :
##print(teams[0])
i = 1

# Looping throught number of teams and filling array  
while(i < numTeams):
    tempStr = input("Enter the team name of team " + str(i+1) + ":\n")
    abrevStr = tempStr.lower()
    # Can uncomment for console output
    ## print(abrevStr)
    teams.append(Abrev.get(abrevStr))
    # Can uncomment for console output
    ##print(teams[i])
    i += 1

## Get range of years from user 
minYear = int(input("Enter the first year of data range (form xxxx):\n"))
maxYear = int(input("Enter the end year of data range (form xxxx):\n"))

## User input error accounting
if(minYear < 1880 or minYear > 2022 or maxYear < 1880 or maxYear > 2022 or minYear > maxYear):
    minYear = int(input("Range invalid. Please enter a valid first year: "))
    maxYear = int(input("End year: "))

# Loops to obtain each year in specified range for each team
for m in range(numTeams):
    # Start our year index with beginning of our requested range
    year = minYear
    while(year <= maxYear):
        web = ('https://www.baseball-reference.com/teams/'+ teams[m] + '/' + str(year) + '.shtml')
        # Can uncomment this to verify the printing of each web site
        ##print(web)
    
        # Decode binary
        xhtml = url_get_contents(web).decode('UTF-8')
 
        # Defining the HTMLTableParser object
        p = HTMLTableParser()
    
        # feeding the html contents in the
        # HTMLTableParser object
        p.feed(xhtml)

        # Defining our dataframe 

        df = pd.DataFrame(p.tables[0])
        col_names = df.iloc[0]

        # Since this program is utilizing the known reference site, I simply used the indexes known to convert values from the 
        # HTML table from objects to floats
        stat_idx = 3
        for y in range(25):
            df[stat_idx] = pd.to_numeric(df[stat_idx], errors='coerce')
            stat_idx += 1

        # Define column names and remove unnecessary first column from HTML 
        df.columns = col_names
        df = df.drop(df[df['Name'] == 'Name'].index)
        conn = sqlite3.connect('DataBaseName.db')

        # Necessary variable for to_sql function
        c = conn.cursor()
        df.to_sql(str(year) + teams[m], conn, if_exists='replace', index = False, index_label = None, chunksize = None, dtype = None, method = None)
        year += 1
        conn.commit()
        