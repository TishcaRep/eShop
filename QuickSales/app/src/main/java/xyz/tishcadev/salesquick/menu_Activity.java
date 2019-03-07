package xyz.tishcadev.salesquick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu_Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layaut);
    }

    public void Product(View v)
    {
        Intent intent = new Intent(getApplicationContext(), product_Activity.class);
        startActivityForResult(intent,1);
    }
}
