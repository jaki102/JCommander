package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.file.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Controller {

    @FXML
    private TableView leftTable;
    @FXML
    private TableView rightTable;
    @FXML
    private ComboBox drivesIn;
    @FXML
    private ComboBox drivesOut;
    @FXML
    private Menu language;
    @FXML
    private MenuItem langPL;
    @FXML
    private MenuItem langEN;
    @FXML
    private TableColumn leftIconColumn;
    @FXML
    private TableColumn leftNameColumn;
    @FXML
    private TableColumn leftSizeColumn;
    @FXML
    private TableColumn leftDateColumn;
    @FXML
    private TableColumn rightIconColumn;
    @FXML
    private TableColumn rightNameColumn;
    @FXML
    private TableColumn rightSizeColumn;
    @FXML
    private TableColumn rightDateColumn;
    @FXML
    private Button btnCopy;
    @FXML
    private Button btnMove;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnLeftBack;
    @FXML
    private Button btnRightBack;
    @FXML
    private Label pthLeft;
    @FXML
    private Label pthRight;
    @FXML
    private Label showPathLeft;
    @FXML
    private Label showPathRight;
    private ResourceBundle bundle;
    private Locale locale;
    private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String pathLeft = "";
    private String pathRight = "";
    private String fullPathLeft;
    private String rootPathLeft = "C:\\";
    private String fullPathRight;
    private String rootPathRight = "C:\\";
    private Task copyTask;
    private Task moveTask;
    private Task deleteTask;
    private Stage progressBoxStage;

    @FXML
    public void initialize(){
        leftIconColumn.setCellValueFactory(new PropertyValueFactory<FilesList,ImageView>("image"));
        leftNameColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("name"));
        leftSizeColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("displaySize"));
        leftDateColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("dateType"));
        rightIconColumn.setCellValueFactory(new PropertyValueFactory<FilesList,ImageView>("image"));
        rightNameColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("name"));
        rightSizeColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("displaySize"));
        rightDateColumn.setCellValueFactory(new PropertyValueFactory<FilesList,String>("dateType"));
        leftSizeColumn.setComparator(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                if (o1.equals("") || o2.equals(""))
                    return 0;
                else
                {
                    o1 = o1.replaceAll(",", ".");
                    Double floatSize1 = new Double(0);
                    if (o1.contains("KB"))
                    {
                        o1 = o1.replaceAll("KB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f;
                    } else if (o1.contains("MB"))
                    {
                        o1 = o1.replaceAll("MB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f*1024f;
                    } else if (o1.contains("GB"))
                    {
                        o1 = o1.replaceAll("GB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f*1024f*1024f;
                    } else if(o1.contains("B"))
                    {
                        o1 = o1.replaceAll("B", "");
                        floatSize1 = Double.parseDouble(o1);
                    }

                    Double floatSize2 = new Double(0);
                    o2 = o2.replaceAll(",", ".");
                    if (o2.contains("KB"))
                    {
                        o2 = o2.replaceAll("KB", "");
                        floatSize2 = Double.parseDouble(o2) * 1024f;
                    } else if (o2.contains("MB"))
                    {
                        o2 = o2.replaceAll("MB", "");
                        floatSize2 = Double.parseDouble(o2)  * 1024f*1024f;
                    } else if (o2.contains("GB"))
                    {
                        o2 = o2.replaceAll("GB", "");
                        floatSize2 = Double.parseDouble(o2) * 1024f*1024f*1024f;
                    } else if(o2.contains("B"))
                    {
                        o2 = o2.replaceAll("B", "");
                        floatSize2 = Double.parseDouble(o2);
                    }

                    return floatSize1.compareTo(floatSize2);
                }
            }
        });
        rightSizeColumn.setComparator(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                if (o1.equals("") || o2.equals(""))
                    return 0;
                else
                {
                    o1 = o1.replaceAll(",", ".");
                    Double floatSize1 = new Double(0);
                    if (o1.contains("KB"))
                    {
                        o1 = o1.replaceAll("KB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f;
                    } else if (o1.contains("MB"))
                    {
                        o1 = o1.replaceAll("MB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f*1024f;
                    } else if (o1.contains("GB"))
                    {
                        o1 = o1.replaceAll("GB", "");
                        floatSize1 = Double.parseDouble(o1) * 1024f*1024f*1024f;
                    } else if(o1.contains("B"))
                    {
                        o1 = o1.replaceAll("B", "");
                        floatSize1 = Double.parseDouble(o1);
                    }

                    Double floatSize2 = new Double(0);
                    o2 = o2.replaceAll(",", ".");
                    if (o2.contains("KB"))
                    {
                        o2 = o2.replaceAll("KB", "");
                        floatSize2 = Double.parseDouble(o2) * 1024f;
                    } else if (o2.contains("MB"))
                    {
                        o2 = o2.replaceAll("MB", "");
                        floatSize2 = Double.parseDouble(o2)  * 1024f*1024f;
                    } else if (o2.contains("GB"))
                    {
                        o2 = o2.replaceAll("GB", "");
                        floatSize2 = Double.parseDouble(o2) * 1024f*1024f*1024f;
                    } else if(o2.contains("B"))
                    {
                        o2 = o2.replaceAll("B", "");
                        floatSize2 = Double.parseDouble(o2);
                    }

                    return floatSize1.compareTo(floatSize2);
                }
            }
        });
        setRootPath();
        setLang();
        loadLang("pl");
        setDirectoriesOnTheLeftList();
        setDirectoriesOnTheRightList();
        leftPathBack();
        rightPathBack();
        pthLeft.setText(rootPathLeft);
        pthRight.setText(rootPathRight);
        buttonDis();
    }
    private void loadLang(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("sample/MyResources",locale);
        leftNameColumn.setText(bundle.getString("nameColumn"));
        leftSizeColumn.setText(bundle.getString("sizeColumn"));
        leftDateColumn.setText(bundle.getString("dateColumn"));
        rightNameColumn.setText(bundle.getString("nameColumn"));
        rightSizeColumn.setText(bundle.getString("sizeColumn"));
        rightDateColumn.setText(bundle.getString("dateColumn"));
        btnCopy.setText(bundle.getString("btnCopy"));
        btnMove.setText(bundle.getString("btnMove"));
        btnDelete.setText(bundle.getString("btnDelete"));
        language.setText(bundle.getString("language"));
        langEN.setText(bundle.getString("langEN"));
        langPL.setText(bundle.getString("langPL"));
        showPathLeft.setText(bundle.getString("showPathLeft"));
        showPathRight.setText(bundle.getString("showPathRight"));
    }
    public ObservableList<FilesList> setTable(String path){
        Map<String, List<FilesList>> el = ListFilesAndFolders.listFilesAndFolders(path, DATE_FORMAT);
        List<FilesList> files = new ArrayList<>();
        List<FilesList> directories = new ArrayList<>();
        for(Map.Entry<String,List<FilesList>> entry : el.entrySet()){
            if (entry.getKey().equals("Files")) {
                files = entry.getValue();
            } else if (entry.getKey().equals("Directories")) {
                directories = entry.getValue();
            }
        }
        List<FilesList> joined = new ArrayList<>(files);
        joined.addAll(directories);
        ObservableList<FilesList> setColumn = FXCollections.observableArrayList(joined);
        return setColumn;
    }
    private void setRootPath() {
        File[] file = File.listRoots();
        drivesIn.getItems().addAll(Arrays.asList(file));
        drivesIn.getSelectionModel().selectFirst();
        drivesOut.getItems().addAll(Arrays.asList(file));
        drivesOut.getSelectionModel().selectFirst();
        drivesIn.setOnAction((event -> {
            pathLeft = "";
            pthLeft.setText(drivesIn.getSelectionModel().getSelectedItem().toString());
            ObservableList<FilesList> obList = setTable(drivesIn.getSelectionModel().getSelectedItem().toString());
            leftTable.setItems(obList);
        }));
        drivesOut.setOnAction((event -> {
            pathRight = "";
            pthRight.setText(drivesOut.getSelectionModel().getSelectedItem().toString());
            ObservableList<FilesList> obList = setTable(drivesOut.getSelectionModel().getSelectedItem().toString());
            rightTable.setItems(obList);
        }));
        ObservableList<FilesList> leftList = setTable("C:\\");
        leftTable.setItems(leftList);
        ObservableList<FilesList> rightList = setTable("C:\\");
        rightTable.setItems(rightList);
    }
    private void setLang(){
            langPL.setOnAction((event -> {
                loadLang("pl");
                DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                leftTable.setItems(setTable(pthLeft.getText()));
                rightTable.setItems(setTable(pthRight.getText()));
            }));
            langEN.setOnAction((event -> {
                loadLang("en");
                DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
                leftTable.setItems(setTable(pthLeft.getText()));
                rightTable.setItems(setTable(pthRight.getText()));
            }));
    }
    private void setDirectoriesOnTheLeftList() {
        leftTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                rootPathLeft = drivesIn.getSelectionModel().getSelectedItem().toString();
                pathLeft += "\\" + leftTable.getSelectionModel().selectedItemProperty().getValue().toString();
                fullPathLeft = rootPathLeft + pathLeft;
                String newFullPathLeft = fullPathLeft.replace("\\\\", "\\");
                File f = new File(newFullPathLeft);
                if (f.isDirectory()) {
                    openDirectoryLeft(newFullPathLeft);
                    pthLeft.setText(newFullPathLeft);
                } else {
                    openFile(f);
                    boolean endsWithSlash = pathRight.endsWith(File.separator);
                    String cos = pathLeft.substring(0, pathLeft.lastIndexOf(File.separatorChar,
                            endsWithSlash ? pathLeft.length() - 2 : pathLeft.length() - 1));
                    pathLeft= cos;
                }
                buttonDis();
            }
        });
    }
    private void setDirectoriesOnTheRightList() {
        rightTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                rootPathRight = drivesOut.getSelectionModel().getSelectedItem().toString();
                pathRight += "\\" + rightTable.getSelectionModel().selectedItemProperty().getValue().toString();
                fullPathRight = rootPathRight + pathRight;
                String newFullPathRight = fullPathRight.replace("\\\\", "\\");
                File f = new File(newFullPathRight);
                if (f.isDirectory()) {
                    openDirectoryRight(newFullPathRight);
                    pthRight.setText(newFullPathRight);
                } else {
                    openFile(f);
                    boolean endsWithSlash = pathRight.endsWith(File.separator);
                    String cos = pathRight.substring(0, pathRight.lastIndexOf(File.separatorChar,
                            endsWithSlash ? pathRight.length() - 2 : pathRight.length() - 1));
                    pathRight= cos;
                }
                buttonDis();
            }
        });
    }
    public void openFile(File filePath){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openDirectoryLeft(String filePath){
        ObservableList<FilesList> leftList = setTable(filePath);
        leftTable.setItems(leftList);
    }
    public void openDirectoryRight(String filePath){
        ObservableList<FilesList> rightList = setTable(filePath);
        rightTable.setItems(rightList);
    }
    @FXML
    private void leftPathBack(){
        btnLeftBack.setOnMouseClicked(e-> {
                boolean endsWithSlash = pathLeft.endsWith(File.separator);
                String cos = pathLeft.substring(0, pathLeft.lastIndexOf(File.separatorChar,
                        endsWithSlash ? pathLeft.length() - 2 : pathLeft.length() - 1));
                pathLeft = cos;
                fullPathLeft = rootPathLeft + pathLeft;
                String newFullPathLeft = fullPathLeft.replace("\\\\", "\\");
                ObservableList<FilesList> leftList = setTable(newFullPathLeft);
                leftTable.setItems(leftList);
                pthLeft.setText(newFullPathLeft);
                buttonDis();
        });
    }
    @FXML
    private void rightPathBack() {
        btnRightBack.setOnMouseClicked(e-> {
            boolean endsWithSlash = pathRight.endsWith(File.separator);
            String cos = pathRight.substring(0, pathRight.lastIndexOf(File.separatorChar,
                    endsWithSlash ? pathRight.length() - 2 : pathRight.length() - 1));
            pathRight = cos;
            fullPathRight = rootPathRight + pathRight;
            String newFullPathRight = fullPathRight.replace("\\\\", "\\");
            ObservableList<FilesList> rightList = setTable(newFullPathRight);
            rightTable.setItems(rightList);
            pthRight.setText(newFullPathRight);
            buttonDis();
        });
    }
    @FXML
    private void btnCopyClick(){
        btnCopy.setOnMouseClicked(e-> {
            ButtonType btnAccept = new ButtonType(bundle.getString("Accept"), ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType(bundle.getString("Cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
            File f = new File(pthRight.getText() + "\\" +leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
            if(f.exists()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString("overwriteContent"),btnAccept,btnCancel );
                    alert.setTitle(bundle.getString("Title"));
                    alert.setHeaderText(bundle.getString("overwriteHeader"));
                   // alert.setContentText(bundle.getString("overwriteContent"));
                    Optional<ButtonType> result = alert.showAndWait();
                   // alert.getButtonTypes().setAll(btnAccept, btnCancel);
                try{
                    if (result.get() == btnAccept){
                        copyFile();
                    }
                }catch (Exception c){
                    c.printStackTrace();
                }
            }else {
            try {
                copyFile();
            }catch (Exception b){
                b.printStackTrace();
            }}
        });
    }
    private void copyFile(){
        try {
            showProgressBox();
        }catch (Exception e){
            e.printStackTrace();
        }
        copyTask = new Task() {
            @Override
            protected Void call() throws Exception {
                Path copyFromLeft = Paths.get(pthLeft.getText(), leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
                Path copyToRight = Paths.get(pthRight.getText(), copyFromLeft.getFileName().toString());
                try {
                    Files.copy(copyFromLeft, copyToRight, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                } catch (IOException t) {
                    System.err.println(t);
                }
                return null;
            }
        };
        new Thread(copyTask).start();
        copyTask.setOnSucceeded(e -> {
            progressBoxStage.close();
            refreshTables();
        });
/*        copyTask.setOnSucceeded(e -> {
            ObservableList<FilesList> leftList = setTable(pthLeft.getText());
            leftTable.setItems(leftList);
            ObservableList<FilesList> rightList = setTable(pthRight.getText());
            rightTable.setItems(rightList);
        });*/
/*       Path copyFromLeft = Paths.get(pthLeft.getText(), leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
        Path copyToRight = Paths.get(pthRight.getText(), copyFromLeft.getFileName().toString());
        try {
            Files.copy(copyFromLeft, copyToRight, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
        } catch (IOException t) {
            System.err.println(t);
        }*/
    }

    @FXML
    private void btnDeleteClick(){
        btnDelete.setOnMouseClicked(e->{
            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(bundle.getString("Title"));
                alert.setHeaderText(bundle.getString("alertDeleteHeader"));
                alert.setContentText(bundle.getString("alertDeleteContent"));
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                        deleteFile();
                }
            }catch (Exception c){
                c.printStackTrace();
            }
        });
    }
    private void deleteFile(){
        deleteTask = new Task() {
            @Override
            protected Void call() throws Exception {
                File fileLeft = new File(pthLeft.getText()+"\\"+leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
                try {
                    if (!fileLeft.isDirectory()) {
                        FileUtils.forceDelete(fileLeft);
                    } else {
                        FileUtils.deleteDirectory(fileLeft);
                    }
                } catch (IOException n) {
                    n.printStackTrace();
                }
                return null;
            }
        };
        new Thread(deleteTask).start();
        deleteTask.setOnSucceeded(e -> {
            refreshTables();
        });
        /*File fileLeft = new File(pthLeft.getText()+"\\"+leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
        try {
            if (!fileLeft.isDirectory()) {
                FileUtils.forceDelete(fileLeft);
            } else {
                FileUtils.deleteDirectory(fileLeft);
            }
        } catch (IOException n) {
            n.printStackTrace();
        }*/
    }
    @FXML
    private void btnMoveClick(){
        btnMove.setOnMouseClicked(e->{
            File f = new File(pthRight.getText() + "\\" +leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
            if(f.exists()){
                try{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(bundle.getString("Title"));
                    alert.setHeaderText(bundle.getString("overwriteHeader"));
                    alert.setContentText(bundle.getString("overwriteContent"));
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        moveFile();
                    }
                }catch (Exception c){
                    c.printStackTrace();
                }
            }else {
            try {
                moveFile();
            }catch (Exception a){
                a.printStackTrace();
            }}
        });
    }
    private void moveFile() {
        try {
            showProgressBox();
        }catch (Exception e){
            e.printStackTrace();
        }
        moveTask = new Task() {
            @Override
            protected Void call() throws Exception {
                Path copyFromLeft = Paths.get(pthLeft.getText(), leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
                Path copyToRight = Paths.get(pthRight.getText(), copyFromLeft.getFileName().toString());
                File fileLeft = new File(pthLeft.getText()+"\\"+leftTable.getSelectionModel().selectedItemProperty().getValue().toString());
                try {
                    if (!fileLeft.isDirectory()) {
                        Files.copy(copyFromLeft, copyToRight, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        FileUtils.forceDelete(fileLeft);
                    } else {
                        Files.copy(copyFromLeft, copyToRight, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        FileUtils.deleteDirectory(fileLeft);
                    }
                } catch (IOException n) {
                    n.printStackTrace();
                }
                return null;
            }
        };
        new Thread(moveTask).start();
        moveTask.setOnSucceeded(e -> {
            progressBoxStage.close();
            refreshTables();
        });
/*      copyFile();
        deleteFile();
        ObservableList<FilesList> leftList = setTable(pthLeft.getText());
        leftTable.setItems(leftList);
        ObservableList<FilesList> rightList = setTable(pthRight.getText());
        rightTable.setItems(rightList);*/
    }
    private void buttonDis(){
        if(pathLeft.equals("")){
            btnLeftBack.setDisable(true);
        }else{
            btnLeftBack.setDisable(false);
        }
        if(pathRight.equals("")){
            btnRightBack.setDisable(true);
        }else{
            btnRightBack.setDisable(false);
        }
    }
    private void refreshTables(){
        ObservableList<FilesList> leftList = setTable(pthLeft.getText());
        leftTable.setItems(leftList);
        ObservableList<FilesList> rightList = setTable(pthRight.getText());
        rightTable.setItems(rightList);
    }
    private void showProgressBox() throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("progressBox.fxml"));
            Parent secondRoot = loader.load();
            progressBoxStage = new Stage();
            progressBoxStage.setTitle("Status operacji");
            progressBoxStage.setScene(new Scene(secondRoot));
            progressBoxStage.show();

    }
}
