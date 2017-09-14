package com.arafo.delaunay

/**
 * Created by Rafa on 14/09/2017.
 */

data class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point) {

    fun containsInCircumcircle(point: Point): Double {
        val ax = vertex1.x - point.x
        val ay = vertex1.y - point.y
        val bx = vertex2.x - point.x
        val by = vertex2.y - point.y
        val cx = vertex3.x - point.x
        val cy = vertex3.y - point.y

        val det_ab = ax * by - bx * ay
        val det_bc = bx * cy - cx * by
        val det_ca = cx * ay - ax * cy

        val a_squared = ax * ax + ay * ay
        val b_squared = bx * bx + by * by
        val c_squared = cx * cx + cy * cy

        return a_squared * det_bc + b_squared * det_ca + c_squared * det_ab
    }

    fun sharesVertexWith(triangle: Triangle): Boolean {
        if (this.vertex1.x == triangle.vertex1.x && this.vertex1.y == triangle.vertex1.y) return true
        if (this.vertex1.x == triangle.vertex2.x && this.vertex1.y == triangle.vertex2.y) return true
        if (this.vertex1.x == triangle.vertex3.x && this.vertex1.y == triangle.vertex3.y) return true

        if (this.vertex2.x == triangle.vertex1.x && this.vertex2.y == triangle.vertex1.y) return true
        if (this.vertex2.x == triangle.vertex2.x && this.vertex2.y == triangle.vertex2.y) return true
        if (this.vertex2.x == triangle.vertex3.x && this.vertex2.y == triangle.vertex3.y) return true

        if (this.vertex3.x == triangle.vertex1.x && this.vertex3.y == triangle.vertex1.y) return true
        if (this.vertex3.x == triangle.vertex2.x && this.vertex3.y == triangle.vertex2.y) return true
        if (this.vertex3.x == triangle.vertex3.x && this.vertex3.y == triangle.vertex3.y) return true

        return false
    }
}