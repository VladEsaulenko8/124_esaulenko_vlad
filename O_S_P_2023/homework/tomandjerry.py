tom_speed = float(input("Введите скорость Тома: "))
jerry_speed = float(input("Введите скорость Джерри: "))
distance = float(input("Введите расстояние между Томом и Джерри: "))

time_to_catch = distance / (tom_speed - jerry_speed)

if time_to_catch <= 0:
print("Ошибка! Скорость Тома должна быть больше скорости Джерри.")
elif time_to_catch > 0:
print(f"Том догонит Джерри через {time_to_catch} часов(а).")