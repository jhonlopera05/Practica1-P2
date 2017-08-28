package com.jhonlopera.practica1_p2;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String usuario, contraseña, confirmar, correo, genero, ciudad;
    private String hobbies ="";
    private String fecha = "";
    private EditText edusuario, edcontraseña, edconfirmar, edCorreo;
    private CheckBox chCine, chTeatro, chCuenteria, chDerporte, chComer, chDormir;
    private Button bFecha, bAceptar;
    private Spinner sCiudades;
    private RadioButton rMasculino, rFemenino;
    private TextView tvInformacion;
    private EditText edfecha;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazar los EditText

        edusuario = (EditText) findViewById(R.id.Usuario);
        edcontraseña = (EditText) findViewById(R.id.Contraseña);
        edconfirmar = (EditText) findViewById(R.id.Confirmar);
        edCorreo = (EditText) findViewById(R.id.edCorreo);
        edfecha = (EditText) findViewById(R.id.edfecha);


        //Enlazar los ChekBox
        chCine = (CheckBox) findViewById(R.id.chCine);
        chTeatro = (CheckBox) findViewById(R.id.chTeatro);
        chCuenteria = (CheckBox) findViewById(R.id.chCuenteria);
        chDerporte = (CheckBox) findViewById(R.id.chDeporte);
        chComer = (CheckBox) findViewById(R.id.chComer);
        chDormir = (CheckBox) findViewById(R.id.chDormir);

        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);

        //enlazar los botones

        bFecha = (Button) findViewById(R.id.bFecha);
        bAceptar = (Button) findViewById(R.id.bAceptar);

        //Enlazar Text View

        tvInformacion = (TextView) findViewById(R.id.tvInformacion);

        //Enlazar spinner

        sCiudades = (Spinner) findViewById(R.id.sCiudades);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);
        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ciudad = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Generar acciones para los botones

        bFecha.setOnClickListener(this);
        bAceptar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == bFecha) {
            final Calendar calendario = Calendar.getInstance();
            day = calendario.get(Calendar.DAY_OF_MONTH);
            month = calendario.get(Calendar.MONTH);
            year = calendario.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edfecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, day, month, year);

            datePickerDialog.updateDate(1980, 0, 1);
            datePickerDialog.show();
        }


        if (v == bAceptar) {
            usuario = edusuario.getText().toString();
            contraseña = edcontraseña.getText().toString();
            confirmar = edconfirmar.getText().toString();
            correo = edCorreo.getText().toString();
            fecha = edfecha.getText().toString();

            if (rFemenino.isChecked()) {
                genero = "Femenino";
            } else {
                genero = "Masculino";
            }
            if (chCine.isChecked()) {
                hobbies += "Cine, ";
            }
            if (chTeatro.isChecked()) {
                hobbies += "Teatro, ";
            }
            if (chCuenteria.isChecked()) {
                hobbies += "Cuenteria, ";
            }
            if (chDerporte.isChecked()) {
                hobbies += "Deporte, ";
            }
            if (chComer.isChecked()) {
                hobbies += "Comer, ";
            }
            if (chDormir.isChecked()) {
                hobbies += "Dormir, ";
            }


            if (usuario.equals("")||contraseña.equals("")||confirmar.equals("")||correo.equals("")||fecha.equals("")){

                tvInformacion.setText("Llene todos los campos");
                tvInformacion.setTextSize(30);
                tvInformacion.setGravity(Gravity.CENTER);
                //tvInformacion.setBackgroundColor(getResources().getColor(R.color.red));
                tvInformacion.setTextColor(getResources().getColor(R.color.red));
                hobbies = "";


            }
            else {
                if (contraseña.equals(confirmar)) {
                    tvInformacion.setTextColor(getResources().getColor(R.color.black));
                    tvInformacion.setGravity(Gravity.LEFT);
                    tvInformacion.setText("Usuario: " + usuario + "\nContraseña: " + contraseña + "\nCiudad:" + ciudad + "\nFecha de nacimiento:" + fecha + "\nGenero: " + genero + "\nHobbies: " + hobbies);
                    hobbies = "";
                }
                else {
                    tvInformacion.setText("la contraseña no coincide");
                    tvInformacion.setTextSize(30);
                    tvInformacion.setGravity(Gravity.CENTER);
                    tvInformacion.setTextColor(getResources().getColor(R.color.red));
                    hobbies = "";
                }
            }

        }


    }
}
