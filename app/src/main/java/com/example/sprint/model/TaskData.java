package com.example.sprint.model;

import java.util.ArrayList;

/**
 * Created by auzan.
 */

public class TaskData {

    private static String[][] dataTask = new String[][]{
            {"Ngerjain ini", "Mudah"},
            {"Ngerjain itu", "Mudah"},
            {"ini lah", "Menengah"},
            {"itu lah", "Sulit"},
    };

    public static ArrayList<Task> getListData(){
        ArrayList<Task> list = new ArrayList<>();
        for (String[] aData : dataTask) {
            Task task = new Task();
            task.setTitle(aData[0]);
            task.setLevel(aData[1]);

            list.add(task);
        }

        return list;
    }
}
