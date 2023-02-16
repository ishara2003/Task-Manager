package sankalpa.TaskManager.Controllers;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sankalpa.TaskManager.moduls.TaskMange;
//import sankalpa.TaskManager.others.Time;
import sankalpa.TaskManager.tm.TaskTm;
import sankalpa.TaskManager.to.Task;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MenuController {



    public TextField tNumber;
    public TableView tblTaskes;
    public  Button btnToDo;
    public AnchorPane btnPane;
    public JFXTimePicker timepicker;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<TaskTm, Integer> taskNumber;

    @FXML
    private TableColumn<?, ?> task;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableColumn<?, ?> option;

    @FXML
    private Pane addNedwtaskPane;

    @FXML
    private TextField tName;

    @FXML
    private TextField tTime;

    public void initialize(){

        timepicker.valueProperty().addListener(new ChangeListener<LocalTime>() {
            @Override
            public void changed(ObservableValue<? extends LocalTime> observable, LocalTime pre, LocalTime curr) {
                if (curr==null){
                    System.out.println(curr);
                }
            }
        });



        addNedwtaskPane.setVisible(false);

        task.setCellValueFactory(new PropertyValueFactory<>("task"));
        option.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadAllTaskes();

        paneVisability();

        tblTaskes.setStyle("-fx-selection-bar: blue;");

        tblTaskes.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    setData((TaskTm) newValue);
                }));


        btnToDo.setCursor(Cursor.HAND);

        notifications();
        setTime();


    }
    public  void setData(TaskTm taskesTM){
        tNumber.setText(String.valueOf(taskesTM.getId()));
        tName.setText(taskesTM.getTask());
        tTime.setText(taskesTM.getDate());
    }
    @FXML
    void newTaskOnAction(ActionEvent event) {

        addNedwtaskPane.setVisible(true);
        new ZoomIn(addNedwtaskPane).play();

notifications();


        btnDisablelityTrue();
    }

    public void tTimeOnAction(ActionEvent actionEvent) {

        String name = tName.getText();
        String time = tTime.getText();
        String value = String.valueOf(timepicker.getValue());
        int number = Integer.parseInt(tNumber.getText());

        Task task =new Task(number,name,value);

        try {
            TaskMange.addnewtask(task);
            new Alert(null,"Task Added successfuly");
        } catch (SQLException | ClassNotFoundException e) {

            System.out.println(e);
        }
        loadAllTaskes();

    }

     private void loadAllTaskes() {

        ObservableList<TaskTm> obLists = FXCollections.observableArrayList();

            try {
                for (Task c : TaskMange.loadall()) {

                     Button button=new Button("Done");

                    TaskTm tm = new TaskTm(c.getId(), c.getTask(), c.getDate(),button);


                    button.setOnAction(event -> {

                       tblTaskes.getItems().removeAll(tblTaskes.getSelectionModel().getSelectedItem());

                        try {
                            TaskMange.delete(tm.getTask());
                        } catch (SQLException | ClassNotFoundException e) {

                            System.out.println(e);

                        }
                    });
                    obLists.add(tm);
                    tblTaskes.setItems(obLists);
                }

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        public void paneVisability(){

        tblTaskes.setOnMouseClicked(event -> {
            btnDisablelityFalse();
            boolean visible = addNedwtaskPane.isVisible();
            if(visible==true){

                new ZoomOut(addNedwtaskPane).play();
                addNedwtaskPane.setVisible(false);
            }

            if(tblTaskes.getSelectionModel().getSelectedItems().isEmpty()){
                tblTaskes.getSelectionModel().clearSelection();
                }
        });
     }

        public void btnDisablelityTrue(){

        btnToDo.setOnMouseClicked(event -> {
            btnToDo.setDisable(true);
            btnToDo.setCursor(Cursor.HAND);
        });

        }
        public void btnDisablelityFalse(){

        btnPane.setOnMouseEntered(event -> {
            btnToDo.setDisable(false);
            btnToDo.setCursor(Cursor.HAND);
        });

        }

            Label lblTime =new Label();

    private void setTime(){


        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO, e-> {


            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");

            lblTime.setText(LocalDateTime.now().format(formatter));

            try {
                Task task = TaskMange.searchTime(lblTime.getText());

                if (task != null) {
                    String date = task.getDate();
                    String task1 = task.getTask();
                    if (lblTime.getText().equals(date)) {

                       // JOptionPane.showMessageDialog(null,task1);
                        //JOptionPane.showMessageDialog(null,task1);

                    }

                } else {

                }


            } catch (SQLException | ClassNotFoundException ex) {

                System.out.println(ex);
            }

        }),new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.getCurrentTime();
        btnPane.getChildren().add(lblTime);


    }

    public void notifications() {

        timepicker.setOnAction(event -> {
            LocalTime value = timepicker.getValue();
            System.out.println(value);
        });


    }

    public void timepickerOnAction(ActionEvent actionEvent) {


        String name = tName.getText();
        String time = tTime.getText();
        String value = String.valueOf(timepicker.getValue());
        int number = Integer.parseInt(tNumber.getText());

        Task task =new Task(number,name,value);

        try {
            TaskMange.addnewtask(task);
            new Alert(null,"Task Added successfuly");
        } catch (SQLException | ClassNotFoundException e) {

            System.out.println(e);
        }
        loadAllTaskes();


    }
}
