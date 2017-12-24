
package com.example.android.flavor;


import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.media.RemoteController;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.RemoteConnection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static com.example.android.flavor.R.*;
import static com.example.android.flavor.R.raw.song;
public class MainActivity extends AppCompatActivity {

    ComponentName C;

    private MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudio = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    i== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(i==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();

            }

            }

        private void releaseMediaPlayer() {
            if(mediaPlayer !=null) {
                mediaPlayer.release();
                mediaPlayer=null;

            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }

        private void releaseMediaPlayer() {
            if(mediaPlayer !=null) {
                mediaPlayer.release();
                mediaPlayer=null;

            }
            mAudioManager.abandonAudioFocus(mOnAudio);
        }

    };

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        androidFlavors.add(new AndroidFlavor("Donut", "$40", drawable.donut,
                R.raw.song1));
        androidFlavors.add(new AndroidFlavor("Eclair", "$5", drawable.eclair,
                R.raw.song2));
        androidFlavors.add(new AndroidFlavor("Froyo", "$50", drawable.froyo,
                R.raw.song3));
        androidFlavors.add(new AndroidFlavor("GingerBread", "$20", drawable.gingerbread,
                R.raw.song4));
        androidFlavors.add(new AndroidFlavor("Honeycomb", "$100", drawable.honeycomb,
                R.raw.song));
        androidFlavors.add(new AndroidFlavor("Ice Cream Sandwich", "$80", drawable.icecream,
                R.raw.song1));
        androidFlavors.add(new AndroidFlavor("Jelly Bean", "$30", drawable.jellybean,
                R.raw.song2));
        androidFlavors.add(new AndroidFlavor("KitKat", "$20", drawable.kitkat,
                R.raw.song3));
        androidFlavors.add(new AndroidFlavor("Lollipop", "$10", drawable.lollipop,
                R.raw.song4));
        androidFlavors.add(new AndroidFlavor("Marshmallow", "$25" + "", drawable.marshmallow,
                R.raw.song));

        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this, androidFlavors);

        ListView listView = (ListView) findViewById(id.listview_flavor);
        listView.setAdapter(flavorAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AndroidFlavor word= androidFlavors.get(i);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudio,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mAudioManager.registerMediaButtonEventReceiver(C);

                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, word.getmAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
                    }}


            private void releaseMediaPlayer() {
                if(mediaPlayer !=null) {
                    mediaPlayer.release();
                    mediaPlayer=null;

                }
                mAudioManager.abandonAudioFocus(mOnAudio);
            }

            }


        );
    }}

