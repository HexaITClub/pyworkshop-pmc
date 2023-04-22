#!/usr/bin/env python3

number = 45

while True:
    guess = int(input("Enter your guess: "))
    if guess == number:
        print("You guessed it correct!")
        break
    elif guess < number:
        dist = number - guess
        if dist <= 5:
            print("Incorrect. Try little higher buddy")
        else:
            print("Incorrect. Try entering higher number")
    elif guess > number:
        dist = guess - number
        if dist <= 5:
            print("Incorrect. Try little lower buddy")
        else:
            print("Incorrect. Try entering lower number")
