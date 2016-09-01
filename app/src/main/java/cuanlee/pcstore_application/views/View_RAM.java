package cuanlee.pcstore_application.views;

import android.app.Dialog;
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

public class View_RAM extends AppCompatActivity {

    private Dialog dialog;
    private List<RAM> ramList;
    private ArrayList<String> stringRAM;
    private RAM viewRam;
    private Integer stockValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__ram);
        viewRams();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(View_RAM.this, RAM_Home.class);
        finish();
        startActivity(i);
    }

    private void viewRams() {
        ListView ramListView = (ListView) findViewById(R.id.listViewRAM);

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
                dialog = new Dialog(View_RAM.this);

                dialog.setContentView(R.layout.activity_ram__detail);

                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RAMServiceImpl service = new RAMServiceImpl();
                        TextView text = (TextView) v;
                        Long ramID = new Long(text.getText().toString().substring(0, text.getText().toString().indexOf(" ")));
                        viewRam = service.findById(ramID);
                    }
                });

                thread1.start();

                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Window view = dialog.getWindow();

                TextView ramCode = (TextView) view.findViewById(R.id.txtRamCode);
                TextView ramDesc = (TextView) view.findViewById(R.id.txtRAMDesc);
                TextView ramMemSize = (TextView) view.findViewById(R.id.txtMemorySize);
                TextView ramVoltage = (TextView) view.findViewById(R.id.txtVoltage);
                TextView ramMemConfig = (TextView) view.findViewById(R.id.txtMemConfig);
                TextView ramStock = (TextView) view.findViewById(R.id.txtStock);
                TextView ramActive = (TextView) view.findViewById(R.id.txtActive);

                if (viewRam.getCode() != null) {
                    ramCode.setText(viewRam.getCode());
                }

                if (viewRam.getDescription() != null) {
                    ramDesc.setText(viewRam.getDescription());
                }

                if (viewRam.getMemorySize() != null) {
                    ramMemSize.setText(viewRam.getMemorySize());
                }

                if(viewRam.getVoltage() > 0) {
                    ramVoltage.setText(String.valueOf(viewRam.getVoltage()));
                }

                if(viewRam.getMemoryConfiguration() != null) {
                    ramMemConfig.setText(viewRam.getMemoryConfiguration());
                }

                if(viewRam.getStock() != null) {
                    ramStock.setText(String.valueOf(viewRam.getStock()));
                }

                if(viewRam.getActiveStock() != null) {
                    stockValue = viewRam.getActiveStock();
                    if(stockValue > 0)
                        ramActive.setText("Yes");
                    else
                        ramActive.setText("No");

                }

                dialog.setTitle("RAM Details");
                dialog.show();
            }
        });
    }
}
