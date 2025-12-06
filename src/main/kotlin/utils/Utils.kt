package utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

typealias Matrix<T> = List<List<T>>

inline fun <T> readMatrix(input: String, convert: (Char) -> T): Matrix<T> =
    input.split("\n").map { it.toCharArray().map(convert) }

/**
 * Transforms a matrix by calling [transform] with each item and creating a matrix with the outputs
 */
inline fun <T, R> Matrix<T>.mapMatrix(transform: (T) -> R): Matrix<R> = this.map { it.map(transform) }

/**
 * Returns a view of the matrix within the ranges [x] and [y], which are inclusive-exclusive
 */
fun <T> Matrix<T>.subMatrix(x: IntRange, y: IntRange): Matrix<T> = this.subList(y.first, y.last).map { it.subList(x.first, x.last) }


fun <T> Matrix<T>.getIgnoreConstraints(x: Int, y: Int): T? {
    return if (y < 0 || y >= this.size)
        null
    else
        if (x < 0 || x >= this[y].size)
            null
        else
            this[y][x]
}

/**
 * Returns the highest element of a Matrix or throws if no elements exist
 */
fun <T : Comparable<T>> List<List<T>>.matrixMax(): T = this.mapNotNull { it.maxOrNull() }.maxOrNull()!!

/**
 * Returns the lowest element of a Matrix or throws if no elements exist
 */
fun <T : Comparable<T>> List<List<T>>.matrixMin(): T = this.mapNotNull { it.minOrNull() }.minOrNull()!!

infix fun Int.anyRange(end: Int) = if(this < end) this..end else this.downTo(end)

fun <T, R> Collection<T>.parMap(transform: (T) -> R): List<R> = runBlocking {
    val tasks = this@parMap.map { v ->
        async(Dispatchers.Default) { transform(v) }
    }

    tasks.awaitAll()
}

fun String.splitOnce(del: String): Pair<String, String> {
    val parts = this.split(del)
    return parts[0] to parts[1]
}

fun <T, R> Pair<T, T>.map(transform: (T) -> R): Pair<R, R> = transform(this.first) to transform(this.second)