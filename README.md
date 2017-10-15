# Delaunay

A quick implementation of Delaunay triangulation

## Installation

```groovy
compile 'com.arafo:delaunay:1.0'
```

## Usage

**Java**

``` java

import com.arafo.delaunay.Delaunay;
import com.arafo.delaunay.Point;
import com.arafo.delaunay.Triangle;

    public void draw() {

        // A list of 3D points
        List<Point> points = new ArrayList<>();

        // Add points to the list
        ...

        try {
            
            // Delaunay triangulation
            List<Triangle> triangles = Delaunay.triangulate(points);

            for (Triangle triangle : triangles) {
                // Vertex 1 coordinates
                double v1x = triangle.getVertex1().getX();
                double v1y = triangle.getVertex1().getY();

                // Vertex 2 coordinates
                double v2x = triangle.getVertex2().getX();
                double v2y = triangle.getVertex2().getY();

                // Vertex 3 coordinates
                double v3x = triangle.getVertex3().getX();
                double v3y = triangle.getVertex3().getY();

                ...
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
```

**Kotlin**

``` kotlin

import com.arafo.delaunay.Delaunay
import com.arafo.delaunay.Point

    fun draw() {

        // A list of 3D points
        val points = mutableListOf<Point>()

        // Add points to the list
        ...

        try {
            // Delaunay triangulation
            val triangles = Delaunay.triangulate(points)

            for (triangle in triangles) {
                // Vertex 1 coordinates
                val v1x = triangle.vertex1.x
                val v1y = triangle.vertex1.y

                // Vertex 2 coordinates
                val v2x = triangle.vertex2.x
                val v2y = triangle.vertex2.y

                // Vertex 3 coordinates
                val v3x = triangle.vertex3.x
                val v3y = triangle.vertex3.y

                ...
            }

        } catch (e: Exception) {
            println(e.message)
        }
    }



```

## License

Delaunay is Copyright (c) 2017. It is free software, and may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE