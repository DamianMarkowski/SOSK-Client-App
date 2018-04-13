package pl.damianmarkowski.sosk;

import android.app.Application;
import android.content.Context;

public class GlobalVars extends Application {

    public static String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static Context context;

    public static String hostUrl;

    public static String getCourseName() {
        return courseName;
    }

    public static void setCourseName(String courseName) {
        GlobalVars.courseName = courseName;
    }

    public static String courseName;

    public static int courseId;

    public static int userId;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        hostUrl = "http://soskpollubmobilna.unicloud.pl/";
    }

}