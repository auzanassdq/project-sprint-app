package com.example.sprint.network;

import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintList;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by auzan on 7/12/2019.
 * Github: @auzanassdq
 */
public interface GetDataService {
    @GET("sprints")
    Call<SprintList> getAllSprint();

    @GET("tasks")
    Call<TaskList> getAllTask();

//    @POST("sprints")
//    @FormUrlEncoded
//    Call<Sprint> postSprint(@Field("nama_sprint") String title,
//                            @Field("desc_sprint") String desc,
//                            @Field("tgl_mulai") String dateStart,
//                            @Field("tgl_selesai") String dateEnd);

    @POST("sprints")
    Call<Sprint> postSprint(@Body Sprint sprint);

    @POST("tasks")
    Call<Task> postTask(@Body Task task);

    @PUT("tasks/{task_id}")
    Call<Task> putTask(@Body Task task,
                       @Path("task_id") int id);

    @DELETE("tasks/{task_id}")
    Call<Task> deleteTask(@Path("task_id") int id);
}
