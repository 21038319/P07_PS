package sg.edu.rp.c346.id21038319.p07_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    EditText etElement;
    ListView lvColour;
    EditText editTextColour;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    Spinner spinner;

    ArrayList<String> alColours;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextColour);
        lvColour = findViewById(R.id.listviewcolor);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        spinner = findViewById(R.id.spinner);
        editTextColour = findViewById(R.id.editTextColour);

        alColours = new ArrayList<String>();
        alColours.add("red");
        alColours.add("orange");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alColours);

        lvColour.setAdapter(adapter);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextColour.setText("");
            }
        });

        btnAdd.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                String elementName = etElement.getText().toString();
//                int pos = Integer.parseInt(etIndexElement.getText().toString());
//                alColours.add(pos, elementName);
                alColours.add(elementName);
                adapter.notifyDataSetChanged();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(etElement.getText().toString());

                if(alColours.size()<number) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }else{
                    alColours.remove(number);
                    adapter.notifyDataSetChanged();
                }

            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        editTextColour.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        editTextColour.setHint("Type in the index of the task to be removed");
                        if(alColours.size()<1){
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}