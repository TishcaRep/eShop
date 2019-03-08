package xyz.tishcadev.salesquick;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.tishcadev.salesquick.api.Models.MProduct;
import xyz.tishcadev.salesquick.api.Models.MProducts;
import xyz.tishcadev.salesquick.api.Products;

public class product_Activity extends Activity {

    TableDynamic tableDynamic;
    Button ext;
    static String url ="http://192.168.15.162/app/";
    private TableLayout tableLayout;
    private String[] header ={"id","Codigo Barras","Nombre","Costo","Price","Stock","Stock Min","Price min","Price Max","Tipo"};
    private ArrayList<String[]> rows = new ArrayList<>();
    View mView;
    AlertDialog dialog;


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create());
    public  static  Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_layaut);
        tableLayout = new TableLayout(getApplicationContext());
        tableLayout = findViewById(R.id.table);
        tableDynamic = new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeadder(header);
        tableDynamic.backgraundHeader(Color.parseColor("#1146D8"));
        fillTabla();

    }

    private void fillTabla(){
        Products products = retrofit.create(Products.class);
        Call<MProducts> call = products.ProductsFeed();
        call.enqueue(new Callback<MProducts>() {
            @Override
            public void onResponse(Call<MProducts> call, Response<MProducts> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error en la peticion",Toast.LENGTH_SHORT).show();
                    return;
                }

                tableDynamic.addData(fillData(response.body().getProductos()));
                tableDynamic.backgraundData(Color.parseColor("#EFF3F6"), Color.parseColor("#FFFFFF"));
            }
            @Override
            public void onFailure(Call<MProducts> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String[]> fillData(ArrayList<MProduct> data){
        for(MProduct mProduct: data){
            rows.add(new String[]{""+mProduct.getIdProduct(),mProduct.getCodeBar(),mProduct.getName(),""+mProduct.getCost(),""+mProduct.getPrice(),""+mProduct.getStock(),""+mProduct.getStock_Min(),""+mProduct.getPrice_Min(),""+mProduct.getPrice_Max(),mProduct.getTipo()});
        }
        return rows;
    }

    public void AddProduct(View v)
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(product_Activity.this);
        mView  = getLayoutInflater().inflate(R.layout.nuevo_producto_dialog,null);
        mBuilder.setView(mView);
        dialog  = mBuilder.create();
        ext =  mView.findViewById(R.id.ext);
        ext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.hide();
            }
        });
        Button btnSend = mView.findViewById(R.id.btn_Send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Products products = retrofit.create(Products.class);
                
            }
        });
        dialog.show();
    }
}
