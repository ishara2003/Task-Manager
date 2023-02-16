package sankalpa.TaskManager.moduls;

import sankalpa.TaskManager.Util.CrudUtil;
import sankalpa.TaskManager.tm.TaskTm;
import sankalpa.TaskManager.to.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskMange {
    public static boolean addnewtask(sankalpa.TaskManager.to.Task task) throws SQLException, ClassNotFoundException {

      return  CrudUtil.execute("insert into taskes values(?,?,?)",

      task.getId(),
      task.getTask(),
      task.getDate()

                );

    }

    public static ArrayList<Task> loadall() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM taskes");
        ArrayList<sankalpa.TaskManager.to.Task> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new sankalpa.TaskManager.to.Task(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return list;

    }
    public static ArrayList<TaskTm> SeeAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM taskes");
        ArrayList<TaskTm> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new TaskTm(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return list;

    }


    public static void delete(String b) throws SQLException, ClassNotFoundException {

        CrudUtil.execute("delete from taskes where task='"+b+"'");

    }

    public static Task searchTime(String time) throws SQLException, ClassNotFoundException {

        ResultSet ex = CrudUtil.execute("SELECT * FROM taskes WHERE time='"+time+"'");

        while (ex.next()){
            return new Task(
            ex.getInt(1),
            ex.getString(2),
            ex.getString(3));
        }

        return null;
    }
}
