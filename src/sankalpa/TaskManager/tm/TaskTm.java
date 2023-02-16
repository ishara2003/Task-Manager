package sankalpa.TaskManager.tm;

import javafx.scene.control.Button;

public class TaskTm {

    private int id;
    private String task;
    private String date;

   private Button button;

    public TaskTm(int anInt, String string, String resultSetString) {
    }

    public TaskTm(int id, String task, String date, Button button) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.button = button;
    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
