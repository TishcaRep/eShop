package com.example.arman.eshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class nuevo_departamento extends AppCompatActivity {

    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nuevo_departamento);

        bt1 = (Button)findViewById (R.id.button8);
        bt1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (nuevo_departamento.this,Categorias.class);
                startActivity (intent);
            }
        });

    }
}
