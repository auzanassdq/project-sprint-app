package com.example.sprint.model;

import java.util.ArrayList;

/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class SprintData {

    private static String[][] dataSprint = new String[][]{
            {"Fitur 1"},
            {"Fitur 2"},
            {"Fitur 3"},
            {"Fitur 4"},
    };

    public static ArrayList<Sprint> getListData(){
        ArrayList<Sprint> list = new ArrayList<>();
        for (String[] aData : dataSprint) {
            Sprint sprint = new Sprint();
            sprint.setTitle(aData[0]);

            list.add(sprint);
        }

        return list;
    }
}
