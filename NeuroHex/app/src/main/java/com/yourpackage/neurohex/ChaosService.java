package com.yourpackage.neurohex;

import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class ChaosService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            while (true) {
                try {
                    // Vibrate
                    Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));

                    // Log
                    Log.e("CHAOS", "Chaotic Service Running...");

                    // Sound
                    playNoise();

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return START_STICKY;
    }

    private void playNoise() {
        int sampleRate = 8000;
        byte[] noise = new byte[1024];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = (byte)(Math.random() * 255);
        }

        AudioTrack track = new AudioTrack(
            AudioManager.STREAM_MUSIC, sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_8BIT,
            noise.length,
            AudioTrack.MODE_STATIC
        );
        track.write(noise, 0, noise.length);
        track.play();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
