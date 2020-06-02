package display;

import org.joml.Vector2f;

import graphic.Drawable;

public class Cube extends Drawable{
    private Vector2f textOffset;
    private float[] currentTextCoord = Cube.textCoord;

    public static final float[] vertices = new float[]{
        //front
        0.5f, 0.5f, 0.5f,
        0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, 0.5f, 0.5f,
        //right
        0.5f, 0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, 0.5f,
        0.5f, 0.5f, 0.5f,
        //back
        -0.5f, 0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        0.5f, -0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        //left
        -0.5f, 0.5f, 0.5f,
        -0.5f, -0.5f, 0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, 0.5f, -0.5f,
        //top
        -0.5f, 0.5f, 0.5f,
        -0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, -0.5f,
        0.5f, 0.5f, 0.5f,
        //bottom
        0.5f, -0.5f, 0.5f,
        0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f, -0.5f,
        -0.5f, -0.5f, 0.5f,
    };

    public static int[] indices =  new int[]{
        0, 1, 3, 3, 1, 2,
        4, 5, 7, 7, 5, 6,
        8, 9, 11, 11, 9, 10,
        12, 13, 15, 15, 13, 14,
        16, 17, 19, 19, 17, 18,
        20, 21, 23, 23, 21, 22
    };

    public static final float[] textCoord = new float[]{
        0.001f, 0.001f, 0.001f, 0.499f, 0.249f, 0.499f, 0.249f, 0.001f,
        0.251f, 0.001f, 0.251f, 0.499f, 0.499f, 0.499f, 0.499f, 0.001f,
        0.501f, 0.001f, 0.501f, 0.499f, 0.749f, 0.499f, 0.749f, 0.001f,
        0.751f, 0.001f, 0.751f, 0.499f, 0.999f, 0.499f, 0.999f, 0.001f,
        0.001f, 0.501f, 0.001f, 0.999f, 0.249f, 0.999f, 0.249f, 0.501f,
        0.251f, 0.501f, 0.251f, 0.999f, 0.499f, 0.999f, 0.499f, 0.501f,
    };

    public void setTextOffset(float x, float y){
        textOffset = new Vector2f(x, y);
    }

    public Vector2f getTextOffset(){
        return textOffset;
    }

    public void setTextCoord(float[] textCoord){
        this.currentTextCoord = textCoord;
    }

    public float[] getTextCoord(){
        return currentTextCoord;
    }
}