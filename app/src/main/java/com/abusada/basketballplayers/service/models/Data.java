package com.abusada.basketballplayers.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("height_feet")
    private Integer heightFeet;
    @SerializedName("height_inches")
    private Integer heightInches;
    private Integer id;
    @SerializedName("last_name")
    private String lastName;
    private String position;
    private Team team;
    @SerializedName("weight_pounds")
    private Integer weightPounds;

    public Data(){

    }

    protected Data(Parcel in) {
        firstName = in.readString();
        if (in.readByte() == 0) {
            heightFeet = null;
        } else {
            heightFeet = in.readInt();
        }
        if (in.readByte() == 0) {
            heightInches = null;
        } else {
            heightInches = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        lastName = in.readString();
        position = in.readString();
        team = in.readParcelable(Team.class.getClassLoader());
        if (in.readByte() == 0) {
            weightPounds = null;
        } else {
            weightPounds = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        if (heightFeet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(heightFeet);
        }
        if (heightInches == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(heightInches);
        }
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(lastName);
        dest.writeString(position);
        dest.writeParcelable(team, flags);
        if (weightPounds == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(weightPounds);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Integer weightPounds) {
        this.weightPounds = weightPounds;
    }
}
