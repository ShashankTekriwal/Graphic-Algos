//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiConsumer;
import javax.swing.JPanel;

class Draw extends JPanel {
    private BufferedImage canvas;

    public Draw(int width, int height) {
        this.canvas = new BufferedImage(width, height, 2);
    }

    public Dimension getPreferredSize() {
        return new Dimension(this.canvas.getWidth(), this.canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.canvas, (AffineTransform)null, (ImageObserver)null);
    }

    public void fillCanvas(Color c) {
        int color = c.getRGB();

        for(int x = 0; x < this.canvas.getWidth(); ++x) {
            for(int y = 0; y < this.canvas.getHeight(); ++y) {
                this.canvas.setRGB(x, y, color);
            }
        }

        this.repaint();
    }

    public void lineBresenham(int x1, int y1, int x2, int y2, Color c) {
        int color = c.getRGB();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int x;
        int y;
        int xEnd;
        int yEnd;
        int p;
        if(dx > dy) {
            if(x2 < x1) {
                x = x2;
                y = y2;
                xEnd = x1;
                yEnd = y1;
            } else {
                x = x1;
                y = y1;
                xEnd = x2;
                yEnd = y2;
            }

            p = 2 * (dy - dx);
            this.canvas.setRGB(x, y, color);

            for(; x < xEnd; this.canvas.setRGB(x, y, color)) {
                ++x;
                if(p < 0) {
                    p += 2 * dy;
                } else {
                    if(y < yEnd) {
                        ++y;
                    } else {
                        --y;
                    }

                    p += 2 * (dy - dx);
                }
            }
        } else {
            if(y2 < y1) {
                x = x2;
                y = y2;
                yEnd = y1;
                xEnd = x1;
            } else {
                x = x1;
                y = y1;
                yEnd = y2;
                xEnd = x2;
            }

            p = 2 * (dx - dy);
            this.canvas.setRGB(x, y, color);

            for(; y < yEnd; this.canvas.setRGB(x, y, color)) {
                ++y;
                if(p < 0) {
                    p += 2 * dx;
                } else {
                    if(x < xEnd) {
                        ++x;
                    } else {
                        --x;
                    }

                    p += 2 * (dx - dy);
                }
            }
        }

        this.repaint();
    }

    public void lineDDA(int x1, int y1, int x2, int y2, Color c) {
        int color = c.getRGB();
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.abs(dx) >= Math.abs(dy)?Math.abs(dx):Math.abs(dy);
        float xIncrement = (float)dx / (float)steps;
        float yIncrement = (float)dy / (float)steps;
        float x = (float)x1;
        float y = (float)y1;
        this.canvas.setRGB(Math.round(x), Math.round(y), color);

        for(int k = 1; k <= steps; ++k) {
            x += xIncrement;
            y += yIncrement;
            this.canvas.setRGB(Math.round(x), Math.round(y), color);
        }

        this.repaint();
    }

    public void lineMidpoint(int x1, int y1, int x2, int y2, Color c) {
        int color = c.getRGB();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int x;
        int y;
        int xEnd;
        int yEnd;
        int p;
        if(dx > dy) {
            if(x2 < x1) {
                x = x2;
                y = y2;
                xEnd = x1;
                yEnd = y1;
            } else {
                x = x1;
                y = y1;
                xEnd = x2;
                yEnd = y2;
            }

            p = dy - dx / 2;
            this.canvas.setRGB(x, y, color);

            for(; x < xEnd; this.canvas.setRGB(x, y, color)) {
                ++x;
                if(p < 0) {
                    p += dy;
                } else {
                    if(y < yEnd) {
                        ++y;
                    } else {
                        --y;
                    }

                    p += dy - dx;
                }
            }
        } else {
            if(y2 < y1) {
                x = x2;
                y = y2;
                yEnd = y1;
                xEnd = x1;
            } else {
                x = x1;
                y = y1;
                yEnd = y2;
                xEnd = x2;
            }

            p = dx - dy / 2;
            this.canvas.setRGB(x, y, color);

            for(; y < yEnd; this.canvas.setRGB(x, y, color)) {
                ++y;
                if(p < 0) {
                    p += dx;
                } else {
                    if(x < xEnd) {
                        ++x;
                    } else {
                        --x;
                    }

                    p += dx - dy;
                }
            }
        }

        this.repaint();
    }

