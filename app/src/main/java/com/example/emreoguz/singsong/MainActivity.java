package com.example.emreoguz.singsong;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.net.URL;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.*;
public class MainActivity extends AppCompatActivity {

    Button btnSoundStart;
    Button btnSoundStop;
    Button btnSoundPlay;
    TextView txtView;
    ListView listViewMusics;
    private SoundRecorder soundRecord;
    private SoundFile soundFile;
    MediaPlayer mp;
    private int musicPosition = -1;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //GUI'lerimiz
        txtView = (TextView) findViewById(R.id.txtView);
        btnSoundStart = (Button) findViewById(R.id.btnSoundStart);
        btnSoundStop = (Button) findViewById(R.id.btnSoundStop);
        btnSoundPlay = (Button) findViewById(R.id.btnSoundPlay);
        listViewMusics = (ListView) findViewById(R.id.listMusics);
        listViewMusics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicPosition = position;
                Toast.makeText(getApplicationContext(), "Parça Seçildi. ", Toast.LENGTH_SHORT).show();
                if (position == 0)
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.herseyiyak);
                else if (position == 1)
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.firuze);
                else if (position == 2)
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ahistanbul);
                else if (position == 3)
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.giybet);
                else
                    Toast.makeText(getApplicationContext(), "Parça Seçilmedi.", Toast.LENGTH_LONG).show();
            }
        });
        //Threadlerimiz
      /* final Thread threadPlay= new Thread(new TaskPlay());
        final Thread threadRecord=new Thread(new TaskRecord());*/


        //Kayıt Alma
        btnSoundStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPosition == -1)
                    Toast.makeText(getApplicationContext(), "Lütfen Parça Seçiniz.", Toast.LENGTH_LONG).show();
                else {
                    soundRecord = new SoundRecorder();
                    soundFile = new SoundFile();

                    txtView.setText("KAYIT ALINIYOR");

                    //Müzik dosyası çalınıyor.
                    soundFile.Start(mp, musicPosition);
                    //Sesimiz alınıyor.
                    soundRecord.Start("deneme");


                    //Button işlemleri
                    btnSoundStart.setEnabled(false);
                    btnSoundStop.setEnabled(true);
                    btnSoundPlay.setEnabled(true);
                }
            }
        });

        /*final Thread threadStop = new Thread(new TaskStop());
        final Thread threadRecordStop = new Thread(new TaskRecordStop());*/
        //Kayıtı Durdurma
        btnSoundStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnSoundStart.isEnabled()) {
                    soundFile.Stop();
                    soundRecord.Stop();
                } else
                    soundRecord.DontPlay();

                txtView.setText("KAYIT BİTTİ");
                btnSoundStart.setEnabled(true);
                btnSoundStop.setEnabled(false);
                btnSoundPlay.setEnabled(true);

            }
        });

        //Kaydı Çalma

        btnSoundPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundRecord != null) {
                    soundRecord.Play();
                    txtView.setText("KAYIT ÇALINIYOR" + i);
                    i += 1;
                    btnSoundStart.setEnabled(true);
                    btnSoundStop.setEnabled(true);
                    btnSoundPlay.setEnabled(true);
                    txtView.setText("KAYIT ÇALINIYOR");
                } else {
                    txtView.setText("ÇALINACAK PARÇA YOK");
                }
            }
        });

    }
}


