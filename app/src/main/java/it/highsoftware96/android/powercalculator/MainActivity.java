package it.highsoftware96.android.powercalculator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputTxt;
    TextView outputTxt;

    EditText operand1;
    EditText operand2;
    TextView calcOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTxt = (EditText) findViewById(R.id.inputNumber);
        outputTxt = (TextView) findViewById(R.id.outputNumber);

        operand1 = (EditText) findViewById(R.id.calcInput1);
        operand2 = (EditText) findViewById(R.id.calcInput2);
        calcOutput = (TextView) findViewById(R.id.calcOutput);
    }

    protected void convertNumber(View v){
        int inputNumber = 0;
        deactivateKybrd(v);


        if(inputTxt.getText().toString().equals(""))
            return;

        try{
             inputNumber = Integer.parseInt(inputTxt.getText().toString());
        } catch (Exception e){
            outputTxt.setText("Error...");
            return;
        }


        String temp = "";

        while(inputNumber > 0){
            temp += inputNumber % 2;
            inputNumber /= 2;
        }

        temp = new StringBuilder(temp).reverse().toString();

        outputTxt.setText(temp);

    }


    protected void storeNumber(View v){
        // recupero la clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        // creo un nuovo testo per la clipboard
        ClipData clip = ClipData.newPlainText("Output:", outputTxt.getText().toString());
        // aggiungo il testo
        clipboard.setPrimaryClip(clip);

        // recupero il contesto
        Context context = getApplicationContext();
        // testo che deve apparire
        CharSequence text = "Text copied to the clipboard!";
        // durata comparsa
        int duration = Toast.LENGTH_SHORT;

        // creazione del toast
        Toast toast = Toast.makeText(context, text, duration);
        // mostro il toast
        toast.show();
    }

    protected void sum(View v){

        deactivateKybrd(v);

        if(operand1.getText().toString().equals("") || operand2.getText().toString().equals("") )
            return;

        float input1 = 0;
        float input2 = 0;

        try{
            input1 = Float.parseFloat(operand1.getText().toString());
            input2 = Float.parseFloat(operand2.getText().toString());
        } catch (Exception e){
            outputTxt.setText("Error...");
            return;
        }
        float result = input1 + input2;
        calcOutput.setText("" + result);

        operand1.setText("" + result);

    }

    protected void sub(View v){

        deactivateKybrd(v);

        if(operand1.getText().toString().equals("") || operand2.getText().toString().equals("") )
            return;

        float input1 = 0;
        float input2 = 0;

        try{
            input1 = Float.parseFloat(operand1.getText().toString());
            input2 = Float.parseFloat(operand2.getText().toString());
        } catch (Exception e){
            outputTxt.setText("Error...");
            return;
        }
        float result = input1 - input2;
        calcOutput.setText("" + result);

        operand1.setText("" + result);

    }

    protected void multi(View v){

        deactivateKybrd(v);

        if(operand1.getText().toString().equals("") || operand2.getText().toString().equals("") )
            return;

        float input1 = 0;
        float input2 = 0;

        try{
            input1 = Float.parseFloat(operand1.getText().toString());
            input2 = Float.parseFloat(operand2.getText().toString());
        } catch (Exception e){
            outputTxt.setText("Error...");
            return;
        }
        float result = input1 * input2;
        calcOutput.setText("" + result);

        operand2.setText("" + result);

    }

    protected void div(View v){

        deactivateKybrd(v);

        if(operand1.getText().toString().equals("") || operand2.getText().toString().equals("") )
            return;

        float input1 = 0;
        float input2 = 0;
        float result = 0;

        try{
            input1 = Float.parseFloat(operand1.getText().toString());
            input2 = Float.parseFloat(operand2.getText().toString());
            result = input1 / input2;
        } catch (Exception e){
            outputTxt.setText("Error...");
            return;
        }

        calcOutput.setText("" + result);

        operand1.setText("" + result);

    }

    private void deactivateKybrd(View v){
        // chiusura della tastiera
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
