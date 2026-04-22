package funs.gamez.pages;

import funs.gamez.mainview.DashSurfaceView;
import funs.gamez.R;
import funs.gamez.music.GameMusic;
import android.app.Activity;
import android.os.Bundle;

public class MainGameActivity extends Activity {
    private DashSurfaceView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gameView = findViewById(R.id.gameView);

        //初始化声音播放器
        GameMusic.inIt(this);
        //初始化音乐播放器
        GameMusic.inItMusic(R.raw.bg);
        //初始化音效播放器
        GameMusic.inItSound();
        //初始化风声播放器
        GameMusic.windMediaplayer();
        //初始化 run 播放器
        GameMusic.runMediaplayer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.onPagePause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onPageResume();
    }

}