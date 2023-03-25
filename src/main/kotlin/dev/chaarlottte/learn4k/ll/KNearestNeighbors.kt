package dev.chaarlottte.learn4k.ll

import dev.chaarlottte.learn4k.api.LearningAgent
import kotlin.math.sqrt

class KNearestNeighbors(private val k: Int) : LearningAgent<List<Double>> {
    private var data: List<Pair<List<Double>, Int>> = listOf()

    fun train(data: List<Pair<List<Double>, Int>>) {
        this.data = data
    }

    override fun chooseAction(state: List<Double>): Int {
        val distances = this.data.map { it to euclideanDistance(it.first, state) }
            .sortedBy { it.second }
        val kNearestNeighbors = distances.take(k)
        val classCount = mutableMapOf<Int, Int>()

        kNearestNeighbors.forEach { (_, label) ->
            classCount[label.toInt()] = classCount.getOrDefault(label.toInt(), 0) + 1
        }

        return classCount.maxByOrNull { it.value }?.key ?: 0
    }

    private fun euclideanDistance(a: List<Double>, b: List<Double>): Double {
        return sqrt(a.zip(b).sumOf { (aElem, bElem) -> (aElem - bElem).let { it * it } })
    }
}
