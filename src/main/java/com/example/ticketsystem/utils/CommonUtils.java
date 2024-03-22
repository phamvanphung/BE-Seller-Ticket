package com.example.ticketsystem.utils;

import java.util.Random;

import static java.lang.Math.random;

public class CommonUtils {

    private final static Random random = new Random();

    public static String getOTP(){
        int otp = random.nextInt(999998) + 1;
        return String.format("%06d",otp);
    }

    public static String getCodeVoucher(){
        Long code = random.nextLong(9999999998L) + 1;
        return String.format("%010d",code);
    }
}
