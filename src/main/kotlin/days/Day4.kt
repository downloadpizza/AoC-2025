package days

import Day
import utils.Matrix
import utils.getIgnoreConstraints
import utils.readMatrix

const val Empty = false
const val Paper = true

fun checkLocation(matrix: Matrix<Boolean>, x: Int, y: Int): Boolean {
    val v = matrix.getIgnoreConstraints(x, y)
    if (v != Paper) return false

    var s = 0
    for (i in -1..1) {
        for (j in -1..1) {
            if(i == 0 && j == 0) continue

            if(matrix.getIgnoreConstraints(x + i, y + j) == Paper) {
                s += 1
                if(s >= 4) {
                    return false
                }
            }
        }
    }

    return true
}

object Day4 : Day {
    override fun solve1(s: String): String {
        val floor = readMatrix(s) { if(it == '@') Paper else Empty }

        var s = 0
        for(y in floor.indices) {
            for(x in floor[0].indices) {
                if(checkLocation(floor, x, y)) {
                    s += 1
                }
            }
        }

        return s.toString()
    }

    override fun solve2(s: String): String {
        val floor = readMatrix(s) { if(it == '@') Paper else Empty }.map { it.toMutableList() }

        var s = 0
        while(true) {
            val remove = mutableListOf<Pair<Int, Int>>()

            for (y in floor.indices) {
                for (x in floor[0].indices) {
                    if (checkLocation(floor, x, y)) {
                        remove += (x to y)
                    }
                }
            }

            if(remove.size == 0) break

            s += remove.size
            remove.forEach { (x, y) ->
                floor[y][x] = Empty
            }
        }

        return s.toString()
    }
}