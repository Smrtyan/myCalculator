package com.example.test.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String [] buttonText = {
            "AC","/" ,"*" ,"-",
            "7" ,"8" ,"9" ,"+",
            "4" ,"5" ,"6" ,"=",
            "1" ,"2" ,"3" ,"0"
    };
    private String firstNum,secondNum,operator,result;
    private Boolean isFirstNum;
    private void clearCal(){
        firstNum = "";
        secondNum = "";
        operator = "";
        result = "";
        isFirstNum = true;
        TextView tv = findViewById(R.id.tv_showText);
        tv.setText("0");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearCal();
        GenerateButton();
    }

    public void buttonClick(View view){
        Button botton = (Button) view;
        String bottonText =  botton.getText().toString();

        switch (bottonText){
            case "AC":
                clearCal();
                return;
            case "/": case "-": case "*": case "+":
                if(!isFirstNum&&!operator.equals("=")) {
                    clearCal();
                    Toast.makeText(getApplicationContext(),"Error! You should enter = after the second number!",Toast.LENGTH_LONG).show();
                }
                else{
                    operator = bottonText;
                    isFirstNum = false;
                }
                break;
            case "=":
                calculate();
                break;
            default:
                //number
                String tmpStr = (isFirstNum?firstNum:secondNum);

                if(bottonText.equals("0")&&firstNum.equals("0")){
                    //do nothing
                }else {

                    tmpStr = tmpStr + bottonText;

                }
                if(isFirstNum) firstNum = tmpStr;
                else secondNum = tmpStr;

                TextView tv = findViewById(R.id.tv_showText);
                tv.setText(tmpStr);
        }
    }
    private void calculate(){
        int first = Integer.parseInt(firstNum);
        int res;
        int second = Integer.parseInt(secondNum);
        switch (operator){
            case "+":
                res = first + second;
                break;
            case"-":
                res = first - second;
                break;
            case "*":
                res = first * second;
                break;
            case "/":
                if("0".equals(secondNum)){
                    Toast.makeText(getApplicationContext(),"Cannot Devided By 0",Toast.LENGTH_LONG).show();
                }
                res = first / second;
                break;
                default:
                    res = 0;
        }
        TextView tv = findViewById(R.id.tv_showText);
        tv.setText(""+res);
    }
    private void GenerateButton(){
        GridLayout gridLayout = findViewById(R.id.grid_buttonGroup);

        Button button;
        ViewGroup.LayoutParams params;
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        for (String s :buttonText){
            button = new Button(this);
            button.setText(s);
            button.setLayoutParams(params);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClick(v);
                }
            });
            gridLayout.addView(button);
        }

    }
}
