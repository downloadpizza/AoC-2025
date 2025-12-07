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

        val indices = mutableMapOf(tach[0].indexOf(Field.Start) to 1L)

        tach.forEach { line ->
            val immInd = HashMap(indices)
            immInd.forEach { (index, count) ->
                if(count == 0L) {
                    return@forEach
                }
                when(line[index]) {
                    Field.Splitter -> {
                        indices[index - 1] = (indices[index - 1] ?: 0) + count
                        indices[index] = (indices[index] ?: 0) - count
                        indices[index + 1] = (indices[index + 1] ?: 0) + count

                    }
                    else -> {}
                }
            }
        }

        return indices.values.sum().toString()
    }
}