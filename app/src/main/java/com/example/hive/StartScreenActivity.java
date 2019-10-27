package com.example.hive;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.hive.fragments.LoginFragment;
import com.example.hive.fragments.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class StartScreenActivity extends AppCompatActivity implements
        LoginFragment.LoginFragmentCallback, SignUpFragment.SignUpFragmentCallback {

    private FirebaseAuth firebaseAuth;
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_activity);

        prepareFragments();
        displayLoginFragment();

    }

    /**
     * This method is used to initialize the LoginFragment
     * and the SignUpFragment along with the fragmentManager
     */
    private void prepareFragments() {
        loginFragment = LoginFragment.newInstance();
        signUpFragment = SignUpFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * This method is called in order to
     * show the login fragment
     */
    private void displayLoginFragment() {
        fragmentManager.beginTransaction().
                replace(R.id.start_screen_placeholder, loginFragment)
                .addToBackStack(null)
                .commit();

    }


    /**
     * This method is used to check weather or not there
     * is internet connection available in the given context
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }



    /**
     * This method pushes a login request with email and password to Firebase
     * IF we were able to log in the user,we check if his email address is verified
     * or not.If the email is not verified we call the method displayVerificationEmailDialog().If the
     * user has his email verified we call startMainActivity()
     * <p>
     * IF we were unable to log in the use we display a simple toast
     * telling him that the  login details are invalid
     *
     * @param email
     * @param password
     */
    private void pushLoginRequest(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                            startMainActivity();
                        } else {
                            loginFragment.displayErrorMessage(getString(R.string.error_email_not_verified));
                        }
                    } else {
                        loginFragment.displayErrorMessage(getString(R.string.error_invalid_login_details));
                    }
                    loginFragment.toggleLoadingBar();
                });
    }

    /**
     * This method pushed a signUp request to Firebase in order
     * to register a new user with email and password.Moreover
     * after we successfully added a new user to our database
     * we update his profile
     *
     * @param email
     * @param password
     * @param nickname
     */
    private void pushSignUpRequest(String email, String password, String nickname,int pictureID) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseAuth.getCurrentUser().sendEmailVerification();
                        updateNicknameAndProfilePicture(nickname,pictureID);
                        getSupportFragmentManager().popBackStack();
                    } else {
                        signUpFragment.displayErrorMessage(getString(R.string.error_create_account));
                    }
                    signUpFragment.toggleLoadingBar();
                });
    }

    private void updateNicknameAndProfilePicture(String nickname,int pictureId) {
        Uri uri = null;
        switch (pictureId) {
            case 1: {
                uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hive-ec30d.appspot.com/o/profile_picture1.png?alt=media&token=fc2b4ade-0564-4ab8-b1f1-e70ddd9ce7fb");
                break;
            }
            case 2: {
                uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hive-ec30d.appspot.com/o/profile_picture2.png?alt=media&token=526e4d60-24f6-4273-884f-423cff92a616");
                break;
            }
            case 3: {
                uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hive-ec30d.appspot.com/o/profile_picture3.png?alt=media&token=5fc7ebe1-d875-4600-b319-514a1a48f005");
                break;
            }
            case 4: {
                uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hive-ec30d.appspot.com/o/profile_picture4.png?alt=media&token=af89ddd9-ab71-4f29-91ef-d24dc13ff166");
                break;
            }
            default:
                uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hive-ec30d.appspot.com/o/profile_picture1.png?alt=media&token=fc2b4ade-0564-4ab8-b1f1-e70ddd9ce7fb");


        }
        firebaseAuth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder()
                .setDisplayName(nickname)
                .setPhotoUri(uri)
                .build());

    }



    /**
     * The method start the MainActivity
     * IT  SETS THE FLAGS FOR THE INTENT IN ORDER TO DESTROY THIS
     * ACTIVITY AFTER STARTING THE INTENT
     */
    private void startMainActivity() {
        Intent startMainActivityIntent = new Intent(this, MainActivity.class);
        startMainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startMainActivityIntent);
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null && currentUser.isEmailVerified()) {
            startMainActivity();
        }
    }





    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void login(String email, String password) {
        if (isNetworkAvailable()) {
            pushLoginRequest(email, password);
        } else {
            loginFragment.toggleLoadingBar();
            loginFragment.displayErrorMessage(getString(R.string.no_internet_connection));
        }
    }

    /**
     * This method is called in order to
     * show the signUpFragment
     */
    @Override
    public void showSignUpFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.start_screen_placeholder, signUpFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void signUp(String email, String password, String nickname,int pictureID) {
        if (isNetworkAvailable()) {
            pushSignUpRequest(email, password, nickname,pictureID);
        } else {
            signUpFragment.toggleLoadingBar();
        }
    }
}