    public void circleMidpoint(int xC, int yC, int r, Color c) throws InterruptedException {
        int color = c.getRGB();
        int x = 0;
        int y = r;
        int p = 1 - r;

        while(x <= y) {
            this.canvas.setRGB(xC + x, yC + y, color);
            this.canvas.setRGB(xC - x, yC + y, color);
            this.canvas.setRGB(xC + x, yC - y, color);
            this.canvas.setRGB(xC - x, yC - y, color);
            this.canvas.setRGB(xC + y, yC + x, color);
            this.canvas.setRGB(xC - y, yC + x, color);
            this.canvas.setRGB(xC + y, yC - x, color);
            this.canvas.setRGB(xC - y, yC - x, color);
            this.repaint();
            Thread.sleep(50L);
            ++x;
            if(p < 0) {
                p += 2 * x + 1;
            } else {
                --y;
                p += 2 * (x - y) + 1;
            }
        }

        this.repaint();
    }

    public void circleBresenham(int xC, int yC, int r, Color c) {
        int color = c.getRGB();
        int x = 0;
        int y = r;
        int p = 3 - 2 * r;

        while(x <= y) {
            this.canvas.setRGB(xC + x, yC + y, color);
            this.canvas.setRGB(xC - x, yC + y, color);
            this.canvas.setRGB(xC + x, yC - y, color);
            this.canvas.setRGB(xC - x, yC - y, color);
            this.canvas.setRGB(xC + y, yC + x, color);
            this.canvas.setRGB(xC - y, yC + x, color);
            this.canvas.setRGB(xC + y, yC - x, color);
            this.canvas.setRGB(xC - y, yC - x, color);
            this.repaint();

            try {
                Thread.sleep(50L);
            } catch (InterruptedException var10) {
                var10.printStackTrace();
            }

            ++x;
            if(p <= 0) {
                p += 4 * x + 6;
            } else {
                --y;
                p += 4 * (x - y) + 10;
            }
        }

        this.repaint();
    }

//    public void ellipseMidpoint(int xC, int yC, int rx, int ry, Color c) {
//        int color = c.getRGB();
//        BiConsumer setPixels = (x, y) -> {
//            this.canvas.setRGB(xC + x.intValue(), yC + y.intValue(), color);
//            this.canvas.setRGB(xC - x.intValue(), yC + y.intValue(), color);
//            this.canvas.setRGB(xC + x.intValue(), yC - y.intValue(), color);
//            this.canvas.setRGB(xC - x.intValue(), yC - y.intValue(), color);
//
//            try {
//                Thread.sleep(100L);
//            } catch (InterruptedException var7) {
//                var7.printStackTrace();
//            }
//
//            this.repaint();
//        };
//        int rx2 = rx * rx;
//        int ry2 = ry * ry;
//        int x = 0;
//        int y = ry;
//        int p = (int)Math.round((double)(ry2 - rx2 * ry) + 0.25D * (double)rx2);
//        int px = 0;
//        int py = 2 * rx2 * ry;
//        setPixels.accept(Integer.valueOf(x), Integer.valueOf(ry));
//
//        for(; px <= py; setPixels.accept(Integer.valueOf(x), Integer.valueOf(y))) {
//            ++x;
//            px += 2 * ry2;
//            if(p >= 0) {
//                --y;
//                py -= 2 * rx2;
//                p += ry2 + px - py;
//            } else {
//                p += ry2 + px;
//            }
//        }
//
//        for(p = (int)Math.round((double)ry2 * ((double)x + 0.5D) * ((double)x + 0.5D) + (double)(rx2 * (y - 1) * (y - 1)) - (double)(rx2 * ry2)); y >= 0; setPixels.accept(Integer.valueOf(x), Integer.valueOf(y))) {
//            --y;
//            py -= 2 * rx2;
//            if(p <= 0) {
//                ++x;
//                px += 2 * ry2;
//                p += rx2 - py + px;
//            } else {
//                p += rx2 - py;
//            }
//        }
//
//        this.repaint();
//    }

