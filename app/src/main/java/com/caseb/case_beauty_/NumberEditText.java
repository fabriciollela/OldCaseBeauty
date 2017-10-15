package com.caseb.case_beauty_;


import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

public class NumberEditText extends EditText {

    private boolean isUpdating;
    private int positioning[] = {0, 1, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14};

    public NumberEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();

    }

    public NumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();

    }

    public NumberEditText(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    public String getCleanText() {
        String text = NumberEditText.this.getText().toString();

        text.replaceAll("[^0-9]*", "");
        return text;

    }

    private void initialize() {

        final int maxNumberLength = 11;
        this.setKeyListener(keylistenerNumber);

        this.setText(" - ");
        this.setSelection(1);

        this.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String current = s.toString();

/*
* Chamar setText abaixo
* Necessário definir um sinalizador
* E atualizar o texto de modo que não precisamos para reprocessar-lo...
*/
                if (isUpdating) {
                    isUpdating = false;
                    return;

                }

/* retira qualquer digito não numérico... */
                String number = current.replaceAll("[^0-9]*", "");
                if (number.length() > 11)
                    number = number.substring(0, 11);
                int length = number.length();

/* String pad máximo... */
                String paddedNumber = padNumber(number, maxNumberLength);

/* Número dividido em partes... */
                String part1 = paddedNumber.substring(0, 2);
                String part2 = paddedNumber.substring(2, 6);
                String part3 = paddedNumber.substring(6, 11);

/* Criação da máscara do número */
                String numberphone = "(" + part1 + ")" + part2 + "-" + part3 ;

/*
* Definição o sinalizador de atualização , para que quando chamado o
* AfterTextChanged não terá nenhuma ação ...
*/
                isUpdating = true;
                NumberEditText.this.setText(numberphone);

                NumberEditText.this.setSelection(positioning[length]);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        });
    }

    protected String padNumber(String number, int maxLength) {
        String padded = new String(number);
        for (int i = 0; i < maxLength - number.length(); i++)
            padded += " ";
        return padded;

    }

    private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

    private class KeylistenerNumber extends NumberKeyListener {

        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        }
    }
}
