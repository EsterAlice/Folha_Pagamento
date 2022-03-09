package br.ester.sp.cotia.folhapagamentoapp;

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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editApp,editBruto, editDepen,editNome;
    private Button btCalcular, btLimpar, btDesconto;
    private TextView sLiquido, calculoInss;
    private Spinner spinner_Plano;
    private RadioGroup radioVt,radioVa, radioVr;
    private RadioButton simVt, naoVt, simVa, naoVa, simVr, naoVr;
    double salBruto, depen, ps, vt, va, vr, inss, irrf, bs, sl;
    String nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar os componentes na view
        editNome=findViewById(R.id.nome);
        editBruto=findViewById(R.id.edit_bruto);
        editDepen= findViewById(R.id.edit_dependentes);
        spinner_Plano =findViewById(R.id.spinner_plano);
        radioVt=findViewById(R.id.edit_vt1);
        simVt = findViewById(R.id.radio_sim_vt);
        naoVt = findViewById(R.id.radio_nao_vt);
        simVa = findViewById(R.id.radio_sim_va);
        naoVa = findViewById(R.id.radio_nao_va);
        simVr = findViewById(R.id.radio_sim_vr);
        naoVr = findViewById(R.id.radio_nao_vr);
        radioVa=findViewById(R.id.edit_va1);
        radioVr=findViewById(R.id.edit_vr1);
        btCalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);
        btDesconto = findViewById(R.id.bt_desconto);
        sLiquido=findViewById(R.id.salarioLiquido);
        calculoInss = findViewById(R.id.calculoInss);




        btCalcular.setOnClickListener(v->{
            //Erro
            if(editBruto.getText().toString().isEmpty()){
                editBruto.setError("Informe o salário bruto");
                Toast.makeText(getBaseContext(),"Informe o salário bruto", Toast.LENGTH_SHORT).show();
            }else if(editDepen.getText().toString().isEmpty()) {
                editDepen.setError("Informe o número de dependentes");
                Toast.makeText(getBaseContext(), "Informe o número de dependentes", Toast.LENGTH_SHORT).show();
            }else {
                //Salario Bruto
                 salBruto = Double.parseDouble(editBruto.getText().toString());
                 depen = Double.parseDouble(editDepen.getText().toString());

                //Desconto Plano de Saude
                switch (spinner_Plano.getSelectedItemPosition()) {
                    case 1:
                        if (salBruto < 3000) {
                            ps = 60;
                        } else {
                            ps = 80;
                        }
                        break;
                    case 2:
                        if (salBruto < 3000) {
                            ps = 80;
                        } else {
                            ps = 110;
                        }
                        break;
                    case 3:
                        if (salBruto < 3000) {
                            ps = 95;
                        } else {
                            ps = 135;
                        }
                        break;
                    case 4:
                        if (salBruto < 3000) {
                            ps = 130;

                        } else {
                            ps = 180;

                        }
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + spinner_Plano.getSelectedItemPosition());
                }

                switch (radioVt.getCheckedRadioButtonId()) {
                    case R.id.radio_sim_vt:
                        vt = salBruto * 0.06;
                        break;
                    case R.id.radio_nao_vt:
                        vt = 0;
                        break;

                }

                switch (radioVa.getCheckedRadioButtonId()) {
                    case R.id.radio_sim_va:
                        if (salBruto <= 3000) {
                            va = 15;
                        } else if (salBruto <= 5000) {
                            va = 25;
                        } else {
                            va = 35;
                        }
                        break;
                    case R.id.radio_nao_va:
                        va = 0;
                        break;
                }

                switch (radioVr.getCheckedRadioButtonId()) {
                    case R.id.radio_sim_vr:
                        if (salBruto <= 3000) {
                            vr = 2.60 * 22;
                        } else if (salBruto <= 5000) {
                            vr = 3.65 * 22;
                        } else {
                            vr = 6.50 * 22;
                        }
                        break;
                    case R.id.radio_nao_vr:
                        vr = 0;
                        break;
                }


                if (salBruto <= 1212.00) {
                    inss = salBruto * 0.075;
                } else if (salBruto <= 2427.35) {
                    inss = salBruto * 0.09 - 18.18;
                } else if (salBruto <= 3641.03) {
                    inss = salBruto * 0.12 - 91;
                } else if (salBruto <= 7087.22) {
                    inss = salBruto * 0.14 - 163.82;
                } else {
                    inss = 828.39;
                }


                bs = salBruto - inss - (189.59 * depen);
                if (salBruto <= 1903.98) {
                    irrf = 0;
                } else if (salBruto <= 2826.65) {
                    irrf = bs * 0.075 - 142.80;
                } else if (salBruto <= 3751.05) {
                    irrf = bs * 0.15 - 354.80;
                } else if (salBruto <= 4664.68) {
                    irrf = bs * 0.225 - 636.13;
                } else {
                    irrf = bs * 0.275 - 869.36;
                }
                
                calculoInss.setText(String.format("%.2f", inss));

                sl = salBruto - inss - vt - vr - va - irrf - ps;
                /*sLiquido.setText(String.format("%.2f", sl));*/
                sLiquido.setText(getResources().getString(R.string.text_sl )+ String.format(" R$ %.2f", sl));
                calculoInss.setText(getResources().getString(R.string.text_inss )+ String.format(" R$ %.2f", inss));
                btDesconto.setVisibility(View.VISIBLE);
            }
            });
        btLimpar.setOnClickListener(v -> {

            editDepen.setText("");
            editBruto.setText("");
            simVr.setChecked(false);
            naoVr.setChecked(false);
            simVt.setChecked(false);
            naoVt.setChecked(false);
            simVa.setChecked(false);
            naoVa.setChecked(false);
            btDesconto.setVisibility(View.INVISIBLE);
            calculoInss.setText("");
            sLiquido.setText("");
        });
        btDesconto.setOnClickListener(view -> {
        Intent intent = new Intent(this, DescontoActivity.class);
            intent.putExtra("nome", editNome.getText().toString());
            intent.putExtra("salario_liquido", sl);
            intent.putExtra("num_dependentes", depen);
            intent.putExtra("tipo_plano", ps);
            intent.putExtra("vt", vt);
            intent.putExtra("va", va);
            intent.putExtra("vr", vr);
            intent.putExtra("calculo", inss);
            intent.putExtra("irrf", irrf);

            startActivity(intent);
        });
        }


}




















