package days

import Day

object Day6 : Day {
    override fun solve1(s: String): String {
        val mtx = s.trim().split("\n").map {
            it.trim().split(Regex("\\s+"))
        }

        println(mtx.map { it.indices })

        val indices = mtx[0].indices
        val numbers = mtx.subList(0, mtx.size - 1).map { it.map { it.toLong() } }
        val ops = mtx[mtx.size - 1]

        return indices.sumOf { i ->
            val pn = numbers.map { it[i] }
            pn.reduce { a, b ->
                if(ops[i] == "+") {
                    a + b
                } else {
                    a * b
                }
            }
        }.toString()
    }

    override fun solve2(s: String): String {
        val lines = s.split("\n").filterNot { it.isEmpty() }

        val numberLines = lines.subList(0, lines.size - 1)
        val ops = lines[lines.size - 1]

        val accOps = mutableListOf<Op>()
        ops.forEach { n ->
            if(n == ' ') {
                accOps.last().len += 1
            } else {
                accOps.add(Op(n, 0))
            }
        }

        accOps.last().len += 1

        var sum = 0L
        var offset = 0
        accOps.forEach { (op, len) ->

            val strNums = Array(len) { "" }
            numberLines.forEach { l ->
                (0 until len).forEach { j ->
                    strNums[j] += l[offset + j]
                }
            }

            val nums = strNums.map {it.trim().toLong() }
            println("$nums, $op")

            sum += nums.reduce { a, b ->
                if (op == '+') {
                    a + b
                } else {
                    a * b
                }
            }
            offset += len + 1
        }

        return sum.toString()
    }
}

data class Op(val op: Char, var len: Int)