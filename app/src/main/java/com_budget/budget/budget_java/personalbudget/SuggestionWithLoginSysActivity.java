package com_budget.budget.budget_java.personalbudget;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SuggestionWithLoginSysActivity extends AppCompatActivity {

    private MaterialButton btnLogin, btnSignUp;
    private GridLayout gridLogin;
    private TextView forgotTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PersonalBudget);
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_suggestion_with_login_sys);

        btnLogin = findViewById(R.id.btn_login);
        forgotTextBtn = findViewById(R.id.forgotTextBtn);
        gridLogin = findViewById(R.id.gridLogin);
        btnSignUp = findViewById(R.id.btn_sign_up);

        forgotTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        // define a click listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="text_sign_up"
                Pair<View, String> p1 = Pair.create((View)gridLogin, "grid_trans");
                Pair<View, String> p2 = Pair.create((View)btnSignUp, "text_sign_up");

                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(SuggestionWithLoginSysActivity.this, p1, p2);
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        });

    }
}