    public void scanLineFill(Color c, int... vertices) {
        int numEdges = vertices.length / 2;
        int[] xCoor = new int[numEdges];
        int[] yCoor = new int[numEdges];
        int yMin = 0;

        int yMax;
        for(yMax = 0; yMin < vertices.length; ++yMin) {
            if(yMin % 2 == 0) {
                xCoor[yMax] = vertices[yMin];
            } else {
                yCoor[yMax] = vertices[yMin];
                ++yMax;
            }
        }

        yMin = yCoor[0];
        yMax = yCoor[0];

        for(int slope_inv = 0; slope_inv < numEdges; ++slope_inv) {
            if(yCoor[slope_inv] > yMax) {
                yMax = yCoor[slope_inv];
            } else if(yCoor[slope_inv] < yMin) {
                yMin = yCoor[slope_inv];
            }
        }

        float[] var17 = new float[numEdges];

        for(int ael = 0; ael < numEdges; ++ael) {
            int intersection = ael + 1;
            if(ael == numEdges - 1) {
                intersection = 0;
            }

            var17[ael] = (float)(xCoor[ael] - xCoor[intersection]) / (float)(yCoor[ael] - yCoor[intersection]);
        }

        ArrayList var18 = new ArrayList();
        ArrayList var19 = new ArrayList();

        int i;
        for(i = yMin; i <= yMax; ++i) {
            int j;
            int e;
            for(int arr = 0; arr < yCoor.length; ++arr) {
                if(yCoor[arr] == i) {
                    j = arr - 1;
                    if(arr == 0) {
                        j = yCoor.length - 1;
                    }

                    if(!var18.contains(Integer.valueOf(arr))) {
                        var18.add(Integer.valueOf(arr));
                        var19.add(Float.valueOf((float)xCoor[arr]));
                    } else {
                        e = var18.indexOf(Integer.valueOf(arr));
                        var18.remove(e);
                        var19.remove(e);
                    }

                    if(!var18.contains(Integer.valueOf(j))) {
                        var18.add(Integer.valueOf(j));
                        var19.add(Float.valueOf((float)xCoor[arr]));
                    } else {
                        e = var18.indexOf(Integer.valueOf(j));
                        var18.remove(e);
                        var19.remove(e);
                    }
                }
            }

            float[] var20 = new float[var19.size()];

            for(j = 0; j < var19.size(); ++j) {
                float var21 = ((Float)var19.get(j)).floatValue();
                float x_new = var21 + var17[((Integer)var18.get(j)).intValue()];
                var19.set(j, Float.valueOf(x_new));
                var20[j] = x_new;
            }

            Arrays.sort(var20);

            for(j = 0; j < var20.length - 1; j += 2) {
                for(e = Math.round(var20[j]); (float)e <= var20[j + 1]; ++e) {
                    this.canvas.setRGB(e, i, c.getRGB());
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var16) {
                    var16.printStackTrace();
                }

                this.repaint();
            }
        }

        for(i = 0; i < numEdges - 1; ++i) {
            this.lineDDA(xCoor[i], yCoor[i], xCoor[i + 1], yCoor[i + 1], Color.BLACK);
        }

        this.lineDDA(xCoor[numEdges - 1], yCoor[numEdges - 1], xCoor[0], yCoor[0], Color.BLACK);
        this.repaint();
    }

    void boundaryFill(int x, int y, Color boundary, Color fill) {
        int fillColor = fill.getRGB();
        int boundaryColor = boundary.getRGB();
        int current = this.canvas.getRGB(x, y);
        if(current != boundaryColor && current != fillColor) {
            this.canvas.setRGB(x, y, fillColor);
            this.boundaryFill(x + 1, y, boundary, fill);
            this.boundaryFill(x - 1, y, boundary, fill);
            this.boundaryFill(x, y + 1, boundary, fill);
            this.boundaryFill(x, y - 1, boundary, fill);
        }

    }

    public void test() {
        ArrayList res = new ArrayList();
        ArrayList res2 = new ArrayList();
        res.add(new int[] {1,3});
        res.add(new int[] {1,4});
        res.add(new int[] {1,5});
        res2.add(new int[] {2,5});
        res2.addAll(res);
        for(int i = 0; i <= res2.size()-1; i++){
            System.out.println(((int[])res2.get(i))[0]);
        }
    }

