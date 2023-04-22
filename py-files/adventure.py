#!/usr/bin/env python3

health = 100
drink_potions = 10

# level 1
print("*** Level 1 ***")
print("*** Health: ", health, " ***")

print("Welcome master!")
while True:
    action = input("What do you want to do? ")
    if action == "modify":
        print("*** Options: ***")
        modify_options = ["1) drink for drinking health potion"]
        for op in modify_options:
            print(op)

        action = input(">> ")

        if action == 'drink':
            if drink_potions <= 0:
                print("You don't have any drinking potions.")
                continue
            elif health == 100:
                print("Your health is already full")
                continue
            health = 100
            drink_potions -= 1
    elif action == 'start':
        break

actions = ['run', 'fight', 'walk', 'scare']
while True:
    action = input("[You encountered monster. What do you do?] ")
    if action in actions:
        if action == 'run':
            health -= 2
        elif action == 'fight':
            health -= 20
        elif action == 'walk':
            health -= 10
        elif action == 'scare':
            health = 0
        break

print("You chose to", action)
print("*** Health: ", max(health, 0), " ***")

if health <= 0:
    print("You died!")
