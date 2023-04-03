

import os

# code begins
clear = lambda: os.system('cls')
clear()

def follow_netflix(x,y,z,cost):
    print("\nSubscription costs:", cost)
    if (x+y >= cost):
        print("Alice and Bob have",x+y,"so they buy it." )
    elif (x+z >= cost):
        print("Alice and Charlie have",x+z,"so they buy it.")
    elif (y+z >= cost):
        print("Bob and Charlie have",y+z,"so they buy it.")
    else:

        print("No one can buy it! Their sum is", x+y+z)

x = int(input("Alice's piece: "))
y = int(input("Bob's piece: "))
z = int(input("Charlie's piece: "))
cost = 100

follow_netflix(x,y,z,cost)