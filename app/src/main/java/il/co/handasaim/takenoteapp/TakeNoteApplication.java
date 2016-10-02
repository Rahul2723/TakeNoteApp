package il.co.handasaim.takenoteapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * The application is used to properly initialize the parse service
 * Created by Eidan on 10/2/2016.
 */
public class TakeNoteApplication extends Application {
    /**
     * Parse Application ID (Identifies the application)
     */
    public static final String APPLICATION_ID = "jjzVOwnUbPNgzOmv21GXxjGnuEIzXl97dTaaY80z";
    /**
     * Parse server address
     */
    public static final String SERVER_ADDRESS = "https://parseapi.back4app.com/";
    /**
     * Prase client key (Identifies the client)
     */
    public static final String CLIENT_KEY = "JE55oqjEkjYEsg9SYYc6P6wE2zjR1QZetDY5liDT";

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Note.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APPLICATION_ID)
                .server(SERVER_ADDRESS)
                .clientKey(CLIENT_KEY)
                .build());
    }
}
