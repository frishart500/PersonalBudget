package com_budget.budget.budget_java.personalbudget;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class AdditionActivity extends AppCompatActivity {

    private ImageView backToMain;
    private CheckBox checkBoxExpense, checkBoxIncome;
    private MaterialButton btnUpload, btnCancel;
    private TextInputLayout categoryTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        init();
        setBtnClicks();
        //goes to previous activity
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setBtnClicks(){
        //multiple onclick listener
        View.OnClickListener BTNsAdditionClick = new View.OnClickListener() {
            boolean switchingVar = true;
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    //goes to previous activity
                    case R.id.btn_cancel:
                        onBackPressed();
                        break;
                    case R.id.checkBoxIncome:
                        if(checkBoxExpense.isChecked()){
                            checkBoxExpense.setChecked(false);
                        }
                        if(switchingVar){
                            categoryTextInput.setEnabled(false);
                            switchingVar = false;
                        }else{
                            categoryTextInput.setEnabled(true);
                            switchingVar = true;
                        }
                        break;
                    case R.id.checkBoxExpense:
                        if(checkBoxIncome.isChecked()){
                            checkBoxIncome.setChecked(false);
                        }
                        categoryTextInput.setEnabled(true);
                        break;
                }
            }
        };
        checkBoxIncome.setOnClickListener(BTNsAdditionClick);
        checkBoxExpense.setOnClickListener(BTNsAdditionClick);
        btnCancel.setOnClickListener(BTNsAdditionClick);
    }

    //initialization function:
    private void init(){
        //checkboxes
        checkBoxExpense = findViewById(R.id.checkBoxExpense);
        checkBoxIncome = findViewById(R.id.checkBoxIncome);
        //buttons
        btnUpload = findViewById(R.id.btn_upload);
        btnCancel = findViewById(R.id.btn_cancel);
        backToMain = findViewById(R.id.backToMainBtn);
        //text inout layout
        categoryTextInput = findViewById(R.id.categoryTextInput);
    }

}