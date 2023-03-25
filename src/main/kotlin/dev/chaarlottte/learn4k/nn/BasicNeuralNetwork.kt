package dev.chaarlottte.learn4k.nn

import dev.chaarlottte.learn4k.obj.Layer

class BasicNeuralNetwork(private var shape: IntArray = intArrayOf(2, 4, 4, 2)) {

    private lateinit var layers: Array<Layer>

    fun initialize() {
        // Properly create the layers according to the defined shape
        this.layers = Array(this.shape.size - 1) { i ->
            Layer(this.shape[i], this.shape[i + 1])
        }
    }

    fun neuralize(input: FloatArray): FloatArray {
        var index = 0
        var prevLayer: Layer? = null

        this.layers.forEach { layer ->
            when (index) {
                0 -> {
                    // If it's the first layer, there is not a previous one to work off of
                    layer.forward(input)
                    layer.activate()
                }
                this.layers.size - 1 -> {
                    // If it's the final layer, there is no need to run an activation function
                    layer.forward(prevLayer!!.nodes)
                }
                else -> {
                    // These layers are normies.
                    layer.forward(prevLayer!!.nodes)
                    layer.activate()
                }
            }

            prevLayer = layer
            index++
        }

        // Return the output layer's nodes
        return this.layers[this.layers.size - 1].nodes
    }

}