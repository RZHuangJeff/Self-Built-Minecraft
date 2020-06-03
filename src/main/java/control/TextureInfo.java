package control;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4f;

public class TextureInfo {
    public static final Vector2i UNIT1_SEPERATION = new Vector2i(4, 3);
    public static final Vector2i UNIT2_SEPERATION = new Vector2i(1, 1);
    public static final float UNIT2_WIDTH = 1f/UNIT2_SEPERATION.x;
    public static final float UNIT2_HEIGHT = 1f/UNIT2_SEPERATION.y;

    public static final int ROCK = 1;
    public static final int GRASS = 2;
    public static final int DIRT = 3;
    public static final int COBBLE = 4;
    public static final int PLANK = 5;
    public static final int BEDROCK = 6;
    public static final int LOG = 7;
    public static final int LEAVES = 8;
    public static final int GLASS = 20;
    public static final int WOOL = 35;
    public static final int CRAFT = 58;

    public static final int[] ITEM_LIST = new int[]{
        ROCK, GRASS, DIRT, COBBLE, PLANK, BEDROCK, LOG, LEAVES, GLASS, WOOL, CRAFT
    };

    private static final Vector4f ROCK_ITEM = new Vector4f(0.25f, 0.6666f, 0.3125f, 0.8333f);
    private static final Vector4f GRASS_ITEM = new Vector4f(0.25f, 0.3333f, 0.3125f, 0.5f);
    private static final Vector4f DIRT_ITEM = new Vector4f(0.75f, 0, 0.8125f, 0.1667f);
    private static final Vector4f BEDROCK_ITEM = new Vector4f(0, 0, 0.0625f, 0.1667f);
    private static final Vector4f COBBLE_ITEM = new Vector4f(0.25f, 0, 0.3125f, 0.1667f);
    private static final Vector4f CRAFT_ITEM = new Vector4f(0.5f, 0, 0.5625f, 0.1667f);
    private static final Vector4f GLASS_ITEM = new Vector4f(0, 0.3333f, 0.0625f, 0.5f);
    private static final Vector4f LEAVES_ITEM = new Vector4f(0.5f, 0.3333f, 0.5625f, 0.5f);
    private static final Vector4f LOG_ITEM = new Vector4f(0.75f, 0.3333f, 0.8125f, 0.5f);
    private static final Vector4f PLANK_ITEM = new Vector4f(0, 0.6666f, 0.0625f, 0.8333f);
    private static final Vector4f WOOL_ITEM = new Vector4f(0.5f, 0.6666f, 0.5625f, 0.8333f);

    private static final Vector2f ROCK_U1 = new Vector2f(1, 2);
    private static final Vector2f GRASS_U1 = new Vector2f(1, 1);
    private static final Vector2f DIRT_U1 = new Vector2f(3, 0);
    private static final Vector2f BEDROCK_U1 = new Vector2f(0, 0);
    private static final Vector2f COBBLE_U1 = new Vector2f(1, 0);
    private static final Vector2f CRAFT_U1 = new Vector2f(2, 0);
    private static final Vector2f GLASS_U1 = new Vector2f(0, 1);
    private static final Vector2f LEAVES_U1 = new Vector2f(2, 1);
    private static final Vector2f LOG_U1 = new Vector2f(3, 1);
    private static final Vector2f PLANK_U1 = new Vector2f(0, 2);
    private static final Vector2f WOOL_U1 = new Vector2f(2, 2);

    public static Vector4f getItemTextureOffset(int itemId){
        switch (itemId) {
            case ROCK:
                return new Vector4f(ROCK_ITEM);
            case GRASS:
                return new Vector4f(GRASS_ITEM);
            case DIRT:
                return new Vector4f(DIRT_ITEM);
            case BEDROCK:
                return new Vector4f(BEDROCK_ITEM);
            case COBBLE:
                return new Vector4f(COBBLE_ITEM);
            case PLANK:
                return new Vector4f(PLANK_ITEM);
            case LOG:
                return new Vector4f(LOG_ITEM);
            case LEAVES:
                return new Vector4f(LEAVES_ITEM);
            case GLASS:
                return new Vector4f(GLASS_ITEM);
            case WOOL:
                return new Vector4f(WOOL_ITEM);
            case CRAFT:
                return new Vector4f(CRAFT_ITEM);
            default:
                return new Vector4f(ROCK_ITEM);
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
            case BEDROCK:
                return new Vector2f(BEDROCK_U1);
            case COBBLE:
                return new Vector2f(COBBLE_U1);
            case PLANK:
                return new Vector2f(PLANK_U1);
            case LOG:
                return new Vector2f(LOG_U1);
            case LEAVES:
                return new Vector2f(LEAVES_U1);
            case GLASS:
                return new Vector2f(GLASS_U1);
            case WOOL:
                return new Vector2f(WOOL_U1);
            case CRAFT:
                return new Vector2f(CRAFT_U1);
                
            default:
                return new Vector2f(ROCK_U1);
        }
    }
}