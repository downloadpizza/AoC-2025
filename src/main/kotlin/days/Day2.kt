import utils.parMap

object Day2 : Day{
    override fun solve1(s: String): String {
        val strRanges = s.split(",")
        val ranges = strRanges.map {
            val parts = it.split("-").take(2)
            parts[0].toLong()..parts[1].toLong()
        }

        val sum = ranges.parMap(::findInvalidRanges).sum()

        return sum.toString()
    }

    override fun solve2(s: String): String {
        val strRanges = s.split(",")
        val ranges = strRanges.map {
            val parts = it.split("-").take(2)
            parts[0].toLong()..parts[1].toLong()
        }

        val sum = ranges.parMap(::findInvalidRanges2).sum()

        return sum.toString()
    }
}

fun findInvalidRanges(r: LongRange): Long {
    var sum: Long = 0
    for (i in r) {
        val s = i.toString()

        if(s.length % 2 != 0) continue

        if(s.take(s.length/2) == s.drop(s.length/2)) {
            sum += i
        }
    }

    return sum
}

fun findInvalidRanges2(r: LongRange): Long {
    var sum: Long = 0
    outer@for (i in r) {
        val s = i.toString()

        for(j in 1..s.length/2) {

            if(s.length % j != 0 || s.length/j < 2) continue

            var comp: String? = null
            val allEq = s.chunked(j).all { c ->
                if(comp == null) {
                    comp = c
                    true
                } else {
                    c == comp
                }
            }

            if(allEq) {
                sum += i
                continue@outer
            }
        }
    }

    return sum
}