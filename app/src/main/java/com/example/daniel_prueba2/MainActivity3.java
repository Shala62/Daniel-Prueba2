package com.example.daniel_prueba2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    private RadioButton master, son, profeta;
    private CheckBox lot, jonas, jona, pedro, juan, santiago;
    private ImageView atras2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        master = findViewById(R.id.rdoMaster);
        son = findViewById(R.id.rdoSon);
        profeta = findViewById(R.id.rdoProfeta);
        lot = findViewById(R.id.chkLot);
        jonas = findViewById(R.id.chkJonas);
        jona = findViewById(R.id.chkJona);
        pedro = findViewById(R.id.chkPedro);
        juan = findViewById(R.id.chkJuan);
        santiago = findViewById(R.id.chkSanti);

        atras2 = findViewById(R.id.imgAtras2);
        atras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = getIntent();
                String nombre = intento.getStringExtra("dato");
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                intent.putExtra("dato", nombre);
                startActivity(intent);
            }
        });

    }

    public void Seguir2(View view){
        int totalCheck = 0;

        //Contamos el total de CheckBoxes seleccionados
        if (master.isChecked()) totalCheck++;
        if (profeta.isChecked()) totalCheck++;
        if (son.isChecked()) totalCheck++;
        if (santiago.isChecked()) totalCheck++;
        if (pedro.isChecked()) totalCheck++;
        if (juan.isChecked()) totalCheck++;
        if (lot.isChecked()) totalCheck++;
        if (jona.isChecked()) totalCheck++;
        if (jonas.isChecked()) totalCheck++;

        if (totalCheck < 3) {

            Toast.makeText(this, "Porfavor, responda antes de avanzar", Toast.LENGTH_LONG).show();

        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("Datos2", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Cuarta
            if (jonas.isChecked()) {
                editor.putString("Cuarta", "Jonás");
            } else if (lot.isChecked()) {
                editor.putString("Cuarta", "Lot");
            } else if (jona.isChecked()) {
                editor.putString("Cuarta", "Jonattan");
            }

            //Quinta
            if (pedro.isChecked()) {
                editor.putString("Quinta", "Pedro");
            } else if (juan.isChecked()) {
                editor.putString("Quinta", "Juan");
            } else if (santiago.isChecked()) {
                editor.putString("Quinta", "Santiago");
            }

            //Sexta
            if (master.isChecked()) {
                editor.putString("Sexta", "Un Maestro");
            } else if (profeta.isChecked()) {
                editor.putString("Sexta", "Un Profeta");
            } else if (son.isChecked()) {
                editor.putString("Sexta", "El Hijo de Dios");
            }
            editor.apply();
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(intent);
        }
    }
}