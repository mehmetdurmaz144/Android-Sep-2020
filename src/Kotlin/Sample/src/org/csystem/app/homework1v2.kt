//v2: when kontrol deyimi kullanıldı

package csd

import org.csystem.util.readInt

fun main() {
    homework1v2menu()
}

fun homework1v2menu()
{
    while (true) {
        println("1. Sort numbers")
        println("2. Find polarity")
        println("3. Exit")
        when(readLine()){
            "1" -> numberRelation(
                    readInt("Please input first number"),
                    readInt("Please input second number"),
                    readInt("Please input third number")
            )
            "2" -> println("Response of function is: ${signum(readInt("Please input a number to evaluate"))}")
            else -> {
                print("Have nice day")
                break
            }
        }
    }
}

fun signum(num: Int): Int = when {
    num > 0 ->  1
    num < 0 ->  -1
    else -> 0
}

fun mid(num1: Int, num2: Int, num3: Int): Int {
    if ((num2 <= num1 && num1 <= num3) || (num3 <= num1 && num1 <= num2)) return num1
    if ((num1 <= num2 && num2 <= num3) || (num3 <= num2 && num2 <= num1)) return num2
    if ((num2 <= num3 && num3 <= num1) || (num1 <= num3 && num3 <= num2)) return num3
    return 0
}

fun numberRelation(num1: Int, num2: Int, num3: Int) {

    val midNum = mid(num1, num2, num3)

    fun max(midNum: Int) = when {
        num1 > midNum -> num1
        num2 > midNum -> num2
        num3 > midNum -> num3
        else -> midNum
    }

    fun min(midNum: Int) = when {
        num1 < midNum -> num1
        num2 < midNum -> num2
        num3 < midNum -> num3
        else -> midNum
    }

    fun printCompDesc(a: Int, b: Int): String {
        return if (a == b)
            "= $b"
        else
            "> $b"
    }

    fun printCompAsc(a: Int, b: Int): String {
        return if (a == b)
            "= $b"
        else
            "< $b"
    }

    println("${max(midNum)} ${printCompDesc(max(midNum),midNum)} ${printCompDesc(midNum,min(midNum))}")
}
