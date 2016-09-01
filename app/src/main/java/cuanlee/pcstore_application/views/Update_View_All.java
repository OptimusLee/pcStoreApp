package cuanlee.pcstore_application.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import cuanlee.pcstore_application.R;
import cuanlee.pcstore_application.model.RAM;
import cuanlee.pcstore_application.services.Impl.RAMServiceImpl;

public class Update_View_All extends AppCompatActivity {
    private Dialog dialog;
    private List<RAM> ramList;
    private ArrayList<String> stringRAM;
    private RAM viewRam;
    private Integer stockValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__view__all);
        viewRams();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Update_View_All.this, RAM_Home.class);
        finish();
        startActivity(i);
    }

    private void viewRams() {
        ListView ramListView = (ListView) findViewById(R.id.listViewUpdateRam);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RAMServiceImpl service = new RAMServiceImpl();
                try {
                    ramList = service.findAll();
                    System.out.println(ramList);
                } catch (HttpClientErrorException e) {

                }

                stringRAM = new ArrayList<String>();

                if (ramList != null) {
                    for (RAM ram : ramList) {
                        stringRAM.add(ram.getId() + "  -  " + ram.getCode() + " " + ram.getDescription());
                    }
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringRAM);

        ramListView.setAdapter(adapter);

        ramListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, final View v, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(Update_View_All.this);
                adb.setTitle("Edit");
                adb.setMessage("Are you sure you want to edit record?");
                final int positionToRemove = position;
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView text = (TextView)v;
                        String id = text.getText().toString().substring(0, text.getText().toString().indexOf(" "));
                        Intent i = new Intent(Update_View_All.this, Add_RAM.class);
                        i.putExtra("ID", id);
                        finish();
                        startActivity(i);
                    }
                });
                adb.show();
            }
        });
    }
}
