package com.example.schoolscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;

public class pickRingTone extends AppCompatActivity {

    private SoundPool ringTones;
    private int ring1, ring2, ring3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_ring_tone);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            AudioAttributes audioAttributes = new AudioAttributes().Builder()
//                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
//                    .set
//        }
//        ringTones
    }
}