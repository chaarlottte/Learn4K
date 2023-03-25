package dev.chaarlottte.learn4k.rl

import dev.chaarlottte.learn4k.api.LearningAgent
import kotlin.math.max
import kotlin.random.Random

class QLearningAgent(
    private val numActions: Int,
    private val numStates: Int,
    private val alpha: Double,
    private val gamma: Double,
    private val epsilon: Double
) : LearningAgent<Int> {

    // Initialize the Q-Table
    private val qTable = Array(this.numStates) { DoubleArray(this.numActions) { 0.0 } }

    override fun chooseAction(state: Int): Int {
        // Exploration
        if (Random.nextDouble() < this.epsilon)
            return Random.nextInt(this.numActions)

        // Exploitation
        return this.qTable[state].indices.maxByOrNull { this.qTable[state][it] } ?: 0
    }

    fun learn(state: Int, action: Int, reward: Double, nextState: Int) {
        // Do some magic
        val oldQ = this.qTable[state][action]
        val nextMaxQ = this.qTable[nextState].maxOrNull() ?: 0.0
        val newQ = oldQ + this.alpha * (reward + this.gamma * nextMaxQ - oldQ)
        this.qTable[state][action] = newQ
    }
}
