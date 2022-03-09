package br.senai.sp.cotia.appcalcsalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    private TextView tvLiquido,  tvVR, tvVA, tvVT, tvPS, tvIRRF, tvINSS, tvBruto;
    private String salaLiquido, valeRe, valeAl, valeTrans, valePS, iRRF, iNSS, salaBruto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvLiquido = findViewById(R.id.exibeLiquido);
        tvVR = findViewById(R.id.exibeVR);
        tvVA = findViewById(R.id.exibeVA);
        tvVT = findViewById(R.id.exibeVT);
        tvPS = findViewById(R.id.exibePS);
        tvIRRF = findViewById(R.id.exibeIRRF);
        tvINSS = findViewById(R.id.exibeINSS);
        tvBruto = findViewById(R.id.exibeBruto);

        salaLiquido = getIntent().getStringExtra("salLiquido");
        valeRe = getIntent().getStringExtra("vale_re");
        valeAl = getIntent().getStringExtra("vale_al");
        valeTrans = getIntent().getStringExtra("vale_tr");
        valePS = getIntent().getStringExtra("planoSaude");
        iRRF = getIntent().getStringExtra("irrf");
        iNSS = getIntent().getStringExtra("inss");
        salaBruto = getIntent().getStringExtra("salBruto");

        tvLiquido.setText("Seu salario Liquido é " + salaLiquido);
        tvVR.setText("vale Refeição" + valeRe);
        tvVA.setText("Seu vale alimentação" +valeAl);
        tvPS.setText("Seu plano de saúde " + valePS);
        tvVT.setText("Seu vale transporte" + valeTrans);
        tvIRRF.setText("O IRRF" +iRRF);
        tvINSS.setText("O INSS" +iNSS);
        tvBruto.setText("O Salario Bruto" + salaBruto);
    }
}