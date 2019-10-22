package com.example.sprint.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by auzan on 10/22/2019.
 * Github: @auzanassdq
 */
public class SprintList {
    @SerializedName("results")
    private ArrayList<Sprint> results;

    public ArrayList<Sprint> getResults() {
        return results;
    }

    public void setResults(ArrayList<Sprint> results) {
        this.results = results;
    }
}
