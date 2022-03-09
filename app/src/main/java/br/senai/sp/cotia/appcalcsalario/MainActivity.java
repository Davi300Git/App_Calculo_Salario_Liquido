package br.senai.sp.cotia.appcalcsalario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.atomic.DoubleAdder;
public class MainActivity extends AppCompatActivity {

    private EditText etInfoDados, etDependentes;
    private Button bt_calc;
    private Spinner spinnerPS;
    private double salarioBase, porcentagem, sal_liqui;
    private TextView tvnome;
    private RadioGroup rbVR, rbVA, rbVT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Atribuindo os valores no Spinner
        spinnerPS = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPS.setAdapter(adapter);


        tvnome = findViewById(R.id.tvEntrada);
        bt_calc = findViewById(R.id.btCalcular);
        spinnerPS = findViewById(R.id.spinner);
        etDependentes = findViewById(R.id.etDependentes);
        etInfoDados = findViewById(R.id.etInfoDados);
        rbVR = findViewById(R.id.rgVR);
        rbVA = findViewById(R.id.rgVA);
        rbVT = findViewById(R.id.rgVT);


        // Onclick do Botão
        bt_calc.setOnClickListener(v -> {

            double salBruto = Double.parseDouble((etInfoDados.getText().toString()));
            double dependentes = Double.parseDouble(etDependentes.getText().toString());
            double vr = 0, vt, va, inss = 0, irrf, planoSaude = 0;
            double salarioLiquido;
            String PS = spinnerPS.getSelectedItem().toString();
            switch (PS) {
                case "Standard":
                    if (salBruto <= 3000.00) {
                        planoSaude = 60.00;
                    } else {
                        planoSaude = 80.00;
                    }
                    break;

                case "Básico":
                    if (salBruto <= 3000.00) {
                        planoSaude = 80.00;
                    } else {
                        planoSaude = 110.00;
                    }
                    break;

                case "Super":
                    if (salBruto <= 3000.00) {
                        planoSaude = 95.00;
                    } else {
                        planoSaude = 135.00;
                    }
                    break;

                case "Master":
                    if (salBruto <= 3000.00) {
                        planoSaude = 130.00;
                    } else {
                        planoSaude = 180.00;
                    }
                    break;

                default:
                    break;
            }

            if (rbVT.getCheckedRadioButtonId() == R.id.temVT ) {
                vt = (0.06 * salBruto);
            } else {
                vt = 0;
            }
            if (rbVR.getCheckedRadioButtonId() == R.id.temVR ) {
                if (salBruto <= 3000) {
                    vr = 22 * 2.60;
                } else if (salBruto <= 5000) {
                    vr = 22 * 3.65;
                } else if (salBruto <= 5001) {
                    vr = 22 * 6.50;
                }
            } else {
                vr = 0;
            }
            if (rbVA.getCheckedRadioButtonId() == R.id.temVA ) {
                if (salBruto <= 3000) {
                    va = 15.00;
                } else if (salBruto <= 5000) {
                    va = 25.00;
                } else {
                    va = 35.00;
                }
            } else {
                va = 0;
            }

            if (salBruto <= 1212) {
                inss = 0.075 * salBruto;
            } else if (salBruto >= 1212.01) {
                inss = 0.09 * salBruto + 18.18;
            } else if (salBruto >= 2427.36) {

            } else if (salBruto >= 3641.04) {
                inss = 0.14 * salBruto + 163.82;
            } else {
                inss = 0;

            }

            irrf = salBruto - inss - (189.59 * dependentes);
            if (irrf <= 1903.99) {
                irrf = 0;
            } else if (irrf <= 2826.65) {
                irrf = irrf * 0.075 - 142.80;
            } else if
            (irrf <= 3751.05) {
                irrf = irrf * 0.15 - 354.80;
            } else if (irrf <= 4664.68) {
                irrf = irrf * 0.225 - 636.13;

            } else {
                irrf = irrf * 0.275 - 869.36;

            }
            sal_liqui = salBruto - inss - vt - va - vr - irrf - planoSaude;



            Intent intent = new Intent(this, Result.class);

            intent.putExtra("salLiquido", String.format("R$ %.2f", sal_liqui));
            intent.putExtra("salBruto", String.format("R$ %.2f", salBruto));
            intent.putExtra("inss", String.format("R$ %.2f", inss));
            intent.putExtra("irrf", String.format("R$ %.2f", irrf));
            intent.putExtra("planoSaude", String.format("R$ %.2f", planoSaude));
            intent.putExtra("vale_re", String.format("R$ %.2f", vr));
            intent.putExtra("vale_al", String.format("R$ %.2f", va));
            intent.putExtra("vale_tr", String.format("R$ %.2f", vt));

            startActivity(intent);
        });
    }
}