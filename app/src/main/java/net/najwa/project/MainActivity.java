package net.najwa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//Main activity class start here
public class MainActivity extends AppCompatActivity {
    SeekBar mseekbarforage;
    String mintprogress="22";
    int currentprogress;
    Dialog dialog;

    //Define layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setIcon(R.drawable.logo);

        //setTheme(R.style.Theme_Project);
        setContentView(R.layout.activity_main);
        mseekbarforage=findViewById(R.id.seekbarforage);
        TextView mcurrentage=findViewById(R.id.currentage);

        mseekbarforage.setMax(45);
        mseekbarforage.setProgress(22);
        mseekbarforage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress=progress;
                mintprogress=String.valueOf(currentprogress);
                mcurrentage.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button clear;
        
        final  Button btClear=findViewById(R.id.clear);


// Get the references to the widgets
        final EditText etWeight = (EditText) findViewById(R.id.weight);
        final EditText etHeight = (EditText) findViewById(R.id.height);
        final TextView calc = (TextView) findViewById(R.id.calculate);
        final TextView bmicategory = (TextView) findViewById(R.id.bmicategory);
        final TextView bmical = (TextView) findViewById(R.id.mybmi);
        final TextView healthcategory = (TextView) findViewById(R.id.healthcategory);
        
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Logic for validation, input can't be empty
                String str1 = etWeight.getText().toString();
                String str2 = etHeight.getText().toString();

                if (TextUtils.isEmpty(str1)) {
                    etWeight.setError("PLEASE ENTER YOUR WEIGHT!");
                    etWeight.requestFocus();
                    return;
                }
                else


                if (TextUtils.isEmpty(str2)) {
                    etHeight.setError("PLEASE ENTER YOUR HEIGHT!");
                    etHeight.requestFocus();
                    return;
                }
                else if (str2.length()==0) {
                    etHeight.setError("PLEASE ENTER VALID INPUT");
                }



//Get the user values from the widget reference
                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2) / 100;

//Calculate BMI value
                float bmiValue = calculateBMI(weight, height);

//Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);
                String health = healthcategory(bmiValue);



                bmicategory.setText(String.valueOf(bmiInterpretation));
                healthcategory.setText(String.valueOf(health));
                bmical.setText(String.valueOf(bmiValue));


            }

        });



        btClear.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                String s = etWeight.getText().toString();
                if (s.isEmpty())
                    Toast.makeText(getApplicationContext(), "INSERT NEW VALUE", Toast.LENGTH_SHORT).show();
                else {
                    etWeight.setText("");
                }
                String w = etHeight.getText().toString();
                if (w.isEmpty())
                    Toast.makeText(getApplicationContext(), "INSERT NEW VALUE", Toast.LENGTH_SHORT).show();
                else {
                    etHeight.setText("");
                }
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.history) {
            Intent intent= new Intent(MainActivity.this,History.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.aboutus) {
            Intent intent= new Intent(MainActivity.this,aboutus.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //Calculate BMI


    private float calculateBMI(float weight, float height) {
        return (float) Math.rint(weight / (height * height));
    }

    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "BMI CATEGORY : SEVERELY UNDERWEIGHT";
        } else if (bmiValue < 18.5) {

            return "BMI CATEGORY : UNDERWEIGHT";
        } else if (bmiValue < 25) {

            return "BMI CATEGORY : NORMAL";
        } else if (bmiValue < 30) {

            return "BMI CATEGORY : OVERWEIGHT";
        } else {
            return "BMI CATEGORY : OBESE";
        }
    }

    private String healthcategory(float bmiValue) {


        if (bmiValue < 18.4) {
            return "HEALTH RISK: MAINUTRITION RISK  ";
        } else if (bmiValue < 24.9 && bmiValue > 18.5) {
            return "HEALTH RISK: LOW RISK";
        } else if (bmiValue < 29.9 && bmiValue > 25) {
            return "HEALTH RISK: ENCHANCED RISK ";
        } else if (bmiValue < 34.9 && bmiValue > 30) {
            return "HEALTH RISK: MEDIUM RISK";
        } else if (bmiValue < 39.9 && bmiValue > 35) {
            return "HEALTH RISK: HIGH RISK";
        } else  {
            return "HEALTH RISK: VERY HIGH RISK";
        }

    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage("Are you sure want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}