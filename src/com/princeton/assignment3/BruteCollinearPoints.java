
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> list = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double slopej = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    double slopek = points[i].slopeTo(points[k]);
                    if (slopej == slopek) {
                        for (int m = k + 1; m < points.length; m++) {
                            double slopem = points[m].slopeTo(points[k]);
                            if (slopek == slopem) {
                                Point[] pointCp = new Point[4];
                                pointCp[0] = points[m];
                                pointCp[1] = points[k];
                                pointCp[2] = points[j];
                                pointCp[3] = points[i];
                                Arrays.sort(pointCp);
                                LineSegment seg = new LineSegment(pointCp[0],
                                        pointCp[3]);
                                list.add(seg);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
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
        BruteCollinearPoints res = new BruteCollinearPoints(req);
        for (LineSegment seg : res.segments()) {
            System.out.println(seg);
        }
    }
}
