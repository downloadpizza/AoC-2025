package days

import Day
import utils.readMatrix

object Day3 : Day {
    override fun solve1(s: String): String {

        val matrix = readMatrix(s.trim()) { i -> i.toString().toInt() }

        return matrix.sumOf {
            val highest = it.take(it.size - 1).max()
            val startAt = it.indexOf(highest)
            val secondHighest = it.drop(startAt + 1).max()

            10 * highest + secondHighest
        }.toString()
    }

    override fun solve2(s: String): String {
        val matrix = readMatrix(s.trim()) { i -> i.toString().toLong() }

        return matrix.sumOf {
            var sum = 0L
            var sublist = it

            for(count in 11 downTo 0) {
                sum *= 10
                val highest = sublist.take(sublist.size - count).max()
                sum += highest
                val startAt = sublist.indexOf(highest)
                sublist = sublist.drop(startAt + 1)
            }

            sum
        }.toString()
    }
}