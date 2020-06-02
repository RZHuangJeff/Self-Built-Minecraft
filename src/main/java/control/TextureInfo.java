package control;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class TextureInfo {
    public static final Vector2i UNIT1_SEPERATION = new Vector2i(4, 1);
    public static final Vector2i UNIT2_SEPERATION = new Vector2i(1, 1);
    public static final float UNIT2_WIDTH = 1f/UNIT2_SEPERATION.x;
    public static final float UNIT2_HEIGHT = 1f/UNIT2_SEPERATION.y;

    public static final int ROCK = 1;
    public static final int GRASS = 2;
    public static final int DIRT = 3;

    public static final int APPLE = 260;

    private static final Vector2f ROCK_U1 = new Vector2f(0, 0);
    private static final Vector2f GRASS_U1 = new Vector2f(1, 0);
    private static final Vector2f DIRT_U1 = new Vector2f(2, 0);

    private static final Vector2f APPLE_U2 = new Vector2f(0, 0);

    public static Vector2f getItemTextureOffset(int itemId){
        switch (itemId) {
            case APPLE:
                return new Vector2f(APPLE_U2);
            default:
                return new Vector2f(APPLE_U2);
        }
    }

    public static Vector2f getCubeTextureOffset(int cubeId){
        switch (cubeId) {
            case ROCK:
                return new Vector2f(ROCK_U1);
            case GRASS:
                return new Vector2f(GRASS_U1);
            case DIRT:
                return new Vector2f(DIRT_U1);
            default:
                return new Vector2f(ROCK_U1);
        }
    }
}