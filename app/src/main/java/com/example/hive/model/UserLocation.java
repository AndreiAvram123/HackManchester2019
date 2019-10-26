package com.example.hive.model;

public class UserLocation {
  private int longitude;
  private int latitude;
  
  public UserLocation(int longitude, int latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public int getLongitude() {
    return this.longitude;
  }

  public void setLongitude(int longitude) {
    this.longitude = longitude;
  }

  public int getLatitude() {
    return this.latitude;
  }

  public void setLatitude(int latitude) {
    this.latitude = latitude;
  }
}