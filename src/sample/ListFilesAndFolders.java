package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFilesAndFolders {

    public static Map<String,List<FilesList>> listFilesAndFolders(String path, SimpleDateFormat date){
        List<FilesList> files = new ArrayList<>();
        List<FilesList> directories = new ArrayList<>();
        Map<String ,List<FilesList>> mapa = new HashMap<>();
        File a = new File(path);
        File[] fList = a.listFiles();
        for(File f : fList){
            if(f.isFile()){
                files.add(new FilesList(f.getName(), f.length(), f.lastModified(), "", new ImageView(new Image("file:D:/Studia/Magister/TP%20-%20aplikacje%20lokalne/JCommander/png/plik.png")),date));
            }else if(f.isDirectory()){
                directories.add(new FilesList(f.getName(), f.length(), f.lastModified(), "<dir>", new ImageView(new Image("file:D:/Studia/Magister/TP%20-%20aplikacje%20lokalne/JCommander/png/folder.png")),date));
            }
        }
        mapa.put("Files",files);
        mapa.put("Directories", directories);
        return mapa;
    }

}
