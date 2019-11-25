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
    private String username;

    public Skill() {

    }


    public Skill(String skillTitle, String skillDifficulty, String skillDescription, String username) {
        this.skillTitle = skillTitle;
        this.skillDifficulty = skillDifficulty;
        this.skillDescription = skillDescription;
        this.username = username;
    }

    protected Skill(Parcel in) {
        skillId = in.readInt();
        skillDifficulty = in.readString();
        skillDescription = in.readString();
        skillTitle = in.readString();
        username = in.readString();
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

    public String getSkillTitle() {
        return skillTitle;
    }

    public String getSkillDifficulty() {
        return this.skillDifficulty;
    }

    public String getSkillDescription() {
        return this.skillDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public void setSkillDifficulty(String skillDifficulty) {
        this.skillDifficulty = skillDifficulty;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
        dest.writeString(username);
    }

}