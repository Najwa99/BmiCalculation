package net.najwa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.graphics.Color.parseColor;

public class bmiactivity extends AppCompatActivity {
    android.widget.Button mrecalculate;
    TextView mbmidisplay,mbmicategory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String weight;
    float intheight, intweight;
    RelativeLayout mbackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        getSupportActionBar().hide();
        getActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getActionBar().setTitle("Result");
        ColorDrawable colorDrawable=new ColorDrawable(parseColor("#1E1D1D"));
        getActionBar().setBackgroundDrawable(colorDrawable);


        intent=getIntent();

        mbmidisplay=findViewById(R.id.bmidisplay);
        mbmicategory=findViewById(R.id.bmicategory);
        //mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentLayout);
        //mimageview=findViewById(R.id.imageview);
        mrecalculate=findViewById(R.id.recalculate);

        height= intent.getStringExtra("height");
        weight= intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;

        intbmi=intweight/(intheight*intheight);

        mbmi=Float.toString(intbmi);

        if(intbmi<18.4)
        {
            mbmicategory.setText("Underweight");
            // mbackground.setBackground(Color.RED);
            //mimageview.setImageResource(R.drawable.warning);

        }
        else if(intbmi<24.9 && intbmi>18.5)
        {
            mbmicategory.setText("Normal Weight");
            // mbackground.setBackground(BLUE);
            //mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<29.9 && intbmi>25)
        {
            mbmicategory.setText("Overweight ");
            // mbackground.setBackground(Color.RED);
           // mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<34.9 && intbmi>30)
        {
            mbmicategory.setText("Moderately Obese");
            //mbackground.setBackground(Color.RED);
            //mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<39.9 && intbmi>35)
        {
            mbmicategory.setText("Severely Obese");
            //mbackground.setBackground(Color.RED);
           // mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi>40)
        {
            mbmicategory.setText("Very Severely Obese");
            // mbackground.setBackground(Color.RED);
          //  mimageview.setImageResource(R.drawable.warning);
        }

        //mgender.setText(intent.getStringExtra("gender"));
        //mbmidisplay.setText(mbmi);


        mrecalculate=findViewById(R.id.recalculate);

        mrecalculate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(bmiactivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}

