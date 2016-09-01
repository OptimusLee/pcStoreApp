package cuanlee.pcstore_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cuanlee.pcstore_application.R;

public class RAM_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram__home);
        viewRAM();
        deleteRAM();
        addRAM();
        updateRAM();
        back();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RAM_Home.this, Home.class);
        finish();
        startActivity(i);
    }

    private void viewRAM() {
        Button btn = (Button)findViewById(R.id.btnViewAllRAM);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAM_Home.this, View_RAM.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void deleteRAM() {
        Button btn = (Button)findViewById(R.id.btnDeleteRAM);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAM_Home.this, delete_ram.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void addRAM() {
        Button btn = (Button)findViewById(R.id.btnAddRAM);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAM_Home.this, Add_RAM.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void updateRAM() {
        Button btn = (Button)findViewById(R.id.btnUpdateRAM);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAM_Home.this, Update_View_All.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void back() {
        Button btn = (Button)findViewById(R.id.btnBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RAM_Home.this, Home.class);
                finish();
                startActivity(i);
            }
        });
    }


}
