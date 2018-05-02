package sample;

import javafx.scene.image.ImageView;
import java.text.SimpleDateFormat;

public class FilesList {

    private String name;
    private double size;
    private String dateType;
    private String displaySize;
    private ImageView image;
    private SimpleDateFormat DATA_FORMAT;

    public FilesList( String name, long size, long date, String displaySize, ImageView img, SimpleDateFormat DATE_FORMAT) {

           if(!displaySize.equals("<dir>")){
               this.DATA_FORMAT = DATE_FORMAT;
               this.image = img;
               if(size < 1024){
                   double bsize = Math.round(((double)size)*100.0)/100.0;
                   this.size =   bsize;
                   this.displaySize = Double.toString(bsize) + " B";
               }else if(size < 1024*1024 && size > 1024){
                   double ksize = Math.round((((double)size)/1024.0)*100.0)/100.0;
                   this.size =  ksize;
                   this.displaySize = Double.toString(ksize) + " KB";
               }else if(size < 1024*1024*1024 && size > 1024*1024){
                   double msize = Math.round((((double)size)/(1024.0*1024.0))*100.0)/100.0;
                   this.size =  msize;
                   this.displaySize = Double.toString(msize) + " MB";
               } else {
                   double gsize = Math.round((((double)size)/(1024.0*1024.0*1024.0))*100.0)/100.0;
                   this.size =  gsize;
                   this.displaySize = Double.toString(gsize) + " GB";
               }
           }else{

               this.displaySize = displaySize;
               this.image = img;
           }
        this.name = name;
           String dateType = DATE_FORMAT.format(date);
           this.dateType=dateType;

    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public String getDateType() {
        return dateType;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public ImageView getImage() {
        return image;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public void setImage(ImageView value) {
        this.image = value;
    }
    public SimpleDateFormat getDATA_FORMAT() {
        return DATA_FORMAT;
    }

    public void setDATA_FORMAT(SimpleDateFormat DATA_FORMAT) {
        this.DATA_FORMAT = DATA_FORMAT;
    }

    @Override
    public String toString() {
        return name;
    }
}
