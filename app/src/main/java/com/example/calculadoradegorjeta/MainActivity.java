package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorjeta;
    private TextView textTotal;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        //Adicionar listener para SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //Abaixo igualo o valor de porcentagem ao progress da seek bar
                porcentagem = progress;
                //abaixo eu uso o metodo Math.round para deixar o double porcentagem sem exibir decimal
                textPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular (){

        String valorRecuperado = editValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "digite um valor primeiro!",
                    Toast.LENGTH_LONG).show();
        } else {
            //converter string recuperado para double
            double valorDigitado = Double.parseDouble( valorRecuperado );

            //calcular a gorjeta total
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;
            //exibir gorjeta e total
            textGorjeta.setText("R$" + Math.round(gorjeta));
            textTotal.setText("R$" + total);
        }


    }

}
