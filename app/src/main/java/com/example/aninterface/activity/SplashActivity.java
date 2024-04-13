package com.example.aninterface.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.aninterface.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//
//        // Firebase initialization
//        try {
//            FileInputStream serviceAccount = new FileInputStream("D:\\Information Technology\\InterfaceGUI\\Interface\\app\\logininterface-cffd1-firebase-adminsdk-q0c9y-6f431c3fea.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://logininterface-cffd1-default-rtdb.firebaseio.com")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ImageView logoImage = findViewById(R.id.splashimage);
        ImageView canva_logo = findViewById(R.id.canva_image);

        AlphaAnimation fadeAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeAnimation.setDuration(1500);

        logoImage.setAnimation(fadeAnimation);
        logoImage.setVisibility(ImageView.VISIBLE);

        canva_logo.setAnimation(fadeAnimation);
        canva_logo.setVisibility(ImageView.VISIBLE);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, SPLASH_DURATION);
    }
}
