
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> list = new ArrayList<LineSegment>();
    private List<ArrayList<Point>> res = new ArrayList<ArrayList<Point>>();

    public FastCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            double[] ptCp = new double[points.length];

            Point[] pointCopy = new Point[points.length];

            for (int t = 0; t < points.length; t++) {
                pointCopy[t] = points[t];
                ptCp[t] = points[i].slopeTo(points[t]);
            }

            Arrays.sort(ptCp);
            Arrays.sort(pointCopy, pointCopy[i].slopeOrder());

            int h = 0;
            for (int m = 0; m < ptCp.length; m++) {
                ArrayList<Point> pList = new ArrayList<Point>();
                while ((m + h) < ptCp.length && ptCp[m] == ptCp[m + h]) {
                    pList.add(pointCopy[m + h]);
                    h++;
                }
                if (h < 3) {
                    h = 0;
                    pList.clear();
                }
                if (h >= 3) {
                    pList.add(pointCopy[m]);
                    pList.add(pointCopy[0]);
                    Collections.sort(pList);

                    if (res.size() == 0) {
                        ArrayList<Point> newList = new ArrayList<Point>();
                        newList.add(pList.get(0));
                        newList.add(pList.get(pList.size() - 1));
                        res.add(newList);
                    } else {
                        boolean flg = false;
                        for (int pp = 0; pp < res.size(); pp++) {
                            if ((res.get(pp).get(0).compareTo(pList.get(0)) == 0 && res
                                    .get(pp).get(1)
                                    .compareTo(pList.get(pList.size() - 1)) == 0)
                                    || (res.get(pp).get(1)
                                            .compareTo(pList.get(0)) == 0 && res
                                            .get(pp)
                                            .get(0)
                                            .compareTo(
                                                    pList.get(pList.size() - 1)) == 0)) {
                                flg = true;
                            }
                        }

                        if (!flg) {
                            ArrayList<Point> newList = new ArrayList<Point>();
                            newList.add(pList.get(0));
                            newList.add(pList.get(pList.size() - 1));
                            res.add(newList);
                        }

                    }

                }

                pList.clear();
                h = 0;
            }

        }
        for (List<Point> testList : res) {
            list.add(new LineSegment(testList.get(0), testList.get(1)));
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