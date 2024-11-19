
# Two-Factor Authentication Using OTP

## Overview
This Android project demonstrates a Two-Factor Authentication (2FA) system using One-Time Passwords (OTP) sent via email.

### Features
1. **Login Validation**: Checks email and password.
2. **OTP Generation**: Creates a unique 6-digit OTP for authentication.
3. **Email Sending**: Sends OTP to the user's email via Gmail's SMTP server.
4. **OTP Verification**: Verifies the OTP before granting access.

### Key Classes
- **LoginActivity**: Handles user login and OTP generation.
- **EmailSender**: Sends the OTP email using JavaMail API.
- **VerifyActivity**: Verifies the OTP entered by the user.

### Setup
1. Update `EmailSender` with your Gmail and app password.
2. Replace demo credentials in `LoginActivity`.
3. Add this dependency to `build.gradle`:
   ```groovy
   implementation 'javax.mail:mail:1.4.7'
   ```
4. Run the app in Android Studio.



---