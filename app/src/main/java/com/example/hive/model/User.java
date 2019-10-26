package com.example.hive.model;

import com.example.hive.model.Skill;
import com.example.hive.model.UserLocation;

import java.util.ArrayList;

public class User {
  private int id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNum;
  private UserLocation location;
  private ArrayList<Skill> skillsToLearn;
  private ArrayList<Skill> skillsToTeach;
  private ArrayList<Donation> donations;
  private Badge badge;

  public User(int id, String username, String firstName, String lastName, String email, String phoneNum, UserLocation location, ArrayList<Skill> skillsToLearn, ArrayList<Skill> skillsToTeach, Badge badge) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNum = phoneNum;
    this.location = location;
    this.skillsToLearn = skillsToLearn;
    this.skillsToTeach = skillsToTeach;
    this.badge = badge;
  }



  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNum() {
    return this.phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public UserLocation getLocation() {
    return this.location;
  }

  public void setLocation(UserLocation location) {
    this.location = location;
  }

  public ArrayList<Skill> getSkillsToLearn() {
    return this.skillsToLearn;
  }

  public void setSkillsToLearn(ArrayList<Skill> skillsToLearn) {
    this.skillsToLearn = skillsToLearn;
  }

  public ArrayList<Donation> getDonations() {
    return this.donations;
  }

  public void setDonations(ArrayList<Donation> donations) {
    this.donations = donations;
  }

  public ArrayList<Skill> getSkillsToTeach() {
    return this.skillsToTeach;
  }

  public void setSkillsToTeach(ArrayList<Skill> skillsToTeach) {
    this.skillsToTeach = skillsToTeach;
  }

  public Badge getBadge() {
    return this.badge;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  // receiveDonation used to update the User's donation balance
  public void receiveDonation(Donation donation){
    donations.add(donation);
  }

  // sendDonation used send donations to a User
  public void sendDonation(User receiver, Donation donation) {
    receiver.receiveDonation(donation);
  }
}
