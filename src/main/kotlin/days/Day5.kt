package days

import Day
import utils.map
import utils.splitOnce

object Day5 : Day {
    override fun solve1(s: String): String {
        val (p1, p2) = s.trim().splitOnce("\n\n")
        val ranges = p1.trim().split("\n").map {
            it.splitOnce("-").map(String::toLong)
        }

        val available = p2.split("\n").map { it.toLong() }

        return available.count { a -> ranges.any { a >= it.first && a <= it.second } }.toString()
    }

    override fun solve2(s: String): String {
        val (p1, _) = s.trim().splitOnce("\n\n")
        val ranges = p1.trim().split("\n").map {
            it.splitOnce("-").map(String::toLong)
        }.sortedBy {it.second}.sortedBy { it.first }

        val acc = mutableListOf<Pair<Long, Long>>()
        ranges.forEach { n ->
            val l = acc.lastOrNull()
            if(l != null) {
                if(l.second >= n.first) {
                    if(l.second < n.second)
                        acc[acc.size - 1] = l.first to n.second
                } else {
                    acc.add(n)
                }
            } else {
                acc.add(n)
            }
        }

        return acc.sumOf {
            (it.second - it.first + 1)
        }.toString()
    }
}