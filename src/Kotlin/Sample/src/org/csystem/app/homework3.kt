package csd

import org.csystem.util.readInt
import org.csystem.util.readString

fun main() {
    homework3menu()
}

fun homework3menu() {
    7
    while (true) {
        println()
        println("1. Extract Primes")
        println("2. Bouncy Ball")
        println("3. Custom Bouncy Ball")
        println("4. Show Goldbach Equations")
        println("Any other: Exit")
        when (readString("Select please")) {
            "1" -> extractPrimes(
                    readInt("Enter a number")
            )
            "2" -> bouncy(
                    readInt("Enter height"),
                    readInt("Enter width")
            )
            "3" -> bouncy(
                    readInt("Enter height"),
                    readInt("Enter width"),
                    readString("Enter Wall shape"),
                    readString("Enter Ball shape"),
            )
            "4" -> goldbach(
                    readInt("Enter a number")
            )
            else -> {
                print("Have a great day")
                break
            }
        }
    }
}

fun extractPrimes(num: Int) {
    var numTemp = num
    var x = 0
    while (numTemp != 1) {
        ++x
        while (numTemp % getPrime(x) == 0) {
            print("${getPrime(x)} ")
            numTemp /= getPrime(x)
        }
    }
}

fun bouncy(height: Int, width: Int) {
    var wall = "|"
    var ball = "*"
    bouncy(height, width, wall, ball)
}

fun bouncy(height: Int, width: Int, wall: String, ball: String) {
    var ballPosition: Int = 0
    var ballDirection: Boolean = true

    for (h in 1..height) {

        print(wall)

        if (!ballDirection) --ballPosition else ++ballPosition

        for (cursorPosition in 1..width)
            if (cursorPosition == ballPosition) print(ball) else print(" ")

        println(wall)

        if (ballDirection && ballPosition == width) ballDirection = !ballDirection
        if (!ballDirection && ballPosition == 1) ballDirection = !ballDirection
    }

}

fun goldbach(num: Int) {
    if (num % 2 != 0) {
        println("Function needs an even number")
        return
    }

    var order = 1
    var eq1 = getPrime(order)
    var eq2 = num - eq1

    while (eq1 <= num / 2) {

        if (isPrimeX(eq2)) println("$num = $eq1 + $eq2")

        eq1 = getPrime(++order)
        eq2 = num - eq1
    }
}

fun getPrime(n: Int) : Int
{
    var count = 0
    var value = 2

    while (true) {
        if (isPrimeX(value))
            ++count

        if (count == n)
            return value

        ++value
    }
}
