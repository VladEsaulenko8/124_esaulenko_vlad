# никнеймы есть в его секретном списке.
# К слову, вот этот список: Мавпродош, Лорнектиф, Древерол, Фиригарпиг, Клодобродыч.
# По мере увеличения круга знакомых Максим, естественно, дополнит данный список.
# Ваша задача такова: создайте, который будет спрашивать у пользователя его ник и
# либо пускать на сайт (выведется сообщение «Ты – свой. Приветствую, любезный {НИК_ПОСЕТИТЕЛЯ}!»),
# либо нет (в этом случае будет такой текст: «Тут ничего нет. Еще есть вопросы?».
# Для проверки прозвищ посетителей используйте встроенную функцию input().

import os

# code begins
clear = lambda: os.system('cls')
clear()

print("Aloha, user!\n" + "I hope you are here not because of an accident"
      + ", it is a private website.")
print("\nDo I know you? \n 1. Yes \n 2. No")
number = int(input("Choose number: "))
match number:
    case 1:
        print("\nHm..Let's try!")
    case 2:
        print("\nSorry, you must leave this website.")
    case _:
        print("\nNumber not found")

if number == 1:
    friends = ["Mavprodosh", "Lornectif", "Dreverol", "Firigarpig", "Klobrodych",
               "Viola", "Max"]
    nickname = str(input("Write your name: "))
    if nickname not in friends:
        print("\nPRIVATE INFORMATION! You MUST leave this website!")
    else:
        print("\nI was waiting for you, " + nickname + "!")