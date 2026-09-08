import java.util.Arrays;

public class ClosestPairSolver {
    private long recursionDepth = 0;
    private long comparisons = 0;

    public double closestPair(Point[] points) {
        this.recursionDepth = 0;
        this.comparisons = 0;

        Point[] pointsByX = Arrays.copyOf(points, points.length);
        Point[] pointsByY = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsByX, (p1, p2) -> Double.compare(p1.x, p2.x));
        Arrays.sort(pointsByY, (p1, p2) -> Double.compare(p1.y, p2.y));

        return closestPairRecursive(pointsByX, pointsByY, 0, pointsByX.length - 1, 0);
    }

    private double closestPairRecursive(Point[] pointsByX, Point[] pointsByY, int left, int right, int depth) {
        this.recursionDepth = Math.max(this.recursionDepth, depth);

        if (right - left < 0) {
            return Double.POSITIVE_INFINITY;
        }
        if (right - left == 0) {
            return Double.POSITIVE_INFINITY;
        }
        if (right - left == 1) {
            this.comparisons++;
            return pointsByX[left].distanceTo(pointsByX[right]);
        }
        if (right - left == 2) {
            this.comparisons += 3;
            double d1 = pointsByX[left].distanceTo(pointsByX[left + 1]);
            double d2 = pointsByX[left].distanceTo(pointsByX[left + 2]);
            double d3 = pointsByX[left + 1].distanceTo(pointsByX[left + 2]);
            return Math.min(d1, Math.min(d2, d3));
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pointsByX[mid];

        double dl = closestPairRecursive(pointsByX, pointsByY, left, mid, depth + 1);
        double dr = closestPairRecursive(pointsByX, pointsByY, mid + 1, right, depth + 1);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            this.comparisons++;
            if (Math.abs(pointsByX[i].x - midPoint.x) < d) {
                strip[stripSize++] = pointsByX[i];
            }
        }

        Point[] stripByY = Arrays.copyOf(strip, stripSize);
        Arrays.sort(stripByY, (p1, p2) -> Double.compare(p1.y, p2.y));

        double minStrip = closestInStrip(stripByY, d);
        return Math.min(d, minStrip);
    }

    private double closestInStrip(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                this.comparisons++;
                double dist = strip[i].distanceTo(strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}