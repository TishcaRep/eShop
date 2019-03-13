package com.example.arman.eshop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arman.eshop.Api.CategoriasClient;
import com.example.arman.eshop.Api.LoginClient;
import com.example.arman.eshop.Api.Models.MCategoria;
import com.example.arman.eshop.Api.Models.MCategorias;
import com.example.arman.eshop.Api.Models.MLoginClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Buscar_departamento extends AppCompatActivity {

    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_buscar_departamento);

        bt1 = (Button)findViewById (R.id.button6);
        bt1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt2 = (Button)findViewById (R.id.button4);
        bt2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                CookieHandler cookieHandler = new CookieManager();
                OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                        .cookieJar(new JavaNetCookieJar(cookieHandler))
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Conexion.Url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                CategoriasClient categoriasClient = retrofit.create(CategoriasClient.class);
                Call<MCategorias> call = categoriasClient.ConsultCatergory(
                        ((EditText)findViewById(R.id.editText3)).getText().toString()
                );
                call.enqueue(new Callback<MCategorias>() {
                    @Override
                    public void onResponse(Call<MCategorias> call, Response<MCategorias> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Error en la peticion",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!response.body().getError()){
                            MCategoria mCategoria = response.body().getCategorias().get(0);
                            Toast.makeText(getApplicationContext(),mCategoria.getName(),Toast.LENGTH_SHORT).show();
                            ((TextView)findViewById(R.id.textView9)).setText(mCategoria.getIdCategory());
                            ((TextView)findViewById(R.id.textView11)).setText(mCategoria.getName());
                        }
                        else {
                            Toast.makeText(getApplicationContext(),response.body().getMensaje(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MCategorias> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
