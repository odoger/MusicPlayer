package com.example.emreoguz.singsong;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.webkit.ConsoleMessage;

import java.io.Console;
import java.io.IOException;

/**
 * Created by Emre on 26.5.2017.
 */

public class SoundRecorder  {
    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private  MediaPlayer myAudioPlay;
   public SoundRecorder(){
        myAudioRecorder= new MediaRecorder();
        myAudioPlay=new MediaPlayer();
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myMusic.3gp";
        System.out.println("SAKLAMA YERİ" + outputFile);
    }

    //This method sets the attributes MediaRecorder
    private void setSource(String soundName){
        try {
            myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            myAudioRecorder.setOutputFile(outputFile);
        }catch (Exception ex){
            ex.toString();
            ex.printStackTrace();
        }

    }


    public void Start(String soundName){
        myAudioRecorder=new MediaRecorder();
        setSource(soundName);
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IllegalStateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SoundRecorder Sınıfı Start Methodu Hata");
        }
    }


    public void Stop(){
        try {
            myAudioRecorder.stop();
            myAudioRecorder.release();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SoundRecorder Sınıfı Stop Methodu Hata");
        }

    }
    public void Play(){
        try {
            myAudioPlay.setDataSource(outputFile);
            myAudioPlay.prepare();
            myAudioPlay.start();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SoundRecorder Sınıfı Play Methodu Hata");
        }

    }

    public void DontPlay(){
        try {
            myAudioPlay.stop();
            myAudioPlay.release();
            //myAudioPlay.release();
        }catch (Exception ex){
            System.out.println("SoundRecorder sınıfı DontPlay methodu hata");
        }
    }
}


