package br.edu.fateczl.atividade05_01;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    *@author: Gustavo Guimarães de Oliveira
     */

    private Spinner spQtdFaces;
    private RadioGroup rgQtds;
    private RadioButton rbQtd1;
    private RadioButton rbQtd2;
    private RadioButton rbQtd3;
    private Button btnRolar;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spQtdFaces = findViewById(R.id.spQtdFaces);
        rgQtds = findViewById(R.id.rgQtds);
        rbQtd1 = findViewById(R.id.rbQtd1);
        rbQtd2 = findViewById(R.id.rbQtd2);
        rbQtd3 = findViewById(R.id.rbQtd3);
        rbQtd1.setChecked(true);
        btnRolar = findViewById(R.id.btnRolar);
        tvResult = findViewById(R.id.tvResult);

        preencheSpinner();
        btnRolar.setOnClickListener(op -> rolar());
    }

    private void rolar() {
        tvResult.setText("");
        StringBuffer buffer = new StringBuffer();
        Integer qtdFaces = (Integer) spQtdFaces.getSelectedItem();
        int qtdDados = 0;
        if (rbQtd1.isChecked()) {
            qtdDados = 1;
        }
        else if (rbQtd2.isChecked()) {
            qtdDados = 2;
        }
        else if (rbQtd3.isChecked()) {
            qtdDados = 3;
        }
        System.out.println("Rolando " + qtdDados + " Dados");
        int totalPontos = 0;
        for (int dado = 1; dado <= qtdDados; dado++) {
            int valor = (int) (Math.random() * qtdFaces + 1);
            buffer.append(String.format("Dado %d: %d\n", dado, valor));
            totalPontos += valor;
        }
        buffer.append(String.format("Total de Pontos: %d\n", totalPontos));
        System.out.println(buffer.toString());
        tvResult.setText(buffer.toString());
    }

    private void preencheSpinner() {
        List<Integer> listaFaces  = new ArrayList<>();
        listaFaces.add(4);
        listaFaces.add(6);
        listaFaces.add(8);
        listaFaces.add(10);
        listaFaces.add(12);
        listaFaces.add(20);
        listaFaces.add(100);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaFaces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdFaces.setAdapter(adapter);
    }
}