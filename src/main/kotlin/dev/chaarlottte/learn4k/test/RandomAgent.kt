package dev.chaarlottte.learn4k.test

import dev.chaarlottte.learn4k.api.LearningAgent
import kotlin.random.Random

class RandomAgent(private val numActions: Int) : LearningAgent<Int> {

    // Well, what did you expect?
    override fun chooseAction(state: Int): Int {
        return Random.nextInt(numActions)
    }
}
