package dev.chaarlottte.learn4k.obj

class Layer(private val numInputs: Int, private val numNodes: Int) {

    // Kotlin when 2d array
    private val weights: Array<FloatArray> = Array(this.numNodes) { FloatArray(this.numInputs) }
    private val biases: FloatArray = FloatArray(this.numNodes)
    var nodes: FloatArray = FloatArray(this.numNodes)

    fun forward(input: FloatArray) {
        this.nodes = FloatArray(this.numNodes)
        for(i in 0 until this.numNodes) {
            // Calculate the sum of the weights times the inputs
            for(j in 0 until this.numInputs)
                this.nodes[i] += this.weights[i][j] * input[j]

            // Add the layer's bias
            this.nodes[i] += this.biases[i]
        }
    }

    fun activate() {
        for(i in 0 until this.numNodes)
            if(this.nodes[i] < 0)
                this.nodes[i] = 0.0f
    }
}