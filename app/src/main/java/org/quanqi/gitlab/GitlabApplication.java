package org.quanqi.gitlab;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.quanqi.gitlab.models.GitlabSession;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Created by cindy on 10/22/14.
 */
public class GitlabApplication extends Application {

    public static GitLabService gitLabService;
    private static GitlabApplication instance;
    RestAdapter restAdapter;
    private String serverUrl;

    public static GitlabApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final Sprinkles sprinkles = Sprinkles.init(getApplicationContext());
        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase sqLiteDatabase) {

                int version = sqLiteDatabase.getVersion();

                switch (version) {
                    case 0:
                        sqLiteDatabase.setVersion(1);
                    case 1:
                        Log.i("FF", "ff");
                        break;
                }
            }
        });

        instance = this;
        setEndPoint("https://gitlab.com/api/v3");
    }

    public void setEndPoint(String endPoint) {
        this.serverUrl = endPoint;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(serverUrl)
                .setConverter(new GsonConverter(gson))
                .build();

        gitLabService = restAdapter.create(GitLabService.class);
    }

}
