package com.example.emreoguz.singsong;

import android.media.MediaPlayer;

/**
 * Created by Emre on 26.5.2017.
 */

public class SoundFile {
    private String soundName;
    private String pathFile;
    private MediaPlayer mySoundPlay;

    public SoundFile(){

    }

    public SoundFile(String soundName){
       this.soundName=soundName;
    }


    public String getName(){
        return soundName;
    }


    public void Start(MediaPlayer mp,int pathName){

        pathFile= "";
        try {
            mySoundPlay=mp;
            //mySoundPlay.setDataSource(R.raw.herseyiyak);
            //mySoundPlay.prepare();
            mySoundPlay.start();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SoundFile Sınıfı Start Methodu Hata");
        }
    }

    public void Stop(){
        try {
            mySoundPlay.stop();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SoundFile Sınıfı Stop Methodu Hata");
        }
    }
}
