package xyz.tishcadev.salesquick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.tishcadev.salesquick.api.LoginClient;
import xyz.tishcadev.salesquick.api.Models.MLoginClient;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText User;
    EditText Password;
    TextView mRes;
    View mView;
    AlertDialog dialog;
    static String url ="http://192.168.15.162/app/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create());

    public  static  Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = findViewById(R.id.user);
        Password = findViewById(R.id.password);
    }

    public void Login(View view){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mView  = getLayoutInflater().inflate(R.layout.dialog_login,null);
        mBuilder.setView(mView);
        dialog  = mBuilder.create();
        mRes =  mView.findViewById(R.id.res);
        dialog.show();
        checkLogin(User.getText().toString(), Password.getText().toString());
    }

    private  void checkLogin(String User,String Password)
    {
        LoginClient loginClient = retrofit.create(LoginClient.class);
        Call<MLoginClient> call = loginClient.sendLoginFeedback(
                User, Password
        );


        call.enqueue(new Callback<MLoginClient>() {
            @Override
            public void onResponse(Call<MLoginClient> call, Response<MLoginClient> response) {
                if (!response.isSuccessful()){
                    mRes.setText("Error en el Servidor");
                    return;
                }
                MLoginClient mLoginClient = response.body();
                assert mLoginClient != null;
                mRes.setText(""+mLoginClient.getMessage());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.hide();
                        return;
                    }
                }, 2000);
                if (!mLoginClient.isError()) {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), menu_Activity.class);
                            startActivityForResult(intent, 0);
                            return;
                        }
                    }, 2000);
                }
            }
            @Override
            public void onFailure(Call<MLoginClient> call, Throwable t) {
                Log.d("ErrorDebug",t.getMessage());
            }
        });
    }
}
