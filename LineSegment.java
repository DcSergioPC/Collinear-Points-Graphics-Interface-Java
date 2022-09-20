
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;

/*************************************************************************
 *  Compilation:  javac LineSegment.java
 *  Execution:    none
 *  Dependencies: Point.java
 *
 *  An immutable datat ype for Line segments in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 *  DO NOT MODIFY THIS CODE.
 *
 *************************************************************************/

public class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private static int max = 1000;
    private LineSegment siguiente;

    /**
     * Initializes a new line segment.
     *
     * @param  p one endpoint
     * @param  q the other endpoint
     * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
     *         is <tt>null</tt>
     */
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.siguiente = null;
    }
    public LineSegment(Point p) {
        if (p == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.siguiente = null;
    }

    public LineSegment(Point p, Point q, LineSegment siguiente) {
        this.p = p;
        this.siguiente = siguiente;
    }

    public LineSegment getSiguiente() {
        return siguiente;
    }

    public Point getP() {
        return p;
    }

    public static void setMax(int max){
        LineSegment.max = max;
    }
    
    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        LineSegment aux = this;
        while(aux.siguiente != null){
            aux.getP().draw("RED");
            aux.getP().drawTo(aux.siguiente.getP());
            aux.getP().draw();
            aux = aux.siguiente;
        }
        aux.getP().draw("RED");char k = 'c';
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
                else if(max==9500)
                    break;
                
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
        Point.setMax(max);
        aux.getP().draw();
    }

    public void add (Point p){
        LineSegment aux = this;
        if(aux==null) return ;
        while(aux.siguiente!=null)
            aux = aux.siguiente;
        aux.siguiente = new LineSegment(p);
    }
    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    
    @Override
    public String toString() {
        LineSegment aux = this;
        String s = "";
        while(aux.siguiente!=null){
            s += aux.p + " , ";
            aux = aux.siguiente;
        }
        s += aux.p;
        return s;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * asssignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}
