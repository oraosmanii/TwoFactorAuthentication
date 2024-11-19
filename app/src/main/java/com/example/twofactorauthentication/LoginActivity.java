package com.example.twofactorauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import javax.mail.MessagingException;

public class LoginActivity extends AppCompatActivity {

    private EmailSender emailSender = new EmailSender();
    private String userEmail="senderEmail";  //email ku do te dergohet one time password
    private String userPassword ="Password"; //password i rendomte per demonstrim login

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        final EditText emailInput = findViewById(R.id.email);
        final EditText passwordInput = findViewById(R.id.password);
        final Button logInBtn = findViewById(R.id.logInBtn);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email = emailInput.getText().toString().trim();
                String pass = passwordInput.getText().toString().trim();
                if (email.isEmpty()|| userPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter log in data", Toast.LENGTH_SHORT).show();
                    return;
                } else if(email.equals(userEmail) && pass.equals(userPassword)){
                    String otp = generateCode();
                    sendEmail(email, otp);

                    Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("otp", otp);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid log in data", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private String generateCode() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    private void sendEmail(String email, String otp) {
        new Thread(() -> {
            try {
                emailSender.sendEmail(email, otp);
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "OTP sent to " + email, Toast.LENGTH_SHORT).show());
            } catch (MessagingException e) {
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Failed to send OTP", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        }).start();
    }
}