package sankalpa.TaskManager.to;

public class Task {

    private int id;
    private String task;
    private String date;

    public Task() {
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

    public Task(int id, String task, String date) {
        this.id = id;
        this.task = task;
        this.date = date;
    }
}
