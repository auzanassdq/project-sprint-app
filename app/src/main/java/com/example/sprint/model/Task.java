package com.example.sprint.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class Task implements Parcelable  {

    @SerializedName("id")
    private int id;

    @SerializedName("sprint_id")
    private int sprintId;

    @SerializedName("nama_task")
    private String title;

    @SerializedName("kesulitan_id")
    private String kesulitanId;

    @SerializedName("status")
    private boolean status;

    @SerializedName("create_at")
    private String createAt;

    @SerializedName("update_at")
    private String updateAt;

    public Task() {
    }

    protected Task(Parcel in) {
        id = in.readInt();
        sprintId = in.readInt();
        title = in.readString();
        kesulitanId = in.readString();
        status = in.readBoolean();
        createAt = in.readString();
        updateAt = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKesulitanId() {
        return kesulitanId;
    }

    public void setKesulitanId(String kesulitanId) {
        this.kesulitanId = kesulitanId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(sprintId);
        parcel.writeString(title);
        parcel.writeString(kesulitanId);
        parcel.writeBoolean(status);
        parcel.writeString(createAt);
        parcel.writeString(updateAt);
    }
}
