package com.example.sprint.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class Sprint implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("nama_sprint")
    private String title;

    @SerializedName("desc_sprint")
    private String desc;

    @SerializedName("tgl_mulai")
    private String dateStart;

    @SerializedName("tgl_selesai")
    private String dateEnd;

    public Sprint(){

    }

    protected Sprint(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        dateStart = in.readString();
        dateEnd = in.readString();
    }

    public static final Creator<Sprint> CREATOR = new Creator<Sprint>() {
        @Override
        public Sprint createFromParcel(Parcel in) {
            return new Sprint(in);
        }

        @Override
        public Sprint[] newArray(int size) {
            return new Sprint[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(dateStart);
        parcel.writeString(dateEnd);
    }
}
