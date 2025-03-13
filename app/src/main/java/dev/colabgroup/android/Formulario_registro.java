package dev.colabgroup.android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.colabgroup.android.logic.Registers;
import static dev.colabgroup.android.logic.Registers.Register;

public class Formulario_registro extends AppCompatActivity {
    private EditText inputName, inputDni, inputAddress, inputAge, inputCourse;
    private Button submitButton;

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
        submitButton = findViewById(R.id.button);

        // Add listener to button
        submitButton.setOnClickListener((v) -> {
            // Validate data
            if (!verifyData()) return;

            // Create register and notify
            Register register = new Register();

            register.setNames(inputName.getText().toString());
            register.setDni(Integer.parseInt(inputDni.getText().toString().trim()));
            register.setAddress(inputAddress.getText().toString().trim());
            register.setAge(Integer.parseInt(inputAge.getText().toString().trim()));
            register.setCourse(inputCourse.getText().toString().trim());

            Registers.getInstance().appendRegister(register);

            Toast.makeText(this, "Se ha registrado el nuevo registro con el ID " + register.getId(), Toast.LENGTH_SHORT).show();
        });

    }

    private boolean verifyData () {
        // Get all values
        String nameValue    = inputName.getText().toString();
        String rawDniValue  = inputDni.getText().toString();
        String addressValue = inputAddress.getText().toString();
        String rawAgeValue  = inputAge.getText().toString();
        String courseValue  = inputCourse.getText().toString();

        boolean flag = false;

        // Verify data
        if (nameValue.isBlank()) {
            Toast.makeText(this, "El campo \"Nombre\" debe de tener contenido", Toast.LENGTH_SHORT).show();
        } else if (rawDniValue.isBlank()) {
            Toast.makeText(this, "El campo \"DNI\" debe de tener contenido", Toast.LENGTH_SHORT).show();

            if (!isPossiblyCasting(rawDniValue.trim())) Toast.makeText(this, "El campo \"DNI\" debe de ser un valor númerico", Toast.LENGTH_SHORT).show();
        } else if (addressValue.isBlank()) {
            Toast.makeText(this, "El campo \"Dirección\" debe de tener contenido.", Toast.LENGTH_SHORT).show();
        } else if (rawAgeValue.isBlank()) {
            Toast.makeText(this, "El campo \"Edad\" debe de tener contenido.", Toast.LENGTH_SHORT).show();

            if (!isPossiblyCasting(rawAgeValue.trim())) Toast.makeText(this, "El campo \"Edad\" debe contener un valor númerico.", Toast.LENGTH_SHORT).show();
        } else if (courseValue.isBlank()) {
            Toast.makeText(this, "El campo \"Curso\" debe de tener contenido", Toast.LENGTH_SHORT).show();
        } else {
            flag = true;
        }

        return flag;
    }

    private boolean isPossiblyCasting (String strInteger) {
        boolean flag = true;

        try {
            Integer.parseInt(strInteger);
        } catch (Exception ex) {
            flag = false;
        }

        return flag;
    }
}