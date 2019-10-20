package vn.edu.hust.calcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    TextView tvCurrent;

    String op1, op2;   // gia tri chua 2 toan hang
    double result;     //gia tri so 2 toan hang
    int previousop; // luu op lan trc
    int op;         // 1: add, 2: sub, 3: mul, 4: div
    int state;      // 1: nhap op1, 2: nhap op2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.text_result);
        tvCurrent = findViewById(R.id.text_current);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);

        findViewById(R.id.btn_equal).setOnClickListener(this);

        findViewById(R.id.btn_rev).setOnClickListener(this);
        findViewById(R.id.btn_bs).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);

        // Khoi tao cac gia tri
        op1 = "0";
        op2 = "";
        result = 0;
        state = 1;
        op = 0;
        previousop = 0;
        tvResult.setText(op1);
        tvCurrent.setText(op2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                addValue("0");
                break;
            case R.id.btn_1:
                addValue("1");
                break;
            case R.id.btn_2:
                addValue("2");
                break;
            case R.id.btn_3:
                addValue("3");
                break;
            case R.id.btn_4:
                addValue("4");
                break;
            case R.id.btn_5:
                addValue("5");
                break;
            case R.id.btn_6:
                addValue("6");
                break;
            case R.id.btn_7:
                addValue("7");
                break;
            case R.id.btn_8:
                addValue("8");
                break;
            case R.id.btn_9:
                addValue("9");
                break;
            case R.id.btn_add:
                selectOperator(1);
                break;
            case R.id.btn_sub:
                selectOperator(2);
                break;
            case R.id.btn_mul:
                selectOperator(3);
                break;
            case R.id.btn_div:
                selectOperator(4);
                break;
            case R.id.btn_equal:
                calcResult();
                break;
            case R.id.btn_rev:
                reverseOperand();
                break;
            case R.id.btn_bs:
                removeDigit();
                break;
            case R.id.btn_ce:
                clearOperand();
                break;
            case R.id.btn_c:
                clearOperator();
                break;
            case R.id.btn_dot:
                TurnDouble();
                break;
        }


    }
    private  void  TurnDouble() {
        if (state == 1) {
            if (op1.contains(".") == false) {
                op1 = op1 + '.';
            }
            tvResult.setText(op1);
        }
    }

    private void clearOperand() {
        if (state == 1) {
            op1 = "0";
            tvResult.setText(op1);
        } else if (state == 2) {
            op1 = "0";
            tvResult.setText(op1);
        } else {
            clearOperator();
            op1 = "0";
            tvResult.setText(op1);
        }
    }

    private void removeDigit() {
        if (state == 1) {
            if (op1.length() == 1) {
                op1 = "0";
            } else  {
                op1 = op1.substring(0,op1.length()-1);
            }
            tvResult.setText(op1);
        } else if (state == 3) {
            clearOperator();
            op1 = "0";
            tvResult.setText(op1);
        }
    }

    private void reverseOperand() {
        if (state == 1) {
            if (op1 != "0") {
                if (op1.contains("-")) {
                    op1 = op1.substring(1);
                } else {
                    op1 = '-' + op1;
                }
                tvResult.setText(op1);
            }
        } else if (state == 2) {
            if (op1.contains("-")) {
                op1 = op1.substring(1);
            } else {
                op1 = '-' + op1;
            }
            state = 1;
            tvResult.setText(op1);
        }
    }

    private void calcResult() {
        if (state == 3){
            clearOperator();
        } else {
            switch (previousop) {
                case 1:
                    result = result + Double.parseDouble(op1);
                    tvResult.setText(String.valueOf(result));
                    tvCurrent.setText("");
                    break;
                case 2:
                    result = result - Double.parseDouble(op1);
                    tvResult.setText(String.valueOf(result));
                    tvCurrent.setText("");
                    break;
                case 3:
                    result = result * Double.parseDouble(op1);
                    tvResult.setText(String.valueOf(result));
                    tvCurrent.setText("");
                    break;
                case 4:
                    if (Double.parseDouble(op1) == 0){
                        tvResult.setText("Error");
                        state = 3;
                        findViewById(R.id.btn_add).setEnabled(false);
                        findViewById(R.id.btn_sub).setEnabled(false);
                        findViewById(R.id.btn_mul).setEnabled(false);
                        findViewById(R.id.btn_div).setEnabled(false);
                        findViewById(R.id.btn_rev).setEnabled(false);
                        findViewById(R.id.btn_dot).setEnabled((false));
                    } else {
                        result = result / Double.parseDouble(op1);
                        tvResult.setText(String.valueOf(result));
                        tvCurrent.setText("");
                    }
                    break;
            }
            state = 1;
            result = 0;
            previousop = 0;
            op = 0;
            op1 = "0";
            op2 = "";
        }

    }

    private void selectOperator(int p) {
        if (state == 1) {
            if (previousop == 0) {
                op2 = op1;
                result = Double.parseDouble(op1);
                tvResult.setText(String.valueOf(result));
            } else {
                switch (previousop) {
                    case 1:
                        result = result + Double.parseDouble(op1);
                        op2 = op2 + op1;
                        tvResult.setText(String.valueOf(result));
                        break;
                    case 2:
                        result = result - Double.parseDouble(op1);
                        op2 = op2 + op1;
                        tvResult.setText(String.valueOf(result));
                        break;
                    case 3:
                        result = result * Double.parseDouble(op1);
                        op2 = op2 + op1;
                        tvResult.setText(String.valueOf(result));
                        break;
                    case 4:
                        op2 = op2 + op1;
                        if (Double.parseDouble(op1) == 0){
                            tvResult.setText("Error");
                            state = 3;
                            findViewById(R.id.btn_add).setEnabled(false);
                            findViewById(R.id.btn_sub).setEnabled(false);
                            findViewById(R.id.btn_mul).setEnabled(false);
                            findViewById(R.id.btn_div).setEnabled(false);
                            findViewById(R.id.btn_rev).setEnabled(false);
                            findViewById(R.id.btn_dot).setEnabled((false));
                        } else {
                            result = result / Double.parseDouble(op1);
                            tvResult.setText(String.valueOf(result));
                        }
                        break;
                }
            }
            previousop = 0;
        }
        op = p;
        op1 = String.valueOf(result);
        if (state != 3){
            state = 2;
        }

        switch (op) {
            case 1:
                if (previousop == 0){
                    op2 = op2 + "+";
                } else {
                    op2 = op2.substring(0,op2.length()-1) + "+";
                }
                previousop = op;
                break;
            case 2:
                if (previousop == 0){
                    op2 = op2 + "-";
                } else {
                    op2 = op2.substring(0,op2.length()-1) + "-";
                }
                previousop = op;
                break;
            case 3:
                if (previousop == 0){
                    op2 = op2 + "x";
                } else {
                    op2 = op2.substring(0,op2.length()-1) + "x";
                }
                previousop = op;
                break;
            case 4:
                if (previousop == 0){
                    op2 = op2 + "/";
                } else {
                    op2 = op2.substring(0,op2.length()-1) + "/";
                }
                previousop = op;
                break;
        }
        tvCurrent.setText(op2);
    }

    private void addValue(String c) {
        if (state == 1) {
            if (op1 == "0") {
                if (c != "0") {
                    op1 = c;
                }
            } else {
                op1 = op1 + c;
            }
            tvResult.setText(op1);
        } else if (state == 2){
            op1 = c;
            state = 1;
            op = 0;
            tvResult.setText(op1);
        } else {
            clearOperator();
            op1 = c;
            state = 1;
            tvResult.setText(op1);
        }
    }

    private void clearOperator() {
        if (state == 3) {
            findViewById(R.id.btn_add).setEnabled(true);
            findViewById(R.id.btn_sub).setEnabled(true);
            findViewById(R.id.btn_mul).setEnabled(true);
            findViewById(R.id.btn_div).setEnabled(true);
            findViewById(R.id.btn_rev).setEnabled(true);
            findViewById(R.id.btn_dot).setEnabled((true));
        }
        // Khoi tao cac gia tri
        op1 = "0";
        op2 = "";
        state = 1;
        op = 0;
        result = 0;
        previousop = 0;

        tvResult.setText(op1);
        tvCurrent.setText(op2);
    }
}
