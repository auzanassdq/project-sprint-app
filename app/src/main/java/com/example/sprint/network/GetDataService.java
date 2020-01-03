package com.example.sprint.network;

import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintList;
import com.example.sprint.model.TaskList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
