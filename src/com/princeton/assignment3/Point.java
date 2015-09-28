
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {
        // draws this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        // string representation
        return this.x + " " + this.y;
    }

    public int compareTo(Point that) {
        // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y > that.y)
            return 1;
        else if (this.y == that.y) {
            if (this.x > that.x)
                return 1;
            else if (this.x == that.x)
                return 0;
            else
                return -1;
        } else
            return -1;
    }

    public double slopeTo(Point that) {
        // the slope between this point and that point
        if (this.x == that.x) {
            if (this.y == that.y)
                return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            if(this.x==that.x) return Double.NEGATIVE_INFINITY;
            else {
            return 0;
            }
        }
        else {
            return (double) (that.y - this.y) / (double) (that.x - this.x);
        }
    }

    public Comparator<Point> slopeOrder() {
        // compare two points by slopes they make with this point
        class PointComparator implements Comparator<Point> {

            @Override
            public int compare(Point p1, Point p2) {
                // TODO Auto-generated method stub
                if (slopeTo(p1) < slopeTo(p2))
                    return -1;
                else if (slopeTo(p1) == slopeTo(p2))
                    return 0;
                else
                    return 1;
            }
        }
        return new PointComparator();
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(3, 4);
        Comparator<Point> c1 = p1.slopeOrder();
        System.out.println(c1.compare(new Point(5, 6), new Point(9, 18)));
    }
}
