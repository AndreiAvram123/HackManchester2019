package com.example.hive.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Skill implements Parcelable {
    private int skillId;
    private String skillDifficulty;
    private String skillDescription;
    private String skillTitle;

    public Skill(String skillTitle, String skillDifficulty, String skillName) {
        this.skillTitle = skillTitle;
        this.skillDifficulty = skillDifficulty;
        this.skillDescription = skillName;
    }

    protected Skill(Parcel in) {
        skillId = in.readInt();
        skillDifficulty = in.readString();
        skillDescription = in.readString();
        skillTitle = in.readString();
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

    public void setSkillDifficulty(String skillDifficulty) {
        this.skillDifficulty = skillDifficulty;
    }

    public String getSkillDescription() {
        return this.skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public int getSkillId() {
        return this.skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
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
    }

}