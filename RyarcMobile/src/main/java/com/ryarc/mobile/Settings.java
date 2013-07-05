package com.ryarc.mobile;

public class Settings {
    private static String appURL = "http://192.168.0.13/RyarcMobile";

    public static void Load() {

    }

    public static String getAppURL() {
        return  appURL;
    }

    public static void saveAppURL(String newURL) {
        appURL = newURL;
    }
}
