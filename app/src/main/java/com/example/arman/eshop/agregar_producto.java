package com.example.arman.eshop;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class agregar_producto extends AppCompatActivity {

    EditText codigo;
    Button escan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_agregar_producto);
        codigo = (EditText)findViewById (R.id.editText4);
        escan = (Button)findViewById (R.id.button2);

        escan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                escanear ();
            }
        });
    }

    public void escanear(){
        IntentIntegrator Intent= new IntentIntegrator(this);
        Intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        Intent.setPrompt("ESCANER CODIGO");
        Intent.setCameraId(0);
        Intent.setBeepEnabled(false);
        Intent.setBarcodeImageEnabled(true);
        Intent.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "CANCELASTE EL ESCANER",Toast.LENGTH_LONG).show();
            }else {
                codigo.setText(result.getContents().toString());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


