package bubblechatappteam.bubblechatapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Pádi on 2015. 11. 21..
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VNjSQF1bmPOZfWUP0lBnJnPhkA1ow9WEOFKXTkWa", "6ayW6X1yWdonD1m272xkcJ8u3guODWn6gX6TKqYr");
    }
}
