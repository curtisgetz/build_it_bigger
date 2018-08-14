package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.curtisgetz.jokedisplay.TellJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokeTaskFinished, View.OnClickListener{

    private ProgressBar mProgress;
    private Button mJokeButton;
    private InterstitialAd mInterstitialAd;
    private String mJoke;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mJokeButton = (Button) root.findViewById(R.id.tell_joke_button);
        mJokeButton.setOnClickListener(this);

        mProgress = (ProgressBar) root.findViewById(R.id.progress);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        setupAd();

        mInterstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                startJokeDisplayActivity();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                startJokeDisplayActivity();
            }


        });



        hideJokeLoading();
        return root;
    }


    private void setupAd() {
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.testing_apunitid));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    private void startJokeDisplayActivity(){
        Intent intent = new Intent(getActivity(), TellJokeActivity.class);
        intent.putExtra(TellJokeActivity.JOKE_EXTRA_KEY, mJoke);
        startActivity(intent);
    }


    @Override
    public void onJokeTaskFinished(String joke) {
        mJoke = joke;
    }

    @Override
    public void onClick(View v) {
        showJokeLoading();
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        new EndpointAsyncTask(this).execute();
    }



    private void showJokeLoading(){
        mProgress.setVisibility(View.VISIBLE);
        mJokeButton.setEnabled(false);
    }

    private void hideJokeLoading(){
        mProgress.setVisibility(View.INVISIBLE);
        mJokeButton.setEnabled(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        hideJokeLoading();
    }


}
