package com.example.tarea_personaje;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCamisa, btnPantalon, btnZapatos;
    private ProgressBar progressCamisa, progressPantalon, progressZapatos;
    private TextView statusText, textCamisa, textPantalon, textZapatos;
    private ImageView camisaImageView, pantalonImageView, zapatosImageView;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontrar las vistas
        btnCamisa = findViewById(R.id.btnCamisa);
        btnPantalon = findViewById(R.id.btnPantalon);
        btnZapatos = findViewById(R.id.btnZapatos);
        progressCamisa = findViewById(R.id.progressCamisa);
        progressPantalon = findViewById(R.id.progressPantalon);
        progressZapatos = findViewById(R.id.progressZapatos);
        statusText = findViewById(R.id.statusText);
        textCamisa = findViewById(R.id.textCamisa);
        textPantalon = findViewById(R.id.textPantalon);
        textZapatos = findViewById(R.id.textZapatos);

        // Encontrar las ImageViews para las prendas
        camisaImageView = findViewById(R.id.camisaImageView);
        pantalonImageView = findViewById(R.id.pantalonImageView);
        zapatosImageView = findViewById(R.id.zapatosImageView);

        // Configurar botones
        btnCamisa.setOnClickListener(v -> vestirCamisa());
        btnPantalon.setOnClickListener(v -> vestirPantalon());
        btnZapatos.setOnClickListener(v -> vestirZapatos());
    }

    private void vestirCamisa() {
        btnCamisa.setEnabled(false);
        statusText.setText("Vistiendo camisa...");
        progressCamisa.setProgress(0);
        textCamisa.setText("0%");

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progreso = i;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }
                handler.post(() -> {
                    progressCamisa.setProgress(progreso);
                    textCamisa.setText(progreso + "%");
                    if (progreso == 100) {
                        btnCamisa.setText("Camisa ✓");
                        btnCamisa.setEnabled(true);
                        camisaImageView.setVisibility(View.VISIBLE);
                        statusText.setText("¡Camisa vestida exitosamente!");
                        actualizarEstadoGeneral();
                    }
                });
            }
        }).start();
    }

    private void vestirPantalon() {
        btnPantalon.setEnabled(false);
        statusText.setText("Vistiendo pantalón...");
        progressPantalon.setProgress(0);
        textPantalon.setText("0%");

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progreso = i;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }
                handler.post(() -> {
                    progressPantalon.setProgress(progreso);
                    textPantalon.setText(progreso + "%");
                    if (progreso == 100) {
                        btnPantalon.setText("Pantalón ✓");
                        btnPantalon.setEnabled(true);
                        pantalonImageView.setVisibility(View.VISIBLE);
                        statusText.setText("¡Pantalón vestido exitosamente!");
                        actualizarEstadoGeneral();
                    }
                });
            }
        }).start();
    }

    private void vestirZapatos() {
        btnZapatos.setEnabled(false);
        statusText.setText("Vistiendo zapatos...");
        progressZapatos.setProgress(0);
        textZapatos.setText("0%");

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progreso = i;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }
                handler.post(() -> {
                    progressZapatos.setProgress(progreso);
                    textZapatos.setText(progreso + "%");
                    if (progreso == 100) {
                        btnZapatos.setText("Zapatos ✓");
                        btnZapatos.setEnabled(true);
                        zapatosImageView.setVisibility(View.VISIBLE);
                        statusText.setText("¡Zapatos vestidos exitosamente!");
                        actualizarEstadoGeneral();
                    }
                });
            }
        }).start();
    }

    private void actualizarEstadoGeneral() {
        int completado = 0;
        if (camisaImageView.getVisibility() == View.VISIBLE) completado++;
        if (pantalonImageView.getVisibility() == View.VISIBLE) completado++;
        if (zapatosImageView.getVisibility() == View.VISIBLE) completado++;

        if (completado == 3) {
            statusText.setText("¡Personaje completamente vestido!");
        } else if (completado > 0) {
            statusText.setText("¡Personaje parcialmente vestido! (" + completado + "/3 prendas)");
        }
    }
}