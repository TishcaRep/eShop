package com.example.arman.eshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Categorias extends AppCompatActivity {

    ImageView bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_categorias);

        bt1 = (ImageView)findViewById (R.id.imageView27);
        bt2 = (ImageView)findViewById (R.id.imageView28);
        bt1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Categorias.this,Buscar_departamento.class);
                startActivity (intent);
            }
        });

        bt2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categorias.this,nuevo_departamento.class);
                startActivity (intent);
            }
        });

    }
}
