package eoz.boxMover;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static void main(String[] args){
        try{
            Display.setDisplayMode(new DisplayMode(1024,768));
            Display.setTitle("Box Mover");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        while (!Display.isCloseRequested()){
            Display.update();
            Display.sync(60);
        }

    }
}
