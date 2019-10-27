package com.example.hive.fragments;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * This is a base class used to build fragments
 * such as a LOGIN fragment or a Sign up Fragment
 */
public abstract class AuthenticationFragment extends Fragment {


    public  abstract void attemptAction();

   public abstract void clearFields();

   public abstract void initializeUI(View layout);

    /**
     * This method should call the customiseField()
     * for the every EditText with its corresponding
     * TextView
     */
    public abstract void customiseFields();

    public abstract void configureButtons();


    public abstract void displayErrorMessage(String message);

    public abstract void toggleLoadingBar();



}
