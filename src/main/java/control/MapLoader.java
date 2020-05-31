package control;

import java.io.File;
import java.util.ArrayList;

public class MapLoader {
    public static boolean createMap(String name){
        String newName = "";
        for(int i = 0; i < name.length(); i++){
            Character c = name.charAt(i);
            if(Character.isDigit(c) || (c >= 'a' && c <= 'z'))
                newName += c;
            else if(Character.isWhitespace(c))
                newName += "_";
        }

        boolean result = false;

        try {
            File file = new File("maps", newName);
            result = file.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<String> getMapNames(){
        ArrayList<String> result = new ArrayList<>();

        File folder = new File("maps");
        for (String file : folder.list()) {
            result.add(file);
        }

        return result;
    }

    public static void deleteMapByName(String todelete){
        File file = new File("maps", todelete);
        file.delete();
    }
}