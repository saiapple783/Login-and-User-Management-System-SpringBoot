package com.loginProject.loginProject.Service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    private final Map<String,String> otpTempStore = new HashMap<>();
    private final Random random= new Random();
    public String generateOtp(String mobilenum){
        String otp = String.format("%06d", random.nextInt(1000000));
        otpTempStore.put(mobilenum,otp);
        return otp;
    }

    //validate otp and also clear otp hashmap

    public boolean ValidateOtp(String mobilenum,String otp){
        return otpTempStore.containsKey(mobilenum) && otpTempStore.get(mobilenum).equals(otp);
    }
    public void clearOtp(String mobileNumber) {
        otpTempStore.remove(mobileNumber);
    }
}
