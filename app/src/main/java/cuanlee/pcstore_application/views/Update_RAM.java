package cuanlee.pcstore_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cuanlee.pcstore_application.R;

public class Update_RAM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__ram);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Update_RAM.this, Update_View_All.class);
        finish();
        startActivity(i);
    }
}
