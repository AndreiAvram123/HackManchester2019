package com.example.hive.model;

public class Badge {
  private int badgeId;
  private int badgeLevel;
  private String badgeName;

  public Badge(int badgeLevel, String badgeName, int badgeId) {
    this.badgeLevel = badgeLevel;
    this.badgeName = badgeName;
    this.badgeId = badgeId;
  }

  public int getBadgeLevel() {
    return this.badgeLevel;
  }

  public void setBadgeLevel(int badgeLevel) {
    this.badgeLevel = badgeLevel;
  }

  public int getBadgeId() {
    return this.badgeId;
  }

  public void setBadgeId(int badgeId) {
    this.badgeId = badgeId;
  }

  public String getBadgeName() {
    return this.badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  } 
}