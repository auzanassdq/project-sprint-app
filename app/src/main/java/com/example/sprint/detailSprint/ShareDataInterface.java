package com.example.sprint.detailSprint;

import com.example.sprint.model.Task;

/**
 * Created by auzan on 1/13/2020.
 * Github: @auzanassdq
 */
public interface ShareDataInterface {
    void postData(Task task);
    void getTask(Task task);
    void putTask(Task task);
    void checkTask(Task task);
    void deleteData(Task task);
}
