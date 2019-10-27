package com.example.hive.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
  private String username;
  private String email;
  private ArrayList<String> interests;
  private ArrayList<Skill> skillsToTeach;
  private double latitude;
  private double longitute;
  private String pictureUri;


  public User(String username, String email, ArrayList<String> interests, ArrayList<Skill> skillsToTeach,
              double latitude, double longitude,String pictureUri) {
    this.username = username;
    this.email = email;
    this.interests = interests;
    this.skillsToTeach = skillsToTeach;
    this.longitute = longitude;
    this.latitude = latitude;
    this.pictureUri = pictureUri;
  }



  public User(){
    //required
  }

  public String getPictureUri() {
    return pictureUri;
  }

  public void setPictureUri(String pictureUri) {
    this.pictureUri = pictureUri;
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

  public ArrayList<String> getInterests() {
    return interests;
  }

  public void setInterests(ArrayList<String> interests) {
    this.interests = interests;
  }

  public ArrayList<Skill> getSkillsToTeach() {
    return skillsToTeach;
  }

  public void setSkillsToTeach(ArrayList<Skill> skillsToTeach) {
    this.skillsToTeach = skillsToTeach;
  }
}
