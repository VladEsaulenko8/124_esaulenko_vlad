import random
# cleaner
import os

clear = lambda: os.system('cls')
clear()


# code begins
def transform_name(name):
    lst = list(name)
    cool_name = ""
    for el in lst:
        cool_name = cool_name + el + "☠"
    return cool_name
# -----------------------------------------------------

def game_counter(random_number):
    counter = 0
    num = int(input("Your answer: "))
    while (num != random_number):
        if num == random_number:
            print("Are you genious? Yes, you're right, " +
                "{0} \nThe number is {1}" +
                "\nThe attemps: {2}".format(name, random_number, counter))
            break
        elif num > random_number:
            print("Haha, fool! Number is lower!")
            counter += 1
            continue
        elif num < random_number:
            print("Haha, fool! Number is higher!")
            counter += 1
            continue
        else:
            print("..what did you say?..")
            continue
# -----------------------------------------------------

name = input("Call your name to begin the game: ")
print("Welcome, {0}!".format(name))
name = transform_name(name)
# -----------------------------------------------------

print("\nOkay, buddy, try to guess what the number?")
random_number = random.randint(0, 125)
game_counter(random_number)