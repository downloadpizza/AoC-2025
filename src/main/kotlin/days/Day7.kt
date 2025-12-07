package days

import Day
import utils.readMatrix

enum class Field {
    Start,
    Empty,
    Splitter
}

object Day7 : Day {
    override fun solve1(s: String): String {
        val tach = readMatrix(s.trim()) {
            return@readMatrix when(it) {
                '.' -> Field.Empty
                'S' -> Field.Start
                '^' -> Field.Splitter
                else -> error("aaa")
            }
        }

        var indices = setOf<Int>(tach[0].indexOf(Field.Start))

        var splitCount = 0
        tach.forEach { line ->
            indices = indices.flatMap {
                when(line[it]) {
                    Field.Splitter -> {
                        splitCount += 1
                        listOf(it - 1, it + 1)
                    }
                    else -> listOf(it)
                }
            }.toSet()
        }

        return splitCount.toString()
    }

    override fun solve2(s: String): String {
        val tach = readMatrix(s.trim()) {
            return@readMatrix when(it) {
                '.' -> Field.Empty
                'S' -> Field.Start
                '^' -> Field.Splitter
                else -> error("aaa")
            }
        }

        val indices = Array(tach[0].size) { 0L }
        indices[tach[0].indexOf(Field.Start)] = 1L
        println(indices.joinToString(", "))

        tach.forEach { line ->
            val immInd = indices.clone()
            immInd.forEachIndexed { idx, count ->
                if(count == 0L) {
                    return@forEachIndexed
                }
                if (line[idx] == Field.Splitter) {
                    indices[idx - 1] += count
                    indices[idx] -= count
                    indices[idx + 1] += count
                }
            }
        }

        return indices.sum().toString()
    }
}
// 16937871060075