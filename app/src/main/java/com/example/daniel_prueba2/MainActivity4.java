package com.example.daniel_prueba2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity4 extends AppCompatActivity {

    private EditText comentario;
    private TextView resultados;
    private ImageView save, exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultados = findViewById(R.id.txtRespuestas);
        comentario = findViewById(R.id.etComentario);

        SharedPreferences sharedPreferences = getSharedPreferences("Datos", MODE_PRIVATE);
        String primera = sharedPreferences.getString("Primera", "");
        String segunda = sharedPreferences.getString("Segunda","");
        String tercera = sharedPreferences.getString("Tercera","");

        boolean primeraCorrecto = primera.equals("Noe");
        boolean segundaCorrecto = segunda.equals("El León");
        boolean terceraCorrecto = tercera.equals("Saúl");

        String resultadoAc = "Resultados:\n";
        resultadoAc += "1. " + primera;
        resultadoAc += primeraCorrecto ? " (Correcto)\n" : " (Incorrecto, era Noe)\n";
        resultadoAc += "2. " + segunda;
        resultadoAc += segundaCorrecto ? " (Correcto)\n" : " (Incorrecto, era El León)\n";
        resultadoAc += "3. " + tercera;
        resultadoAc += terceraCorrecto ? " (Correcto)\n" : " (Incorrecto, era Saúl)\n";

        SharedPreferences sharedPreferences2 = getSharedPreferences("Datos2", MODE_PRIVATE);
        String cuarta = sharedPreferences2.getString("Cuarta", "");
        String quinta = sharedPreferences2.getString("Quinta","");
        String sexta = sharedPreferences2.getString("Sexta","");

        boolean cuartaCorrecto = cuarta.equals("Jonás");
        boolean quintaCorrecto = quinta.equals("Juan");
        boolean sextaCorrecto = sexta.equals("El Hijo de Dios");

        resultadoAc += "4. " + cuarta;
        resultadoAc += cuartaCorrecto ? " (Correcto)\n" : " (Incorrecto, era Jonás)\n";
        resultadoAc += "5. " + quinta;
        resultadoAc += quintaCorrecto ? " (Correcto)\n" : " (Incorrecto, era el Apóstol Juan)\n";
        resultadoAc += "6. " + sexta;
        resultadoAc += sextaCorrecto ? " (Correcto)\n" : " (Incorrecto, Él es el Hijo del \nDios viviente)\n";
        resultados.setText(resultadoAc);


        String archivos[] = fileList();
        if (ArchivosExistentes(archivos, "Notas.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String NotasCompleta = "";
                while (linea != null){
                    NotasCompleta = NotasCompleta + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                comentario.setText(NotasCompleta);
            } catch (IOException e){

            }
        }
        exit = findViewById(R.id.imgSalir);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private boolean ArchivosExistentes(String[] archivos, String NombreArchivo) {
        for(int i = 0; i < archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;

    }

    public void save(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Notas.txt", Activity.MODE_PRIVATE));
            archivo.write(comentario.getText().toString());
            archivo.flush();
            archivo.close();

        } catch (IOException e){

        }
        Toast.makeText(this, "Comentario Guardado", Toast.LENGTH_SHORT).show();

    }
}