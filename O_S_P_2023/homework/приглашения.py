# Список гостей: если бы вы могли пригласить кого угодно (из живых или умерших)
# на обед, то кого бы вы пригласили? Создайте список, включающий минимум трех людей,
# которых вам хотелось бы пригласить на обед . Затем используйте этот список для вывода
# пригласительного сообщения каждому участнику

import os

# code begins
clear = lambda: os.system('cls')
clear()

l1 = ["Vainona Raider", "Johny Clo", "Barbara Gordon"]


def invitings(l1):
    for el in l1:
        print("Dear " + el + ",\nI want to invite you in my party tonight!\n")


invitings(l1)