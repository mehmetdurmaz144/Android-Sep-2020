package csd

import org.csystem.util.readInt
import org.csystem.util.readString

fun main() {
    homework4menu()
}

fun homework4menu() {
    while (true) {
        println()
        println("1. Seven Conditions")
        println("2. Extreme Prime")
        println("Any other: Exit")
        when (readString("Select please")) {
            "1" -> sevenConditionsInRange(
                    readInt("Enter first number"),
                    readInt("Enter last number")
            )
            "2" -> isPrimeXInRange(
                    readInt("Enter first number"),
                    readInt("Enter last number")
            )
            else ->  {
                print("Have a great day")
                break
            }
        }
    }
}


fun sevenConditionsInRange(num1: Int, num2: Int)
{
    for(x in num1..num2)
        if(sevenConditions(x))
            println("$x is complying with conditions")
}

fun isPrimeXInRange(num1: Int, num2: Int)
{
    for(x in num1..num2)
        if(isPrimeX(x))
            println("$x is a PrimeX number")
}

fun sevenConditions(num : Int) : Boolean
{
    fun condition1(num : Int) = getReversed(num) > num
    fun condition2(num : Int) = isPrimeX(num)
    fun condition3(revNum : Int) = isPrimeX(revNum)
    fun condition4(num : Int) = isPrimeX(getFirstN(num, 2))
    fun condition5(num : Int) = isPrimeX(getLastN(num, 2))
    fun condition6(revNum : Int) = isPrimeX(getFirstN(revNum, 2))
    fun condition7(revNum : Int) = isPrimeX(getLastN(revNum, 2))

    var revNum = getReversed(num)

    if(!condition1(num)) return false
    if(!condition2(num)) return false
    if(!condition3(revNum)) return false
    if(!condition4(num)) return false
    if(!condition5(num)) return false
    if(!condition6(revNum)) return false
    if(!condition7(revNum)) return false

    return true
}

fun isPrimeX(num : Int) : Boolean
{
    var number = num

    if(!isPrimeX(number))
        return false

    while(getDigitCount(number) != 1)
    {
        number = sumDigits(number)
        if(!isPrimeX(number)) return false
    }
    return isPrimeX(number)
}

fun sumDigits(num : Int) : Int
{
    var number = num
    var result = 0
    while(number != 0) {
        result += getLastN(number, 1)
        number /= 10
    }

    return result
}

fun getLastN(num: Int, N: Int) : Int =  getReversed(getFirstN(getReversed(num), N))

fun getFirstN(num: Int, N: Int) : Int
{
    var digitCount = getDigitCount(num)

    if(N >= digitCount)
        return num

    return num / pow(10, digitCount-N)
}

fun pow(a: Int, b: Int) : Int
{
    var result = 1

    for (i in 1..b)
        result *= a

    return result
}

fun getReversed(num: Int) : Int {

    var result = 0
    var number = num

    while(number != 0) {
        result = result * 10 + number % 10
        number /= 10
    }
    return result
}

fun getDigitCount(num : Int) : Int {

    var count = 0
    var number = num

    while(number != 0){
        ++count
        number /= 10
    }

    return count
}

