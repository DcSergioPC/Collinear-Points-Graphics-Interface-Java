
/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;

public class Point implements Comparable<Point> {

    private final double x; // x-coordinate of this point
    private final double y; // y-coordinate of this point
    public static int max = 1000;

    /**
     * Initializes a new point.
     *
     * @param x
     *            the <em>x</em>-coordinate of the point
     * @param y
     *            the <em>y</em>-coordinate of the point
     */
    public Point(double x, double y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw(){
        draw("NO");
    }
    
    public void draw(String r) {
        /* DO NOT MODIFY */
        StdDraw.setPenRadius(0.02);
        double def = StdDraw.getPenRadius();
        StdDraw.setPenRadius(0);
            StdDraw.setPenColor(Color.orange);
        if(x>500)
            StdDraw.text(x, y+1200,toString());
        else
            StdDraw.text(x+2000, y+1200,toString());
        StdDraw.show();
        StdDraw.setPenRadius(def);
        if(!r.equals("RED"))
            StdDraw.setPenColor(Color.orange);
        else
            StdDraw.setPenColor(Color.RED);
        StdDraw.point(x+1000, y+1000);
        StdDraw.show();
        
    }

    /**
     * Draws the line segment between this point and the specified point to
     * standard draw.
     *
     * @param that
     *            the other point
     */
    public static void setMax(int max){
        Point.max = max;
    }
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        
        char k = 'c';
        for(int i = 0;i<=max;i++){
            if(StdDraw.hasNextKeyTyped()){
                k = StdDraw.nextKeyTyped();
                if(k=='+')
                    max +=100;
                else if(k=='-')
                    max -=100;
                if(max<0)
                    max=0;
                else if(max>9500)
                    max=9500;
                StdDraw.setPenRadius();
                StdDraw.setPenColor(Color.decode("#454546"));
                StdDraw.filledRectangle(6250, 31800,400,500);
                StdDraw.show();
                StdDraw.setPenColor(Color.CYAN);
                StdDraw.text(6250, 31700, Double.toString(max/1000f).substring(0, 3));
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.rectangle(6250, 31800,350,450);
                StdDraw.show();
                //System.out.println(k + " max = " + max);
            }
            StdDraw.pause(1);
        }
        LineSegment.setMax(max);
        
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(this.x+1000, this.y+1000, that.x+1000, that.y+1000);
        StdDraw.show();
        
    }

    /**
     * Returns the slope between this point and the specified point. Formally,
     * if the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0)
     * / (x1 - x0). For completness, the slope is defined to be +0.0 if the line
     * segment connecting the two points is horizontal; Double.POSITIVE_INFINITY
     * if the line segment is vertcal; and Double.NEGATIVE_INFINITY if (x0, y0)
     * and (x1, y1) are equal.
     *
     * @param that
     *            the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {

        // same
        if (that.x == this.x && that.y == this.y)
            return Double.NEGATIVE_INFINITY;

        double jX = that.x - this.x;
        double jY = that.y - this.y;

        // horizontal
        if (jY == 0.0)
            return +0.0f;

        // Vertical
        if (jX == 0.0)
            return Double.POSITIVE_INFINITY;

        // compute clope
        return jY / jX;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that
     *            the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point
     *         (x0 = x1 and y0 = y1); a negative integer if this point is less
     *         than the argument point; and a positive integer if this point is
     *         greater than the argument point
     */
    public int compareTo(Point that) {
        // corner cases
        if (that == null) {
            throw new NullPointerException();
        }
        // same
        if (this.x == that.x && this.y == that.y) {
            return 0;
        }
        //less
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        }
        //bigger
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point. The slope is
     * defined as in the slopeTo() method.
     *
     *
     *
     * The slopeOrder() method should return a comparator that compares its two
     * argument points by the slopes they make with the invoking point (x0, y0).
     * Formally, the point (x1, y1) is less than the point (x2, y2) if and only
     * if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2
     * − x0). Treat horizontal, vertical, and degenerate line segments as in the
     * slopeTo() method.
     *
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new jComparator();
    }

    private class jComparator implements Comparator<Point> {

        @Override
        public int compare(Point arg0, Point arg1) {
            double jSlope0 = slopeTo(arg0);
            double jSlope1 = slopeTo(arg1);

            return Double.compare(jSlope0, jSlope1);
        }
    }

    /**
     * Returns a string representation of this point. This method is provide for
     * debugging; your program should not rely on the format of the string
     * representation.
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        /* DO NOT MODIFY */
        String X = Double.toString(x/1000);
        String Y = Double.toString(y/1000);
        if(X.endsWith(".0"))
            X = Integer.toString((int) (x/1000));
        if(Y.endsWith(".0"))
            Y = Integer.toString((int) (y/1000));
        return "(" + X + ", " + Y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
}