    public void SutherLandHodgmanPolygonCLipping(int subjectP[][], int clipper[][]){
        ArrayList res = new ArrayList();
        for(int i = 0; i <= subjectP.length - 1; i++){
            res.add(subjectP[i]);
        }
        System.out.println("INPUT POLUGON IS");
        Iterator it = res.iterator();
        while(it.hasNext()){
            int temp[] = (int[]) it.next();
            System.out.println(temp[0]+" , "+temp[1]);
        }
        System.out.println("----------");
        for(int i = 0; i <= 3; i++){
            ArrayList input = new ArrayList(res);
            res.clear();
            int clipperLen = clipper.length;
            int A[] = clipper[i];
            int B[] = clipper[(clipperLen -1 +i)%clipperLen];
            int inputSize = input.size();
            for(int j = 0; j <= inputSize - 1; j++){
                int P[] = (int[]) input.get(j);
                int Q[];
                if(j == inputSize-1){
                    Q = (int[]) input.get(0);
                }else{
                    Q = (int[]) input.get(j+1);
                }

                int state = -1;
                if(isInside(A, B, P)){
                    if(!isInside(A, B, Q)){
                        res.add(intersection(A, B, P, Q));
                    } else {
                        res.add(Q);
                    }
                } else if(isInside(A, B, Q)) {
                    res.add(intersection(A, B, P, Q));
                    res.add(Q);
                }
            }
            //-----------print res
            System.out.println("RES after pass " + i);
            it = res.iterator();
            while(it.hasNext()){
                int temp[] = (int[]) it.next();
                System.out.println(temp[0]+" , "+temp[1]);
            }
            System.out.println("------------------------");
            //---------------------
        }
        Iterator i = res.iterator();
        while(i.hasNext()){
            int temp[] = (int[]) i.next();
            System.out.println(temp[0]+" , "+temp[1]);
        }
    }

    private int[] intersection(int[] a, int[] b, int[] p, int[] q) {
        double A1 = b[1] - a[1];
        double B1 = a[0] - b[0];
        double C1 = A1 * a[0] + B1 * a[1];

        double A2 = q[1] - p[1];
        double B2 = p[0] - q[0];
        double C2 = A2 * p[0] + B2 * p[1];

        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;

        return new int[]{(int)(Math.round(x)), (int)(Math.round(y))};
    }

    private int[] intersection2(int[] a, int[] b, int[] p, int[] q) {
        double A1 = b[1] - a[1];
        double B1 = a[0] - b[0];
        double C1 = A1 * a[0] + B1 * a[1];

        double A2 = q[1] - p[1];
        double B2 = p[0] - q[0];
        double C2 = A2 * p[0] + B2 * p[1];

        if(A1*B2 == A2*B1){
            return new int[]{};
        }

        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;

        if(Math.min(a[0], b[0]) <= x && x <= Math.max(a[0],b[0]) &&
                Math.min(p[0], q[0]) <= x && x <= Math.max(p[0],q[0])){
            return new int[]{(int)(Math.round(x)), (int)(Math.round(y))};
        }
        return new int[]{};

    }

    private boolean isInside(int[] a, int[] b, int[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) < (a[1] - c[1]) * (b[0] - c[0]);
    }

    public void WeilerAthertonPolygonClipping(int poly[][], int clipper[][]){
        ArrayList polyIntersec = new ArrayList();
        int polyLen = poly.length;
        int clipperLen = clipper.length;
        ArrayList clipperIntersec[] = new ArrayList[clipperLen];
        for(int i = 0; i <= clipperLen-1; i++){
            clipperIntersec[i] = new ArrayList();
        }
        for(int i = 0; i <= polyLen-1; i++){
            ArrayList temp = new ArrayList();
            int P[] = poly[i];
            int Q[] = poly[(i+1)%polyLen];
            for(int j = 0; j <= clipperLen-1; j++){
                int A[] = clipper[j];
                int B[] = clipper[(j+1)%clipperLen];
                int iPoint[] = intersection2(P, Q, A, B);
                if(iPoint.length != 0){
                    temp.add(iPoint);
                    clipperIntersec[j].add(iPoint);
                }
            }
            sort(temp, P);
            polyIntersec.add(P);
            polyIntersec.addAll(temp);
        }
        ArrayList clipperSeq = new ArrayList();
        for(int i = 0; i <= clipperLen-1; i++){
            int A[] = clipper[i];
            clipperSeq.add(A);
            sort(clipperIntersec[i],A);
            clipperSeq.addAll(clipperIntersec[i]);
        }
    }

    private void sort(ArrayList temp, int[] p) {
        int len = temp.size();
        int dist[] = new int[len];
        for(int i = 0; i <= len-1; i++){
            dist[i] = Math.abs(p[0] - ((int[])temp.get(i))[0]);
        }
        for(int i = 0; i <= len-1; i++){
            for(int j = 0; j <= len-2; j++){
                if(dist[j] > dist[j+1]){
                    int d = dist[j+1];
                    dist[j+1] = dist[j];
                    dist[j] = d;
                    int t[] = (int[])temp.get(j+1);
                    temp.set(j+1, temp.get(j));
                    temp.set(j, t);
                }
            }
        }
    }
}