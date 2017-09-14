package com.arafo.delaunay

/**
 * Created by Rafa on 14/09/2017.
 */

data class Edge(val startPoint: Point, val endPoint: Point) {

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Edge

        return (this.startPoint == other.startPoint && this.endPoint == other.endPoint) ||
                (this.startPoint == other.endPoint && this.endPoint == other.startPoint)
    }
}