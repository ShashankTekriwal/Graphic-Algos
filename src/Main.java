import javax.swing.JFrame;
import java.awt.Color;

/**
 * Created by Shashank on 3/2/2016.
 */
public class Main {
    public static void main(String args[]) throws InterruptedException {
        int width = 640;
        int height = 800;
        Draw draw = new Draw(width, height);
        JFrame frame = new JFrame("Draw Window");
        frame.add(draw);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        draw.fillCanvas(Color.cyan);
//        draw.scanLineFill(Color.BLACK,100,400,150,150,250,50,350,175,250,150);
//        draw.scanLineFill(Color.blue, 10,500,50,300,150,50,300,100,400,450,250,400,275,100,150,200,100,300,150,470);
//        draw.test();
//        draw.ellipseMidpoint(200,200,100,150,Color.red);
//        draw.lineDDA(200,100,100,200, Color.BLACK);
//        draw.lineDDA(100,200,300,200,Color.BLACK);
//        draw.lineDDA(300,200,200,100,Color.BLACK);
//        draw.boundaryFill(200,150,Color.BLACK,Color.blue);
        int poly[][] = {
                {150,300},
                {250,50},
                {600,300},
                {200,700},
                {325, 125}
        };
        int poly2[][] = {
                {250,100},
                {550,100},
                {550,400},
                {250,400}
        };
        int clipper[][] = {
                {100,200},
                {400,200},
                {400,600},
                {100,600}
        };
//        draw.SutherLandHodgmanPolygonCLipping(poly2, clipper);
        draw.test();
    }
}
