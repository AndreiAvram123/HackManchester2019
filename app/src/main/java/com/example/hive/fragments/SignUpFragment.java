package com.example.hive.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.hive.R;
import com.example.hive.model.Utilities;

public class SignUpFragment  extends AuthenticationFragment {

    private EditText emailField;
    private EditText passwordField;
    private EditText reenteredPasswordField;
    private EditText nicknameField;
    private Button finishButton;
    private ProgressBar progressBar;
    private TextView errorTextView;
    private ImageView backImage;
    private SignUpFragmentCallback signUpFragmentCallback;
    private  ImageView picture1;
    private  ImageView picture2;
    private  ImageView picture3;
    private  ImageView picture4;
    private int profilePictureSelected = 0;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_sign_up, container, false);

        signUpFragmentCallback = (SignUpFragmentCallback) getActivity();


        initializeUI(layout);

        return layout;

    }


    @Override
    public void configureButtons() {
        finishButton.setOnClickListener(view -> attemptAction());
        backImage.setOnClickListener(image -> getFragmentManager().popBackStack());
    }

    @Override
    public void displayErrorMessage(String message) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(message);
    }

    /**
     * Once the user has pressed the finishButton and
     * the credentials are valid it may take some time
     * until Firebase processes our SignUp request(usually this does not happen)
     * We hide the finishButton  and show the loadingBar
     * until Firebase has processed the request
     * !!!!!!!!!
     * THIS METHOD IS ALSO CALLED FROM @activity StartScreenActivity
     * HAS BEEN PROCESSED
     */
    @Override
    public void toggleLoadingBar() {
        if (finishButton.getVisibility() == View.VISIBLE) {
            finishButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            finishButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
        clearFields();
    }


    private void initializeViews(View layout) {

        emailField = layout.findViewById(R.id.email_field_sign_up);
        passwordField = layout.findViewById(R.id.password_field_sign_up);
        reenteredPasswordField = layout.findViewById(R.id.reentered_password_field);
        nicknameField = layout.findViewById(R.id.nickname_field);

        finishButton = layout.findViewById(R.id.finish_sign_up);
        progressBar = layout.findViewById(R.id.loading_progress_bar_sign_up);
        errorTextView = layout.findViewById(R.id.error_message_sign_up);
        backImage = layout.findViewById(R.id.back_image_sign_up);

         picture1 = layout.findViewById(R.id.profile_picture1);
         picture2 = layout.findViewById(R.id.profile_picture2);
         picture3 = layout.findViewById(R.id.profile_picture3);
         picture4 = layout.findViewById(R.id.profile_picture4);

        picture1.setOnClickListener(view ->{
            clearPictures();
            profilePictureSelected =1;
            picture1.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.custom_image_view_background));
    });
        picture2.setOnClickListener(view ->{
            clearPictures();
            profilePictureSelected =2;
            picture2.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.custom_image_view_background));
        });
        picture3.setOnClickListener(view ->{
            clearPictures();
            profilePictureSelected =3;
            picture3.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.custom_image_view_background));
        });
        picture4.setOnClickListener(view ->{
            clearPictures();
            profilePictureSelected =4;
            picture4.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.custom_image_view_background));
        });

        configureButtons();
    }
    private void clearPictures(){
        picture1.setBackground(null);
        picture2.setBackground(null);
        picture3.setBackground(null);
        picture4.setBackground(null);
    }


    private boolean areCredentialsValid(String email, String password, String reenteredPassword,
                                        String nickname) {

        if (!Utilities.isEmailValid(email)) {
            displayErrorMessage(getString(R.string.error_invalid_email));
            return false;
        }
        if (password.isEmpty()) {
            displayErrorMessage(getString(R.string.no_password));
            return false;
        }
        if (!password.equals(reenteredPassword)) {
            displayErrorMessage(getString(R.string.password_match));
            return false;
        }
        if (nickname.isEmpty()) {
            displayErrorMessage(getString(R.string.error_no_nickname));
        }
        return true;

    }


    /**
     * ACTION - SIGN UP
     */
    @Override
    public void attemptAction() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String reenteredPassword = reenteredPasswordField.getText().toString().trim();
        String nickname = nicknameField.getText().toString().trim();
        if (areCredentialsValid(email, password, reenteredPassword, nickname)) {
            toggleLoadingBar();
            signUpFragmentCallback.signUp(email, password, nickname,profilePictureSelected);

        }
    }

    @Override
    public void clearFields() {
        emailField.setText("");
        passwordField.setText("");
        reenteredPasswordField.setText("");
        nicknameField.setText("");
    }

    @Override
   public void initializeUI(View layout) {
        initializeViews(layout);
        customiseFields();
        configureButtons();
    }

    @Override
   public void customiseFields() {

    }

    public interface SignUpFragmentCallback {
        void signUp(String email, String password, String nickname,int pictureID);
    }

}
