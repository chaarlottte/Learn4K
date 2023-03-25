package dev.chaarlottte.learn4k.api

interface LearningAgent<T> {

    fun chooseAction(state: T): Int

}