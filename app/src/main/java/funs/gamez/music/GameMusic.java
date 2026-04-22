package funs.gamez.music;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import funs.gamez.R;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class GameMusic {
    public static MediaPlayer mediaPlayer;//声明 背景音乐播放器
    public static SoundPool soundPool;//声明 音效播放器
    public static MediaPlayer mp;//播放风声播放器
    public static MediaPlayer mprun;//播放器

    private static boolean musicSwitch = true;//音乐开关
    private static boolean soundSwitch = true;//音效开关

    private static Map<Integer, Integer> soundMap; //音效资源id与加载过后的音源id的映射关系表

    //    private static Context context;
    private static WeakReference<Context> sCtxRef;

    //初始化 声音播放器
    public static void inIt(Context c) {
        sCtxRef = new WeakReference<>(c);
//        context = c;
    }

    //初始化风声播放器
    public static void windMediaplayer() {
        mp = MediaPlayer.create(getWFContext(), R.raw.wind);
        mp.setLooping(true);
    }

    private static Context getWFContext() {
        return null != sCtxRef ? sCtxRef.get() : null;
    }

    //初始化 run播放器
    public static void runMediaplayer() {
        mprun = MediaPlayer.create(getWFContext(), R.raw.run);
        mprun.setLooping(true);
    }

    //初始化背景音乐 播放器
    public static void inItMusic(int resid) {
        mediaPlayer = MediaPlayer.create(getWFContext(), resid);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.5f, 0.5f);
    }

    //初始化音效播放器
    public static void inItSound() {
        //第一个参数 10 表示 最多能够播放的音效数量
//        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool = createSoundPool();

        soundMap = new HashMap<Integer, Integer>();
        Context context = getWFContext();
        if (context == null) {
            return;
        }
        //将音效资源加入 soundPool，并做成soundMap 映射
        soundMap.put(R.raw.boss_die, soundPool.load(context, R.raw.boss_die, 1));
        soundMap.put(R.raw.boss_hurt, soundPool.load(context, R.raw.boss_hurt, 1));
        soundMap.put(R.raw.changed, soundPool.load(context, R.raw.changed, 1));
        soundMap.put(R.raw.changing, soundPool.load(context, R.raw.changing, 1));
        soundMap.put(R.raw.crow_hurt, soundPool.load(context, R.raw.crow_hurt, 1));
        soundMap.put(R.raw.dead, soundPool.load(context, R.raw.dead, 1));
        soundMap.put(R.raw.eat, soundPool.load(context, R.raw.eat, 1));
        soundMap.put(R.raw.enemy_die, soundPool.load(context, R.raw.enemy_die, 1));
        soundMap.put(R.raw.hurt, soundPool.load(context, R.raw.hurt, 1));
        soundMap.put(R.raw.jump, soundPool.load(context, R.raw.jump, 1));
        soundMap.put(R.raw.land, soundPool.load(context, R.raw.land, 1));
        soundMap.put(R.raw.run, soundPool.load(context, R.raw.run, 1));
        soundMap.put(R.raw.shoot, soundPool.load(context, R.raw.shoot, 1));
        soundMap.put(R.raw.sword, soundPool.load(context, R.raw.sword, 1));
        soundMap.put(R.raw.wind, soundPool.load(context, R.raw.wind, 1));
        soundMap.put(R.raw.woop, soundPool.load(context, R.raw.woop, 1));

    }

    private static SoundPool createSoundPool() {
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME) // 设置音效使用场景
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // 设置音效的类型

        SoundPool.Builder spb = new SoundPool.Builder();
        spb.setMaxStreams(10);
        spb.setAudioAttributes(attr);    //转换音频格式
        return spb.build();      //创建SoundPool对象
    }

    //暂停音乐
    public static void pauseMusic() {
//        if (mediaPlayer.isPlaying()) {
        if (isPlaying(mediaPlayer)) {
            mediaPlayer.pause();
        }
    }

    //暂停风声
    public static void pauseWind() {
//        if (mp.isPlaying()) {
        if (isPlaying(mp)) {
            mp.pause();
        }
    }

    //暂停 run
    public static void pauseRun() {
//        if (mprun.isPlaying()) {
        if (isPlaying(mprun)) {
            mprun.pause();
        }
    }

    private static boolean isPlaying(MediaPlayer mp) {
        try {
            return null != mp && mp.isPlaying();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    //播放音乐
    public static void startMusic() {
        if (musicSwitch) {
            mediaPlayer.start();
        }
    }

    //播放风声
    public static void startWind() {
        if (musicSwitch) {
            mp.start();
        }
    }

    //播放run
    public static void startRun() {
        if (musicSwitch) {
            mprun.start();
        }
    }

    //切换音乐 并播放
    public static void nextMusic(int resid) {
        releaseMusic();
        inItMusic(resid);
        startMusic();
    }


    //切换风声  并播放
    public static void nextWind(int resid) {
        releaseWind();
        windMediaplayer();
        startWind();
    }

    //切换run  并播放
    public static void nextrun(int resid) {
        releaseRun();
        runMediaplayer();
        startRun();
    }

    //释放音乐资源
    public static void releaseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    //释放风声资源
    public static void releaseWind() {
        if (mp != null) {
            mp.release();
        }
    }

    //释放run声资源
    public static void releaseRun() {
        if (mprun != null) {
            pauseRun();
            mprun.release();
        }
    }

    //设置音乐开关
    public static void setMusicSwitch(boolean musicSwitch) {
        GameMusic.musicSwitch = musicSwitch;
        if (GameMusic.musicSwitch) {
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
        }
    }

    //播放音效
    // resId raw文件夹中的 音效资源，
	/*loop —— 循环播放的次数，
     0为值播放一次，-1为无限循环，其他值为播放loop+1次(例如，3为一共播放4次).*/
    public static int playSound(int resId, int loop) {

        if (!soundSwitch) {
            return 0;
        }

        Integer soundId = soundMap.get(resId);
        if (soundId != null) {
            if (soundId == soundMap.get(R.raw.run))
                return soundPool.play(soundId, 1, 1, 1, loop, 0.7f);
            return soundPool.play(soundId, 1, 1, 1, loop, 1);
        } else {
            return 0;
        }


    }

    //暂停音效
    // streamID 表示 soundPool 音效池中 的id
    public static void pauseSound(int streamID) {

        if (streamID != 0) {
            soundPool.pause(streamID);
        }

    }

    //继续音效
    // streamID 表示 soundPool 音效池中 的id
    public static void resumeSound(int streamID) {

        if (streamID != 0) {
            soundPool.resume(streamID);
        }

    }

    //终止音效
    // streamID 表示 soundPool 音效池中 的id
    public static void stopSound(int streamID) {

        if (streamID != 0) {
            soundPool.stop(streamID);
        }

    }

    public static void releaseSound() {
        if (soundPool != null) {
            soundPool.release();
        }
    }

}
