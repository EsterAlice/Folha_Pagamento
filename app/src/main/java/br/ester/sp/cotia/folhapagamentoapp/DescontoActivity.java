package br.ester.sp.cotia.folhapagamentoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescontoActivity extends AppCompatActivity {

    private Button btImprimir;
    private TextView sLiquido, plano,valeT, valeA, valeR, desInss, desIrrf, pNome;
    double  ps;
    double vt;
    double va;
    double vr;
    double inss;
    double irrf;
    double sl;
    String nome;

     @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_desconto);

        pNome = findViewById(R.id.nome1);
         plano =findViewById(R.id.ps);
         valeT=findViewById(R.id.vt);
         valeA = findViewById(R.id.va);
         valeR = findViewById(R.id.vr);
         desInss = findViewById(R.id.inss);
         desIrrf = findViewById(R.id.irrf);
         sLiquido=findViewById(R.id.sl);

         nome = getIntent().getStringExtra("nome");
         ps = getIntent().getDoubleExtra("tipo_plano", 0);
         vt = getIntent().getDoubleExtra("vt", 0);
         va = getIntent().getDoubleExtra("va", 0);
         vr = getIntent().getDoubleExtra("vr", 0);
         inss = getIntent().getDoubleExtra("calculo", 0);
         irrf = getIntent().getDoubleExtra("irrf", 0);
         sl = getIntent().getDoubleExtra("salario_liquido", 0 );

         pNome.setText(nome);
        sLiquido.setText(getResources().getString(R.string.text_sl )+ String.format(" R$ %.2f", sl));
        plano.setText(getResources().getString(R.string.text_ps )+ String.format(" R$ %.2f", ps));
        valeT.setText(getResources().getString(R.string.text_vt )+ String.format(" R$ %.2f", vt));
        valeR.setText(getResources().getString(R.string.text_vr )+ String.format(" R$ %.2f", vr));
        valeA.setText(getResources().getString(R.string.text_va )+ String.format(" R$ %.2f", va));
        desInss.setText(getResources().getString(R.string.text_inss )+ String.format(" R$ %.2f", inss));
        desIrrf.setText(getResources().getString(R.string.text_irrf )+ String.format(" R$ %.2f", irrf));

     }
}

