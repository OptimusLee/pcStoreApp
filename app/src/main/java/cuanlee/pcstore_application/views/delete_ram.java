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

public class delete_ram extends AppCompatActivity {

    private ListView ramListView;
    private List<RAM> ramList;
    private ArrayList<String> stringRAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ram);
        viewRams();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(delete_ram.this, RAM_Home.class);
        finish();
        startActivity(i);
    }

    private void viewRams() {
        ramListView = (ListView) findViewById(R.id.listViewRAMDELETE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RAMServiceImpl service = new RAMServiceImpl();
                try {
                    ramList = service.findAll();
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

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringRAM);

        ramListView.setAdapter(adapter);

        ramListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, final View v, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(delete_ram.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete ram record?");
                final int positionToRemove = position;
                adb.setNegativeButton("No", null);
                adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Thread thread1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                TextView text = (TextView)v;
                                Long ramId = new Long(text.getText().toString().substring(0, text.getText().toString().indexOf(" ")));
                                System.out.println(ramId);
                                RAMServiceImpl service = new RAMServiceImpl();
                                RAM deleteRAM = service.findById(ramId);
                                System.out.println(deleteRAM);
                                service.delete(deleteRAM);

                            }
                        });

                        thread1.start();

                        try
                        {
                            thread1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        stringRAM.remove(position);

                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });
    }
}
