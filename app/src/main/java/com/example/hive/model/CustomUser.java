package com.example.hive.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class CustomUser implements Parcelable {
    private String username;
    private String email;
    private String profilePictureUri;


    //required for firebase
    public CustomUser(){

    }

    public CustomUser(String username, String email, String profilePictureUri) {
        this.username = username;
        this.email = email;
        this.profilePictureUri = profilePictureUri;
    }

    private CustomUser(Parcel in) {
        username = in.readString();
        email = in.readString();
        profilePictureUri = in.readString();
    }

    public static final Creator<CustomUser> CREATOR = new Creator<CustomUser>() {
        @Override
        public CustomUser createFromParcel(Parcel in) {
            return new CustomUser(in);
        }

        @Override
        public CustomUser[] newArray(int size) {
            return new CustomUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(profilePictureUri);
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

    public String getProfilePictureUri() {
        return profilePictureUri;
    }

    public void setProfilePictureUri(String profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }
}
