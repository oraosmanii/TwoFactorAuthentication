package com.example.twofactorauthentication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;




public class VerifyActivity extends AppCompatActivity {
    private EditText otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        otp = findViewById(R.id.otp);

        final Button verifyBtn = findViewById(R.id.verifyBtn);

        String generatedCode = getIntent().getStringExtra("otp");

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = otp.getText().toString();
                verifyOtp(enteredOtp, generatedCode);
            }
        });
    }

    private void verifyOtp(String enteredOtp, String generatedOtp){
        if (enteredOtp.equals(generatedOtp)) {
            Toast.makeText(VerifyActivity.this, "Valid OTP!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(VerifyActivity.this, SuccessActivity.class));
        } else {
            Toast.makeText(VerifyActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
        }
    }

}
