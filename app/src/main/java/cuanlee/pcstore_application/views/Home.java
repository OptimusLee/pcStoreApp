package cuanlee.pcstore_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cuanlee.pcstore_application.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ram_home();
        terminate();
    }

    private void ram_home() {
        Button btn = (Button)findViewById(R.id.btnRAM);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, RAM_Home.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void terminate() {
        Button btn = (Button)findViewById(R.id.btnTerminateProgram);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
    }
}
