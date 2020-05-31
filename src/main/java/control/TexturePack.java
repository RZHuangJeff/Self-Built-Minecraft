package control;

import java.io.File;
import java.util.HashMap;

import graphic.Texture;

public class TexturePack {
    private static final String rootPath = "textures";

    private static HashMap<String, Texture> textures;

    public static void init() throws Exception{
        textures = new HashMap<>();

        File root = new File(rootPath);
        for (String path : root.list()) {
            Texture texture = new Texture(rootPath + "/" + path);
            textures.put(path.substring(0, path.lastIndexOf(".png")), texture);
        }
    }

    public static Texture getTexture(String name){
        return textures.get(name);
    }
}