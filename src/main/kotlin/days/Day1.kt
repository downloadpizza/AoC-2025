package days

import Day
import kotlin.math.absoluteValue
import kotlin.math.sign

data class State(var dial: Int, var zeroClicks: Int)

object Day1 : Day{
    override fun solve1(s: String): String {
        return s
            .lineSequence()
            .map { (if (it[0] == 'R') 1 else -1) * it.substring(1).toInt() }
            .fold(initial = State(50, 0)) { (dial, zeroClicks), m ->
                State(
                    (dial + m).mod(100),
                    if(dial == 0) zeroClicks + 1 else zeroClicks
                )
            }
                .zeroClicks
                .toString()
    }

    override fun solve2(s: String): String {
        return s
            .lineSequence()
            .map { (if (it[0] == 'R') 1 else -1) * it.substring(1).toInt() }
            .fold(State(50, 0)) { state, m ->
                (0 until m.absoluteValue).fold(state) { (dial, zeroClicks), _ ->
                    State(
                        (dial + m.sign).mod(100),
                        if(dial == 0) zeroClicks + 1 else zeroClicks
                    )
                }
            }
            .zeroClicks
            .toString()
    }

}
