package com.example.arman.eshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {


    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        bt1 = (Button)findViewById (R.id.button);
        bt2 = (Button)findViewById (R.id.button2);

        bt1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this,Menu.class);
                startActivity (intent);
            }
        });

        bt2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this,Categorias.class);
                startActivity (intent);
            }
        });

    }
}
