package com.example.hive.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Skill implements Parcelable {
    private int skillId;
    private String skillDifficulty;
    private String skillDescription;
    private String skillTitle;
    private CustomUser user;

    public Skill(){

    }

    public Skill(String skillTitle, String skillDifficulty, String skillDescription,CustomUser user) {
        this.skillTitle = skillTitle;
        this.skillDifficulty = skillDifficulty;
        this.skillDescription = skillDescription;
        this.user = user;
    }

    protected Skill(Parcel in) {
        skillId = in.readInt();
        skillDifficulty = in.readString();
        skillDescription = in.readString();
        skillTitle = in.readString();
        user = in.readParcelable(CustomUser.class.getClassLoader());
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(skillId);
        dest.writeString(skillDifficulty);
        dest.writeString(skillDescription);
        dest.writeString(skillTitle);
        dest.writeParcelable(user,flags);

    }

    public String getSkillDifficulty() {
        return skillDifficulty;
    }

    public void setSkillDifficulty(String skillDifficulty) {
        this.skillDifficulty = skillDifficulty;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }
}