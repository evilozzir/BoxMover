package eoz.boxMover;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    static List<Box> shapes = new ArrayList<Box>();
    static int topShape;
    static boolean somethingIsSelected = false;
    static boolean somethingIsHovered = false;
    static int x = 1024;
    static int y = 768;
    public static void main(String[] args){
        try{
            Display.setDisplayMode(new DisplayMode(x,y));
            Display.setTitle("Box Mover");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 1024, 768, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        while (!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
            while(Keyboard.next()){
                if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE &! Keyboard.getEventKeyState()){
                    Display.destroy();
                    System.exit(0);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_N &! Keyboard.getEventKeyState()){
                    if(shapes.size()>0){
                        shapes.add(new Box(shapes.get(shapes.size()-1).x+5,shapes.get(shapes.size()-1).y+5));
                    }
                    else{
                        shapes.add(new Box(5,5));
                    }
                    System.out.printf("Created new box named %s\n",shapes.size()-1);
                }
            }
           for(Box box : shapes) {
               boolean inBounds = box.isInBounds(Mouse.getX(), y - Mouse.getY());
               if(inBounds) {
                   topShape = shapes.indexOf(box);
                   somethingIsHovered = true;
                   if(Mouse.isButtonDown(0)) {
                       somethingIsSelected = true;
                   }
               }
               if(!inBounds){
                   if(somethingIsHovered){
                       box.deselect();
                   }
                   if(!Mouse.isButtonDown(0)) {
                       somethingIsSelected = false;
                   }
               }
               box.draw();
           }
           if(somethingIsHovered) {
               shapes.get(topShape).select();
           }
           if(Mouse.isButtonDown(0)&&somethingIsSelected) {
               shapes.get(topShape).select();
               shapes.get(topShape).update(Mouse.getDX(),-Mouse.getDY());
           }
            Display.update();
            Display.sync(60);
        }
    }
}
