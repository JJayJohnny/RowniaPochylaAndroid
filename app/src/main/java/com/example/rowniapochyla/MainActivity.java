package com.example.rowniapochyla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double roundTwoDecimals(double x)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(x));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = (Button) findViewById(R.id.calculateButton);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText("Kąt \u03B1(\u02DA):");



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editWeight = (EditText) findViewById(R.id.editWeight);
                EditText editAngle = (EditText) findViewById(R.id.editAngle);
                EditText editWspol = (EditText) findViewById(R.id.editWspol);
                EditText editHeight = (EditText) findViewById(R.id.editHeight);

                TextView resultPrzysp = (TextView) findViewById(R.id.resultPrzysp);
                TextView resultSpeed = (TextView) findViewById(R.id.resultSpeed);
                TextView resultTime = (TextView) findViewById(R.id.resultTime);

                if(!editWeight.getText().toString().isEmpty() && !editAngle.getText().toString().isEmpty() && !editWspol.getText().toString().isEmpty() && !editHeight.getText().toString().isEmpty()) {
                    double m = Double.parseDouble(editWeight.getText().toString());
                    double angleDeg = Double.parseDouble(editAngle.getText().toString());
                    double wspolrzednaTarcia = Double.parseDouble(editWspol.getText().toString());
                    double angleRad = Math.toRadians(angleDeg);
                    double h = Double.parseDouble(editHeight.getText().toString());


                    if (angleDeg > 0 && angleDeg < 90 && wspolrzednaTarcia >= 0 && wspolrzednaTarcia <= 1 && m > 0 && h > 0) {
                        double Fs = m * 10 * Math.sin(angleRad);
                        double Fn = m * 10 * Math.cos(angleRad);
                        double T = Fn * wspolrzednaTarcia;
                        double a = (Fs - T) / m;
                        double s = h / Math.sin(angleRad);
                        double time = Math.sqrt(2 * s / a);
                        double V = a * time;

                        if(a<0) a=0.00;

                        resultPrzysp.setText("Przyspieszenie: " + roundTwoDecimals(a) + "(m/s\u00B2)");
                        resultSpeed.setText("Prędkość końcowa: " + roundTwoDecimals(V) + "(m/s)");
                        resultTime.setText("Czas zjazdu: " + roundTwoDecimals(time) + "(s)");
                    } else {
                        resultPrzysp.setText("Błędne dane!");
                        resultSpeed.setText("");
                        resultTime.setText("");
                    }
                } else
                {
                    resultPrzysp.setText("Wpisz dane!");
                    resultSpeed.setText("");
                    resultTime.setText("");
                }
            }

        });
    }
}
