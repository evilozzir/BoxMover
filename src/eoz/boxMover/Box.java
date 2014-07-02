package eoz.boxMover;

import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
/**
 * Created by Gonzalo on 02/07/2014.
 */
public class Box {
    public int x,y;
    private float colorRed, colorBlue, colorGreen;
    Box (int x, int y){
        this.y = y;
        this.x = x;
        Random randomGenerator = new Random();
        colorBlue = randomGenerator.nextFloat();
        colorRed = randomGenerator.nextFloat();
        colorGreen = randomGenerator.nextFloat();
    }
    void randomizeColors(){
        Random randomGenerator = new Random();
        colorBlue = randomGenerator.nextFloat();
        colorRed = randomGenerator.nextFloat();
        colorGreen = randomGenerator.nextFloat();
    }
    void draw(){
        glColor3f(colorRed,colorGreen,colorGreen);
        glBegin(GL_QUADS);
        glVertex2i(x,y);
        glVertex2i(x + 50, y);
        glVertex2i(x + 50, y + 50);
        glVertex2i(x, y + 50);
        glEnd();
    }

}
