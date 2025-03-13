package dev.colabgroup.android;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Formulario_registro extends AppCompatActivity {
    private EditText inputName, inputDni, inputAddress, inputAge, inputCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.formulario_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get all references
        inputName = findViewById(R.id.editTextText);
        inputDni = findViewById(R.id.editTextText2);
        inputAddress = findViewById(R.id.editTextText3);
        inputAge = findViewById(R.id.editTextText4);
        inputCourse = findViewById(R.id.editTextText5);
    }


}