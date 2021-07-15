package com.example.tweet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Splash extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0x100;
    private static final String TAG = Splash.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } catch(Exception ex){
            Log.e(TAG, ex.toString());
        }

        setContentView(R.layout.splash);

        Animator animator = Animator.getInstance();

        animator.rotateRight(findViewById(R.id.imgSplash));
        Animation ani = animator.rotateRight(findViewById(R.id.tvSplash));

        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public void toLogin(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(Splash.this,
                        getResources().getString(R.string.login_success),
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(Splash.this, MainActivity.class));
                // ...
            } else {
                Toast.makeText(Splash.this,
                        getResources().getString(R.string.login_failed), Toast.LENGTH_LONG).show();
            }
        }
    }
}