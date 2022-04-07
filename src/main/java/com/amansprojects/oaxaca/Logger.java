package com.amansprojects.oaxaca;

import java.util.Date;

public class Logger {
    @SuppressWarnings("deprecation")
    public static void log(String s) {
        Date d = new Date();
        System.out.println("["+String.format("%02d", d.getHours())+":"+String.format("%02d", d.getMinutes())+":"+String.format("%02d", d.getSeconds())+"] " + s);
    }
}
