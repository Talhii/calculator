package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText first_value_et,second_value_et;
    TextView result_tv,remainder_tv;
    Button calculate_btn;
    Spinner spinner;

    //Creating object of Classes
    Add add;
    Subtract subtract;
    Multiply multiply;
    Divide divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting Id
        first_value_et = findViewById(R.id.first_value_et);
        second_value_et = findViewById(R.id.second_value_et);

        result_tv = findViewById(R.id.result_tv);
        remainder_tv = findViewById(R.id.remainder_tv);

        calculate_btn = findViewById(R.id.calculate_btn);

        spinner = (Spinner) findViewById(R.id.spinner);

        //Initializing Objects
        add = new Add();
        subtract = new Subtract();
        multiply = new Multiply();
        divide = new Divide();


        //Setting up adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        //oonClickListener on Calculate Button
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_value_et.getText().toString().equals("") || second_value_et.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter both Numbers", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Invoking Method
                    process(Integer.parseInt(first_value_et.getText().toString()),Integer.parseInt(second_value_et.getText().toString()),(String)spinner.getSelectedItem());
                }
            }
        });

    }

    //Creating Method
    void process(int firstValue ,int secondValue , String operation){

        if(operation.equals("Add")){
            int addResult = add.calculate(firstValue,secondValue);
            result_tv.setText("Result :"+addResult);
            remainder_tv.setText("");
        }

        else if(operation.equals("Subtract")){
            int subtractResult = subtract.calculate(firstValue,secondValue);
            result_tv.setText("Result :"+subtractResult);
            remainder_tv.setText("");
        }
        else if(operation.equals("Multiply")){
            int multiplyResult = multiply.calculate(firstValue,secondValue);
            result_tv.setText("Result :"+multiplyResult);
            remainder_tv.setText("");
        }
        else if(operation.equals("Divide") && secondValue != 0){
            divide.calculate(firstValue,secondValue);
            result_tv.setText("Result :"+ divide.result);
            remainder_tv.setText("Remainder" + divide.remainder);
        }
        else if(operation.equals("Divide") && secondValue == 0){
            Toast.makeText(this, "Exception Caught (Cannnot divide by 0)", Toast.LENGTH_SHORT).show();
        }
    }
}