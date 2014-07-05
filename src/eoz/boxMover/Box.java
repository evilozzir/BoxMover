package eoz.boxMover;

import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
/**
 * Created by Gonzalo on 02/07/2014.
 */
public class Box {
    public int x,y;
    private float colorRed, colorBlue, colorGreen, colorAlfa;
    boolean selected = false;
    Box (int x, int y){
        this.y = y;
        this.x = x;
        Random randomGenerator = new Random();
        colorBlue = randomGenerator.nextFloat();
        colorRed = randomGenerator.nextFloat();
        colorGreen = randomGenerator.nextFloat();
        colorAlfa = 1.0f;
    }
    void randomizeColors(){
        Random randomGenerator = new Random();
        colorBlue = randomGenerator.nextFloat();
        colorRed = randomGenerator.nextFloat();
        colorGreen = randomGenerator.nextFloat();
    }
    void draw(){
        glColor4f(colorRed,colorBlue,colorGreen,colorAlfa);
        glBegin(GL_QUADS);
        glVertex2i(x,y);
        glVertex2i(x + 50, y);
        glVertex2i(x + 50, y + 50);
        glVertex2i(x, y + 50);
        glEnd();
    }
    boolean isInBounds(int mouseX, int mouseY){
        return mouseX > x && mouseX < x + 50 && mouseY > y && mouseY < y + 50;
    }
    void select(){
        colorAlfa = 0.2f;
    }
    void deselect(){
        colorAlfa = 1.0f;
    }
    void update(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
