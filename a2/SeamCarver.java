/*
 * Author:        Paul Rodriguez
 * Created:       5/5/2014
 * Last Updated:  5/5/2014
 * 
 * 
 */
import java.awt.Color;
public class SeamCarver
{
    private double BORDER_ENERGY = 195075.0;
    private Picture pic;
    private double[][] pic_energy;
    private double[][] distTo;
    private double[][] edgeTo;
    
    public SeamCarver(Picture picture)
    {
        pic = new Picture(picture);
    }
    
    //current picture
    public Picture picture()
    {
        return new Picture(pic);
    }
    
    //picture width
    public int width()
    {
        return pic.width();
    }
    
    //picture height
    public int height() 
    {
        return pic.height();
    }
    
    public double energy(int x, int y)
    {
        //System.out.println("x coord:" + x + ", y coord:"+y);
        //System.out.println("width: "+width() + ", height: "+height());
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
        {
            throw new IndexOutOfBoundsException("pixel indices out of bounds. x: "+ x+", y: "+ y + ":: width: "+width()+", height: "+height());
        }
        
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
        {
            return BORDER_ENERGY;
        }
        
        //  get the colors of the pixels adjacent to pixel (x,y)
        Color top = pic.get(x, y - 1);
        Color bottom = pic.get(x, y + 1);
        Color left = pic.get(x - 1, y);
        Color right = pic.get(x + 1, y);
        
        double gradient_x = Math.pow(left.getRed()-right.getRed(), 2)  + Math.pow(left.getGreen()-right.getGreen(),2) + Math.pow(left.getBlue() - right.getBlue(),2);
        double gradient_y = Math.pow(top.getRed()-bottom.getRed(), 2)  + Math.pow(top.getGreen()-bottom.getGreen(),2) + Math.pow(top.getBlue() - bottom.getBlue(),2);
        
        return gradient_x + gradient_y;
    }
    
    public int[] findHorizontalSeam()
    {
    
        return null;
    }
    
    public int[] findVerticalSeam()
    {
        
        double[][] pic_energy = new double[width()][height()];
        double[][] distTo = new double[width()][height()];
        double[][] edgeTo = new double[width()][height()];
        
        for (int i = 0; i < width(); i++)
        {
            //  one row
            for (int j = 0; j < height(); j++)
            {
                pic_energy[i][j] = energy(i, j);
                edgeTo[i][j] = -1;
                if (i == 0)
                {
                    distTo[i][j] = 0;
                }
                else
                {
                    distTo[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        
        for (int row = 0; row < height() - 1; row++) {
            for (int col = 0; col < width(); col++) {
                
                
                //  relax edge at bottom left
                if (col - 1 >= 0) {
                    relax(row, col, row + 1, col - 1);
                }
                
                //  relax edge at bottom
                relax(row, col, row + 1, col - 1);
               

                //  relax edge at bottom right
                if (col + 1 < width()) {
                    relax(row, col, row + 1, col + 1);
                }
            }
        }


        return null;
    }

    private void relax(int x1, int y1, int x2, int y2)
    {
        if (distTo[x2][y2] > distTo[x1][y1] + pic_energy[x2][y2])
        {
            distTo[x2][y2] = distTo[x1][y1] + pic_energy[x2][y2];
            edgeTo[x2][y2] = x2 * width() + y2;
        }
    }
    {
    
    }
    
    public void removeHorizontalSeam(int[] a)
    {
    
    }
    
    public void removeVerticalSeam(int[] a)
    {
        
    }
     
}