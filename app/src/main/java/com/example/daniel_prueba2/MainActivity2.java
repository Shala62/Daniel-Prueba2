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

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    private TextView nombre;
    private RadioButton noemi, noe, david, saul, salom;
    private CheckBox tigre, leon;
    private ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String dato = getIntent().getStringExtra("dato");
        nombre = findViewById(R.id.txtNombre);
        nombre.setText("Bienvenido " + dato);

        noemi = findViewById(R.id.rdoNoemi);
        noe = findViewById(R.id.rdoNoe);
        david = findViewById(R.id.rdoDavid);
        saul = findViewById(R.id.rdoSaul);
        salom = findViewById(R.id.rdoSalomon);
        tigre = findViewById(R.id.chkTigre);
        leon = findViewById(R.id.chkLeon);

        atras = findViewById(R.id.imgAtras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void Seguir(View view){
        int totalCheck = 0;

        //Contamos el total de CheckBoxes seleccionados
        if (noe.isChecked()) totalCheck++;
        if (noemi.isChecked()) totalCheck++;
        if (tigre.isChecked()) totalCheck++;
        if (leon.isChecked()) totalCheck++;
        if (david.isChecked()) totalCheck++;
        if (salom.isChecked()) totalCheck++;
        if (saul.isChecked()) totalCheck++;

        if (totalCheck < 3) {

            Toast.makeText(this, "Porfavor, responda antes de avanzar", Toast.LENGTH_LONG).show();

        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("Datos", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Primera
            if (noemi.isChecked()) {
                editor.putString("Primera", "Noemí");
            } else if (noe.isChecked()) {
                editor.putString("Primera", "Noe");
            }

            //Segunda
            if (tigre.isChecked()) {
                editor.putString("Segunda", "El Tigre");
            } else if (leon.isChecked()) {
                editor.putString("Segunda", "El León");
            }

            //Tercera
            if (david.isChecked()) {
                editor.putString("Tercera", "David");
            } else if (saul.isChecked()) {
                editor.putString("Tercera", "Saúl");
            } else if (salom.isChecked()) {
                editor.putString("Tercera", "Salomón");
            }
            editor.apply();

            //Mandamos el Nombre a la siguiente actividad para que cuando vuelva no le salga el "Bienvenido null"
            Intent intento = getIntent();
            String nombre = intento.getStringExtra("dato");
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("dato", nombre);
            startActivity(intent);
        }
    }
}