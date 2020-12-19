//v2: when kontrol deyimi kullanıldı

package csd

import org.csystem.util.readInt
import org.csystem.util.readLong

fun main()
{
    homework2v2menu()
}

fun homework2v2menu()
{
    while (true) {
        println("1. Draw Baklava Pattern")
        println("2. Convert Seconds to Years Months Days Hours Minutes Seconds")
        println("3. Exit")
        when(readLine()){
            "1" -> drawPattern(readInt("Please input first number"))
            "2" -> displayDuration(readLong("Please input first number"))
            else -> {
                print("Have nice day")
                break
            }
        }
    }
}

fun showQty(label: String, value: Long) : String = when
{
        value > 1.toLong() -> "$value ${label}s"
        value == 1.toLong() -> "$value $label"
        else -> ""
}

fun drawPattern(n : Int) {

    var fullheight = (n * 2) - 2
    var reachPositionToPutStar = n - 1
    var numberOfStarsToPut = 0

    for(x in 0..fullheight)
    {
        when {
            x < n -> {
                --reachPositionToPutStar
                numberOfStarsToPut = 2 * x
            }
            x >= n -> {
                ++reachPositionToPutStar
                numberOfStarsToPut -= 2
            }
        }

        for(y in 0..reachPositionToPutStar)
            print(" ")

        for(s in 0..numberOfStarsToPut)
            print("*")

        println()
    }
}

fun displayDuration (duration : Long)
{
    var days : Long = 0
    var months : Long = 0
    var years : Long = 0
    var hour : Long = 0
    var minute : Long = 0
    var second : Long = 0

    if(duration < 86400)
        second = duration
    else
    {
        days = duration.div(86400)
        println(days)
        second = duration - (days *  86400)
    }

    if(days >= 365)
    {
        years = days.div(365)
        days -= (years * 365)
    }

    if(days >= 30)
    {
        months = days.div(30)
        days -= (months * 30)
    }

    if(second >= 3600)
    {
        hour = second.div(3600)
        second -= (hour * 3600)
    }

    if(second >= 60)
    {
        minute = second.div(60)
        second -= (minute * 60)
    }

    // showQty moved to v2

    println("${showQty("year", years)} ${showQty("month", months)} ${showQty("day", days)} " +
            "${showQty("hour", hour)} ${showQty("minute", minute)} ${showQty("second", second)}")
}
