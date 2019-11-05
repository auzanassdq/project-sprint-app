package com.example.sprint.network;

import com.example.sprint.model.SprintList;
import com.example.sprint.model.TaskList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by auzan on 7/12/2019.
 * Github: @auzanassdq
 */
public interface GetDataService {
    @GET("sprints")
    Observable<SprintList> getAllSprint();

    @GET("tasks")
    Observable<TaskList> getAllTask();
}
