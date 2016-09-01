package cuanlee.pcstore_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cuanlee.pcstore_application.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
        exit();
    }

    private void login() {
        Button btn = (Button)findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtusername = (EditText)findViewById(R.id.txtBxUsername);
                EditText txtpassword = (EditText)findViewById(R.id.txtBxPassword);
                TextView lbl = (TextView)findViewById(R.id.lblInvalid);



                if(txtusername.getText().toString().equalsIgnoreCase("cuanlee" ) && txtpassword.getText().toString().equalsIgnoreCase("cuanlee123"))
                {
                    Toast.makeText(MainActivity.this,"Welcome To My PC Store",Toast.LENGTH_LONG).show();
                    lbl.setVisibility(View.INVISIBLE);
                    Intent i = new Intent(MainActivity.this, Home.class);
                    //i.putExtra("Tab", "Patient");
                    finish();
                    startActivity(i);
                }
                else
                {
                    lbl.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void exit() {
        Button btn = (Button)findViewById(R.id.btnExit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
    }
}
