package com.example.arman.eshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arman.eshop.Api.CategoriasClient;
import com.example.arman.eshop.Api.Models.MCategoria;
import com.example.arman.eshop.Api.Models.MCategorias;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editar_departamento extends AppCompatActivity {

    private Button bt2,bt3,bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_editar_departamento);
        bt2 = (Button)findViewById (R.id.button4);
        bt3 = (Button)findViewById (R.id.button7);
        bt4 = (Button)findViewById (R.id.button8);
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
                            ((TextView)findViewById(R.id.editText)).setText(mCategoria.getName());
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
        bt3.setOnClickListener (new View.OnClickListener () {
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
                Call<ResponseBody> call = categoriasClient.Editar(
                        ((EditText)findViewById(R.id.editText3)).getText().toString(),
                        ((EditText)findViewById(R.id.editText)).getText().toString()
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Error en la peticion",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (true){
                            try {
                                JSONObject res = new JSONObject(response.body().string());
                                Toast.makeText(getApplicationContext(),""+res.getString("mensaje"),Toast.LENGTH_SHORT).show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        bt4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
