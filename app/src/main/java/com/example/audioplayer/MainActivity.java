package com.example.audioplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MusicAdapter.OnItemClickListener {

    private RecyclerView musicRecyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private MediaPlayer mediaPlayer;
    public static int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPosition = 0;

        musicRecyclerView = findViewById(R.id.musicRecyclerView);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        musicList = new ArrayList<>();

        musicList.add(new Music ("bad guy", "Billie Eilish", R.raw.music1));
        musicList.add(new Music("Believer", "Imagine Dragons", R.raw.music2));
        musicList.add(new Music ("Smells like teen spirit", "Nirvana", R.raw.music3));
        musicList.add(new Music("Пачка сигарет", "Кино", R.raw.music4));
        musicList.add(new Music ("Кукла колдуна", "Король и шут", R.raw.music5));



        musicAdapter = new MusicAdapter(musicList, (MusicAdapter.OnItemClickListener) this);
        musicRecyclerView.setAdapter(musicAdapter);
    }

    private int currentSongPosition = -1;
    private boolean isPaused = false;
    @Override
    public void onItemClick(int position) {
        if (position == currentSongPosition) {
            if (mediaPlayer != null) {
                if (isPaused) {
                    mediaPlayer.start();
                    Toast.makeText(this, "Продолжение воспроизведения", Toast.LENGTH_SHORT).show();
                    isPaused = false;
                } else {
                    mediaPlayer.pause();
                    Toast.makeText(this, "Пауза", Toast.LENGTH_SHORT).show();
                    isPaused = true;
                }
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            Music music = musicList.get(position);
            mediaPlayer = MediaPlayer.create(this, music.getFilePath());
            mediaPlayer.start();
            Toast.makeText(this, "Воспроизведение: " + music.getTitle(), Toast.LENGTH_SHORT).show();
            currentSongPosition = position;
            isPaused = false;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}