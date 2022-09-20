import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.awt.Font;

public class GraficalPoints{
    public static Point[] leerfichero(String file) throws FileNotFoundException{
        Scanner scanner =  new Scanner(new File("input/"+file));
        // read the N points from a file
       // In in = new In(args[0]);
        int N = scanner.nextInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        scanner.close();
        return points;
    }
    public static void main(String[] args) throws FileNotFoundException {
        
        Point[] points = leerfichero("input100.txt");
        StdDraw.show(0);
        StdDraw.setCanvasSize(900, 900);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(Color.decode("#454546"));
        StdDraw.filledSquare(32768/2, 32768/2,32768/2);
        StdDraw.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.textLeft(1000, 31700, "HOLD '+' or '-'");
        
        StdDraw.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.text(6250, 31700, Double.toString(1000/1000f).substring(0, 3));
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.rectangle(6250, 31800,350,450);
        StdDraw.show();
        
        StdDraw.setPenRadius(0.005);
        StdDraw.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.line(1000,1000, 31000, 1000);
        StdDraw.line(1000,1000, 1000, 31000);
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 16));
        StdDraw.text(1200, 31000, "y");
        StdDraw.text(31000, 1200, "x");
        StdDraw.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
        StdDraw.setPenRadius();
        StdDraw.setPenColor(Color.GRAY);
        for (int i = 0; i <= 30; i++) {
            StdDraw.text(i*1000+1000, 300, Integer.toString(i));
            StdDraw.line(i*1000+1000,1000, i*1000+1000, 31000);
            StdDraw.text(300,i*1000+1000, Integer.toString(i));
            StdDraw.line(1000,i*1000+1000, 31000 , i*1000+1000);
        }
        StdDraw.show();
        
        // print and draw the line segments
        //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLACK);
        LineSegment[] seg = collinear.segments();
        
        System.out.println("Puntos de input100.txt");
        for (int i = 0 ; i < points.length ; i++) {
            System.out.print(points[i] + " , ");
            if(((i+1)%6)==0)
                System.out.println("");
        }
        System.out.println("\n\nGrupo de 4 o mas puntos colineales");
        for (int i = 0 ; i < seg.length ; i++) {
            //StdOut.println(segment);
            System.out.printf("Grupo %d \t[%s]\n",i+1,seg[i]);
            seg[i].draw();
            StdDraw.show();
        }
        System.out.println("");
        
        // draw the points
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(Color.orange);
        for (Point p : points) {
            p.draw();
            //System.out.println(p);
        }
        /*Arrays.sort(points);
        System.out.println("sorted");
        for (Point p : points) {
            p.draw();
            //System.out.println(p);
        }*/

        StdDraw.show();
        StdDraw.save("src/input/input100.png");
        
        points = leerfichero("input20.txt");
        System.out.println("Puntos de input20.txt");
        for (int i = 0 ; i < points.length ; i++) {
            System.out.print(points[i] + " , ");
            if(((i+1)%6)==0)    System.out.println("");
        }
        collinear = new FastCollinearPoints(points);
        seg = collinear.segments();
        System.out.println("\n\nGrupo de 4 o mas puntos colineales");
        for (int i = 0 ; i < seg.length ; i++) {
            System.out.printf("Grupo %d \t[%s]\n",i+1,seg[i]);
        }
        System.out.println("");
        
        points = leerfichero("input10.txt");
        System.out.println("Puntos de input10.txt");
        for (int i = 0 ; i < points.length ; i++) {
            System.out.print(points[i] + " , ");
            if(((i+1)%6)==0)    System.out.println("");
        }
        collinear = new FastCollinearPoints(points);
        seg = collinear.segments();
        System.out.println("\n\nGrupo de 4 o mas puntos colineales");
        for (int i = 0 ; i < seg.length ; i++) {
            System.out.printf("Grupo %d \t[%s]\n",i+1,seg[i]);
        }
        System.out.println("");
    }
}