package display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.joml.Vector2i;

import control.TexturePack;
import graphic.Mesh;
import graphic.Texture;

public class Label extends Controller{
    private Texture font;
    private String text = "";
    private float width;
    private static float[] textWidth = null;

    public Label(){
        this.font = TexturePack.getTexture("ascii");
        
        if(textWidth == null)
            loadTextWidth();
    }

    private void loadTextWidth(){
        textWidth = new float[256];
        Scanner scanner = new Scanner(Label.class.getResourceAsStream("/ascii.prop"));
        scanner.useDelimiter("width.|=|\r\n");

        Arrays.fill(textWidth, 14);

        for(int i = 0; i < 157; i++){
            textWidth[scanner.nextInt()] = 2*scanner.nextFloat();
            scanner.next();
        }
    }

    public void setText(String text){
        this.text = text;
        loadMesh();
    }

    public String getText(){
        return text;
    }

    public float getWidth(){
        return width*getScale().x;
    }

    public void setTextSize(float size){
        setSize(size, size);
    }

    private void loadMesh(){
        byte[] chars = text.getBytes();

        ArrayList<Float> vertices = new ArrayList<>();
        ArrayList<Float> textCoords = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        float w = 0;

        Vector2i textPos = new Vector2i();

        for(int i = 0; i < chars.length; i++){
            if(chars[i] < 0 || chars[i] >= 256) continue;
            
            textPos.x = chars[i]%16;
            textPos.y = chars[i]/16;

            addPosition(vertices, w, 0.0f, 0.0f);
            addPosition(vertices, w, 1.0f, 0.0f);
            addPosition(vertices, (w + textWidth[chars[i]]/16f), 1.0f, 0.0f);
            addPosition(vertices, (w + textWidth[chars[i]]/16f), 0.0f, 0.0f);

            w += textWidth[chars[i]]/16f;

            textPos.x *= 16;
            textPos.y *= 16;
            if(chars[i] == 'o' || chars[i] == 'w'){
                addPosition(textCoords, textPos.x/256f, (textPos.y + 1)/256f);
                addPosition(textCoords, textPos.x/256f, (textPos.y + 15)/256f);
                addPosition(textCoords, (textPos.x + textWidth[chars[i]])/256f, (textPos.y + 15)/256f);
                addPosition(textCoords, (textPos.x + textWidth[chars[i]])/256f, (textPos.y + 1)/256f);
            }else{
                addPosition(textCoords, textPos.x/256f, textPos.y/256f);
                addPosition(textCoords, textPos.x/256f, (textPos.y + 15)/256f);
                addPosition(textCoords, (textPos.x + textWidth[chars[i]])/256f, (textPos.y + 15)/256f);
                addPosition(textCoords, (textPos.x + textWidth[chars[i]])/256f, textPos.y/256f);
            }

            int startIndex = i*4;
            indices.add(startIndex);
            indices.add(startIndex + 1);
            indices.add(startIndex + 3);
            indices.add(startIndex + 3);
            indices.add(startIndex + 1);
            indices.add(startIndex + 2);
        }

        float[] vArray = new float[vertices.size()];
        float[] tArray = new float[textCoords.size()];
        byte[] iArray = new byte[indices.size()];

        for(int i = 0; i < vArray.length; i++)
            vArray[i] = vertices.get(i);

        for(int i = 0; i < tArray.length; i++)
            tArray[i] = textCoords.get(i);

        for(int i = 0; i < iArray.length; i++)
            iArray[i] = indices.get(i).byteValue();

        Mesh mesh = new Mesh(vArray, iArray);
        mesh.setTextCoord(tArray);
        mesh.setTexture(font);

        this.setMesh(mesh);

        width = w;
    }

    private void addPosition(ArrayList<Float> list, float x, float y){
        list.add(x);
        list.add(y);
    }

    private void addPosition(ArrayList<Float> list, float x, float y, float z){
        list.add(x);
        list.add(y);
        list.add(z);
    }
}