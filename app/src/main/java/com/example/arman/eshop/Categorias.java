package com.example.arman.eshop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.arman.eshop.Api.CategoriasClient;
import com.example.arman.eshop.Api.Models.MCategoria;
import com.example.arman.eshop.Api.Models.MCategorias;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Categorias extends AppCompatActivity {

    ImageView bt1,bt2,bt3,bt4,bt5;
    TableDynamic tableDynamic;
    private TableLayout tableLayout;
    private String[] header ={"id","Nombre"};
    private ArrayList<String[]> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_categorias);

        bt1 = (ImageView)findViewById (R.id.imageView27);
        bt2 = (ImageView)findViewById (R.id.imageView28);
        bt3 = (ImageView)findViewById (R.id.imageView30);
        bt4 = (ImageView)findViewById (R.id.imageView29);
        bt5 = (ImageView)findViewById (R.id.img_actualizar_categoria);

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

        bt3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categorias.this,editar_departamento.class);
                startActivity (intent);
            }
        });

        bt4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categorias.this,eliminar_categoria.class);
                startActivity (intent);
            }
        });


        bt5.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                rows = new ArrayList<>();
                fillTabla();
            }
        });
        fillTabla();
    }
    private void fillTabla(){
        tableLayout = new TableLayout(getApplicationContext());
        tableLayout = findViewById(R.id.table);
        tableLayout.removeAllViews();
        for (int i = 1; i < tableLayout.getChildCount(); i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        tableDynamic = new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeadder(header);
        tableDynamic.backgraundHeader(Color.parseColor("#1F78BD"));

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
        Call<MCategorias> call = categoriasClient.getData();
        call.enqueue(new Callback<MCategorias>() {
            @Override
            public void onResponse(Call<MCategorias> call, Response<MCategorias> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error en la peticion",Toast.LENGTH_SHORT).show();
                    return;
                }
                tableDynamic.addData(fillData(response.body().getCategorias()));
                tableDynamic.backgraundData(Color.parseColor("#EFF3F6"), Color.parseColor("#FFFFFF"));
            }

            @Override
            public void onFailure(Call<MCategorias> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String[]> fillData(ArrayList<MCategoria> data){
        for(MCategoria mcategoria: data){
            rows.add(new String[]{""+mcategoria.getIdCategory(),mcategoria.getName()});
        }
        return rows;
    }
}
