# Напишите программу, которая принимает текст (от 5 слов с пробелами)
# и выводит два слова: наиболее часто встречающееся в тексте и самое длинное.
# Если все слова встречаются по 1 разу (или часто встречающиеся получатся с
# одинаковой частотой) вывести первое из них.

from collections import Counter
import os

# code begins
clear = lambda: os.system('cls')
clear()

def most_frequent(lst):
    counter = 0
    num = lst[0]
    for el in lst:
        curr_frequency = lst.count(el)
        if(curr_frequency> counter):
            counter = curr_frequency
            num = el
    return num

def longest_word(lst):
    s = max(lst, key = len)
    return s

phrase = str(input("Write something (5 words): "))
lenght = len(phrase)
if lenght >= 5:
    lst = phrase.split(" ")
    # print(Counter(lst))
    print("\nThe most frequent word in this phrase: " + most_frequent(lst))
    print("The longest word is: " + longest_word(lst))
else:
    print("There is not enough words!")