package com.example.sprint.detailSprint;

import com.example.sprint.model.Task;

/**
 * Created by auzan on 1/13/2020.
 * Github: @auzanassdq
 */
public interface ShareDataInterface {
    void postData(Task task);
    void updateData(Task task);
    void deleteData(Task task);
    void getTask(Task task);
}
