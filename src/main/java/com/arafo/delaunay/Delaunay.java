package com.arafo.delaunay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafa on 14/09/2017.
 */

public class Delaunay {

    /**
     * Performs the Delaunay triangulation on a set of n vertices in O(n**2) time
     * @param triangulationPoints The points to triangulate.
     * @return A list of Delaunay-triangles.
     */
    public static List<Triangle> triangulate(List<Point> triangulationPoints) throws Exception {
        if ( triangulationPoints.size() < 3 ) {
            throw new Exception("Can not triangulate less than three vertices!");
        }

        // The triangle list
        List<Triangle> triangles = new ArrayList<>();

        // The "supertriangle" which encompasses all triangulation points.
        // This triangle initializes the algorithm and will be removed later.
        Triangle superTriangle = superTriangle(triangulationPoints);
        triangles.add(superTriangle);

        // Include each point one at a time into the existing triangulation
        for (int i = 0; i < triangulationPoints.size(); i++) {
            // Initialize the edge buffer.
            List<Edge> EdgeBuffer = new ArrayList<>();

            // If the actual vertex lies inside the circumcircle, then the three edges of the
            // triangle are added to the edge buffer and the triangle is removed from list.
            for (int j = triangles.size() - 1; j >= 0; j--) {
                Triangle t = triangles.get(j);
                if (t.containsInCircumcircle(triangulationPoints.get(i)) > 0) {
                    EdgeBuffer.add(new Edge(t.getVertex1(), t.getVertex2()));
                    EdgeBuffer.add(new Edge(t.getVertex2(), t.getVertex3()));
                    EdgeBuffer.add(new Edge(t.getVertex3(), t.getVertex1()));
                    triangles.remove(j);
                }
            }

            // Remove duplicate edges. This leaves the convex hull of the edges.
            // The edges in this convex hull are oriented counterclockwise!
            for (int j = EdgeBuffer.size() - 2; j >= 0; j--) {
                for (int k = EdgeBuffer.size() - 1; k >= j + 1; k--) {
                    if (EdgeBuffer.get(j).equals(EdgeBuffer.get(k))) {
                        EdgeBuffer.remove(k);
                        EdgeBuffer.remove(j);
                        k--;
                        continue;
                    }
                }
            }

            // Generate new counterclockwise oriented triangles filling the "hole" in
            // the existing triangulation. These triangles all share the actual vertex.
            for (int j = 0; j < EdgeBuffer.size(); j++) {
                triangles.add(new Triangle(
                        EdgeBuffer.get(j).getStartPoint(),
                        EdgeBuffer.get(j).getEndPoint(),
                        triangulationPoints.get(i)));
            }
        }

        // We don't want the supertriangle in the triangulation, so
        // remove all triangles sharing a vertex with the supertriangle.
        for (int i = triangles.size() - 1; i >= 0; i--) {
            if (triangles.get(i).sharesVertexWith(superTriangle)) {
                triangles.remove(i);
            }
        }

        // Return the triangles
        return triangles;
    }

    /**
     * Returns a triangle that encompasses all triangulation points.
     * @param triangulationPoints A list of triangulation points.
     * @return Returns a triangle that encompasses all triangulation points.
     */
    private static Triangle superTriangle(List<Point> triangulationPoints) {
        double M = triangulationPoints.get(0).getX();

        // Get the extremal x and y coordinates
        for (int i = 1; i < triangulationPoints.size(); i++) {
            double xAbs = Math.abs(triangulationPoints.get(i).getX());
            double yAbs = Math.abs(triangulationPoints.get(i).getY());
            if ( xAbs > M ) M = xAbs;
            if ( yAbs > M ) M = yAbs;
        }

        // Make a triangle
        Point sp1 = new Point(10 * M, 0, 0);
        Point sp2 = new Point(0, 10 * M, 0);
        Point sp3 = new Point(-10 * M, -10 * M, 0);

        return new Triangle(sp1, sp2, sp3);
    }
}