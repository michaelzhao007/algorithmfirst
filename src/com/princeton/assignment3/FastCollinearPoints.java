package com.princeton.assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastCollinearPoints {
    private List<LineSegment> list = new ArrayList<LineSegment>();
    private Map<Point, Point> map = new HashMap<Point, Point>();

    public FastCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, points[i].slopeOrder());
            double[] ptCp = new double[points.length - 1];

            Point[] pointCopy = new Point[points.length];
            int k = 0;

            for (int t = 0; t < points.length; t++) {
                pointCopy[t] = points[t];
                if (t != i) {
                    ptCp[k++] = points[i].slopeTo(points[t]);
                }
            }

            Arrays.sort(ptCp);

            Arrays.sort(pointCopy, pointCopy[i].slopeOrder());

            for (int m = 0; m < ptCp.length - 2; m++) {
                if (ptCp[m] == ptCp[m + 1] && ptCp[m + 1] == ptCp[m + 2]) {
                    Point[] res = new Point[4];
                    res[0] = pointCopy[m + 1];
                    res[1] = pointCopy[m + 2];
                    res[2] = pointCopy[m + 3];
                    res[3] = pointCopy[0];
                    Arrays.sort(res);
                    if (!(map.containsKey(res[0]) && map.get(res[0]) == res[3])
                            && !(map.containsKey(res[3]) && map.get(res[3]) == res[0])) {
                        map.put(res[0], res[3]);
                        list.add(new LineSegment(res[0], res[3]));
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return list.size();
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegs = new LineSegment[list.size()];
        for (int i = 0; i < list.size(); i++) {
            lineSegs[i] = list.get(i);
        }

        return lineSegs;
    }

    public static void main(String[] args) {
        Point p1 = new Point(10000, 0);
        Point p2 = new Point(0, 10000);
        Point p3 = new Point(3000, 7000);
        Point p4 = new Point(7000, 3000);
        Point p5 = new Point(20000, 21000);
        Point p6 = new Point(3000, 4000);
        Point p7 = new Point(14000, 15000);
        Point p8 = new Point(6000, 7000);
        Point[] req = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8 };
        FastCollinearPoints res = new FastCollinearPoints(req);
        for (LineSegment seg : res.segments()) {
            System.out.println(seg);
        }

    }
}