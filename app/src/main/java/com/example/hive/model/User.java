package com.example.hive.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User implements Parcelable {

  private String username;
  private String email;
  private ArrayList<Skill> currentLearningSkills;
  private ArrayList<Skill> skillsToTeach;
  private double latitude;
  private double longitute;
  private String pictureUri;

  public User(){

  }

  public User(String username, String email, ArrayList<Skill> currentLearningSkills, ArrayList<Skill> skillsToTeach,
              double latitude, double longitude, String pictureUri) {
    this.username = username;
    this.email = email;
    this.currentLearningSkills = currentLearningSkills;
    this.skillsToTeach = skillsToTeach;
    this.longitute = longitude;
    this.latitude = latitude;
    this.pictureUri = pictureUri;
  }


  protected User(Parcel in) {
    username = in.readString();
    email = in.readString();
    currentLearningSkills = in.createTypedArrayList(Skill.CREATOR);
    skillsToTeach = in.createTypedArrayList(Skill.CREATOR);
    latitude = in.readDouble();
    longitute = in.readDouble();
    pictureUri = in.readString();
  }

  public static final Creator<User> CREATOR = new Creator<User>() {
    @Override
    public User createFromParcel(Parcel in) {
      return new User(in);
    }

    @Override
    public User[] newArray(int size) {
      return new User[size];
    }
  };

  public void addSkillToTeach(Skill skill){
    if(skillsToTeach==null) {
      skillsToTeach = new ArrayList<>();
    }
      skillsToTeach.add(skill);

}
  public void addInterest(Skill skill){
    if(currentLearningSkills ==null) {
      currentLearningSkills = new ArrayList<>();
    }
    currentLearningSkills.add(skill);

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(username);
    dest.writeString(email);
    dest.writeTypedList(currentLearningSkills);
    dest.writeTypedList(skillsToTeach);
    dest.writeDouble(latitude);
    dest.writeDouble(longitute);
    dest.writeString(pictureUri);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<Skill> getCurrentLearningSkills() {
    return currentLearningSkills;
  }

  public void setCurrentLearningSkills(ArrayList<Skill> currentLearningSkills) {
    this.currentLearningSkills = currentLearningSkills;
  }

  public ArrayList<Skill> getSkillsToTeach() {
    return skillsToTeach;
  }

  public void setSkillsToTeach(ArrayList<Skill> skillsToTeach) {
    this.skillsToTeach = skillsToTeach;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitute() {
    return longitute;
  }

  public void setLongitute(double longitute) {
    this.longitute = longitute;
  }

  public String getPictureUri() {
    return pictureUri;
  }

  public void setPictureUri(String pictureUri) {
    this.pictureUri = pictureUri;
  }
}
