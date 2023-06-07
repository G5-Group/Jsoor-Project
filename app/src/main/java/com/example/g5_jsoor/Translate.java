package com.example.g5_jsoor;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import java.io.IOException;

    public class Translate extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PreviewCallback {

        private static final int CAMERA_PERMISSION_CODE = 100;
        private Camera camera;
        private SurfaceView surfaceView;
        private SurfaceHolder surfaceHolder;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_translate);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.translate);
            // Set the toolbar as the app bar for the activity
            setSupportActionBar(toolbar);
            // Enable the up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Set the navigation click listener using lambda expression
            toolbar.setNavigationOnClickListener(v -> onBackPressed());


            surfaceView = findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(this);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Camera permission not granted
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, CAMERA_PERMISSION_CODE);
            } else {
                // Camera permission already granted
                openCamera();
            }
        }

        private void openCamera() {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(surfaceHolder);
                camera.setPreviewCallback(this);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error opening camera", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (requestCode == CAMERA_PERMISSION_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Camera permission granted
                    openCamera();
                } else {
                    // Camera permission denied
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();

                }
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // Do nothing
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // Do nothing
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.setPreviewCallback(null);
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        }

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            // Process camera frame data here
        }



    }