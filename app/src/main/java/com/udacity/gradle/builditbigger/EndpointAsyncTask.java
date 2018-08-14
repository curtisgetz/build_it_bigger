package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.media.audiofx.LoudnessEnhancer;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private OnJokeTaskFinished mListener;
   // private Context mContext;


    public EndpointAsyncTask(OnJokeTaskFinished listener) {
        this.mListener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();

        }

        try {
            //return myApiService.sayHi("test").execute().getData();
            return myApiService.tellJoke().execute().getMyJoke();
        }catch (IOException e){
            // Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            return e.getMessage();
        }

    }




    @Override
    protected void onPostExecute(String result) {
        mListener.onJokeTaskFinished(result);
    }




}
