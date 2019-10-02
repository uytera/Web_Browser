package com.filem.tech;
import java.io.File;
import java.util.ArrayList;

public class FilesList {
    private ArrayList<File> Dirs = new ArrayList<>();
    private ArrayList<File> Files = new ArrayList<>();
    private String ParentPath;

    public ArrayList<File> getDirs() {
        return Dirs;
    }

    public ArrayList<File> getFiles() {
        return Files;
    }

    public String getPath() {
        return ParentPath;
    }

    public boolean setLists(String path){
        try{
            File file = new File(path);
            File[] arrFiles  = new File(path).listFiles();
            ParentPath = file.getParent();

            for (File f: arrFiles ){
                 if (f.isFile()){
                     Files.add(f);
                 }
                 else{
                     Dirs.add(f);
                 }
            }
            return true;
        }

        catch(NullPointerException excep){
            return false;
        }
    }
}
