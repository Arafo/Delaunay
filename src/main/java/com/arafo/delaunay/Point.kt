package com.arafo.delaunay

/**
 * Created by Rafa on 14/09/2017.
 */

data class Point(val x: Double, val y: Double, val z: Double) {

    operator fun unaryMinus(): Point = Point(-x, -y, -z)
    operator fun plus(other: Double): Point = Point(x + other, y + other, z + other)
    operator fun minus(other: Double): Point = Point(x - other, y - other, z - other)
    operator fun times(other: Double): Point = Point(x * other, y * other, z * other)
    operator fun div(other: Double): Point = Point(x / other, y / other, z / other)

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Point): Point = Point(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Point): Point = Point(x / other.x, y / other.y, z / other.z)
}