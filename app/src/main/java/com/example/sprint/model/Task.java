package com.example.sprint.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class Task implements Parcelable {
    private String title;
    private String desc;
    private String dateStart;
    private String dateEnd;
    private boolean isFinish;

    public Task() {
    }

    protected Task(Parcel in) {
        title = in.readString();
        desc = in.readString();
        dateStart = in.readString();
        dateEnd = in.readString();
        isFinish = in.readByte() != 0;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(dateStart);
        parcel.writeString(dateEnd);
        parcel.writeByte((byte) (isFinish ? 1 : 0));
    }
}
