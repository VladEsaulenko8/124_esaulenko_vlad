import requests as r
import urllib.parse
from bs4 import BeautifulSoup

def month_name(num,helper):
    if helper==0:
        ru = ['январе', 'феврале', 'марте', 'апреле', 'мае', 'июне', 'июле', 'августе', 'сентябре',
              'октябре', 'ноябре', 'декабре']
        return ru[num - 1]
    else:
        ru1 = ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь',
              'Октябрь', 'Ноябрь', 'Декабрь']
        return ru1[num - 1]

def season_events(number_of_month, year):
    month_text= month_name(number_of_month,0)
    my_file = open("wiki.txt", "w")
    data = parse_wiki(number_of_month, year)
    my_file.write("Вы родились в "+ month_text + " в " + str(year) + " году.\n"+"\n"+ str(data))
    my_file.close()

def parse_wiki(month,year):
    link="https://ru.wikipedia.org/wiki/"+ urllib.parse.quote("Категория:"+ month_name(month,1) +"_" +str(year) +"_года")
    page = r.get(link)
    page = page.text
    parsed = BeautifulSoup(page,'html.parser')
    parsed = parsed.find("div", class_="mw-category mw-category-columns")
    parsed_for_list= parsed.find_all("a",limit=6)
    parsed_list =[x.text for x in parsed_for_list]
    if month_name(month,1) in parsed_list[0]:
        parsed_list.pop(0)
    else:
        parsed_list.pop(5)
    final_string =''

    for x in parsed_list[::-1]:
        final_string=str(x)+ '\n' +final_string

    return final_string


season_events(2,2004)
