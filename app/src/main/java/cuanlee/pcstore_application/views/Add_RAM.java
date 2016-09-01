package cuanlee.pcstore_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import cuanlee.pcstore_application.R;
import cuanlee.pcstore_application.model.RAM;
import cuanlee.pcstore_application.services.Impl.RAMServiceImpl;

public class Add_RAM extends AppCompatActivity {

    private RAM ramRecord;
    private RAM ram;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update__ram);
        cancel();
        activeList();
        create();

        String id = getIntent().getStringExtra("ID");

        if(id != null) {
            operation = "Update";
            setValues();
        }
        else
        {
            operation = "Add";
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Add_RAM.this, RAM_Home.class);
        finish();
        startActivity(i);
    }

    private void cancel() {
        Button btn = (Button)findViewById(R.id.btnRAMCancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add_RAM.this, RAM_Home.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void activeList() {
        Spinner active = (Spinner)findViewById(R.id.activeSpinner);
        String[] list = new String[] {"Yes", "No"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, list);
        active.setAdapter(adapter);
    }

    private void create() {
        Button btn = (Button)findViewById(R.id.btnRAMSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //try {
                try {
                    boolean valid = validation();

                    if(valid) {
                        if(operation == "Add")
                        {
                            Toast.makeText(getApplicationContext(), "Record Successfully Added", Toast.LENGTH_LONG).show();
                        }
                        else if(operation == "Update")
                        {
                            Toast.makeText(getApplicationContext(), "Record Successfully Updated", Toast.LENGTH_LONG).show();
                        }

                        Intent i = new Intent(Add_RAM.this, View_RAM.class);
                        finish();
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Make sure all fields are filled in correctly", Toast.LENGTH_LONG).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private boolean validation() throws ParseException {
        boolean valid = false;

        TextView ramCode = (TextView)findViewById(R.id.txtAddEditRamCode);
        TextView ramDesc = (TextView)findViewById(R.id.txtAddEditRamDesc);
        TextView ramMemSize = (TextView)findViewById(R.id.txtAddEditRamMemSize);
        TextView ramVoltage = (TextView)findViewById(R.id.txtAddEditRamVoltage);
        TextView ramMemConfig = (TextView)findViewById(R.id.txtAddEditRamMemConfig);
        TextView ramStock = (TextView)findViewById(R.id.txtAddEditRamStock);

        Spinner activeSpinner = (Spinner)findViewById(R.id.activeSpinner);
        Integer active = 0;
        if(activeSpinner.getSelectedItem().toString().equals("Yes")) {
            active = 1;
        } else {
            active = 0;
        }

        if(ramCode.getText().toString().equals("")) {
            ramCode.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramCode.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(ramDesc.getText().toString().equals("")) {
            ramDesc.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramDesc.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(ramMemSize.getText().toString().equals("")) {
            ramMemSize.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramMemSize.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(ramVoltage.getText().toString().equals("")) {
            ramVoltage.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramVoltage.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(ramMemConfig.getText().toString().equals("")) {
            ramMemConfig.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramMemConfig.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(ramStock.getText().toString().equals("")) {
            ramStock.setBackground(getResources().getDrawable(R.drawable.textview_invalid));
        } else {
            ramStock.setBackground(getResources().getDrawable(R.drawable.textview_valid));
        }

        if(!ramCode.getText().toString().equals("")
                && !ramDesc.getText().toString().equals("")
                && !ramMemSize.getText().toString().equals("")
                && !ramVoltage.getText().toString().equals("")
                && !ramMemConfig.getText().toString().equals("")
                && !ramStock.getText().toString().equals(""))
        {
            Double ramVoltageVar = Double.parseDouble(ramVoltage.getText().toString());
            Integer ramStockVar = Integer.parseInt(ramStock.getText().toString());

            String id = getIntent().getStringExtra("ID");
            if(id == null)
            {
                ramRecord = new RAM(ramCode.getText().toString(), ramDesc.getText().toString(), ramMemSize.getText().toString(), ramVoltageVar,ramMemConfig.getText().toString(),ramStockVar, active);
            }
            else
            {
                ramRecord = new RAM(Long.parseLong(id),ramCode.getText().toString(), ramDesc.getText().toString(), ramMemSize.getText().toString(), ramVoltageVar,ramMemConfig.getText().toString(),ramStockVar, active);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = getIntent().getStringExtra("ID");

                    if(id == null)
                    {
                        RAMServiceImpl service = new RAMServiceImpl();
                        service.save(ramRecord);
                    }
                    else
                    {
                        RAMServiceImpl service = new RAMServiceImpl();
                        service.update(ramRecord);
                    }
                }
            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            valid = true;
        }

        return valid;
    }

    private void setValues() {
        final String id = getIntent().getStringExtra("ID");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RAMServiceImpl service = new RAMServiceImpl();
                ram = service.findById(new Long(id));
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView ramCode = (TextView)findViewById(R.id.txtAddEditRamCode);
        TextView ramDesc = (TextView)findViewById(R.id.txtAddEditRamDesc);
        TextView ramMemSize = (TextView)findViewById(R.id.txtAddEditRamMemSize);
        TextView ramVoltage = (TextView)findViewById(R.id.txtAddEditRamVoltage);
        TextView ramMemConfig = (TextView)findViewById(R.id.txtAddEditRamMemConfig);
        TextView ramStock = (TextView)findViewById(R.id.txtAddEditRamStock);
        Spinner ramActive = (Spinner)findViewById(R.id.activeSpinner);

        String voltage = String.valueOf(ram.getVoltage());
        String stock = String.valueOf(ram.getStock());

        ramCode.setText((CharSequence) ram.getCode().toString());
        ramDesc.setText((CharSequence) ram.getDescription().toString());
        ramMemSize.setText((CharSequence) ram.getMemorySize().toString());
        ramVoltage.setText((CharSequence) voltage);
        ramMemConfig.setText((CharSequence) ram.getMemoryConfiguration().toString());
        ramStock.setText((CharSequence) stock);

        switch(ram.getActiveStock()) {
            case 1 : ramActive.setSelection(0);
                break;
            case 0 : ramActive.setSelection(1);
                break;
        }
    }
}
