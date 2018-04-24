package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText CustFname, CustLname, Gender, Contact, Email, Password;
    Button Execute;

    String[] columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustFname = (EditText) findViewById(R.id.CustFname_et);
        CustLname = (EditText) findViewById(R.id.CustLname_et);
        Gender = (EditText) findViewById(R.id.Gender_et);
        Contact = (EditText) findViewById(R.id.Contact_et);
        Email = (EditText) findViewById(R.id.Email_et);
        Password = (EditText) findViewById(R.id.Password_et);

        String table = "customer";
        String[] rows, searchRows, searchColumns;

        rows = new String[]{"Esther","Kanyangonda","Female","0722259337","estherkanyangonda@gmail.com","TRSGH"};

        searchColumns = new String[]{"F_Name","L_Name","Gender","Phone_Number"};
        searchRows = new String[]{"mike","omachar","Bi","074444432"};

        columns = new String[]{"email","password"};
        Byte flag = 1;

        parseQueryString(table, this.columns, rows, searchRows, searchColumns, flag);
    }

    public void Execute(View view) {
        String Cust_Fname = CustFname.getText().toString();
        String Cust_Lname = CustLname.getText().toString();
        String gender = Gender.getText().toString();
        String contact = Contact.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        this.columns = new String[]{Cust_Fname, Cust_Lname, gender, contact, email, password};
    }

    private String parseQueryString(String table, String[] columns, String[] rows,
                                  String[] searchRows, String[] searchColumns, byte flag) {

        String columnsData, rowsData, searchParameters, queryData;

        columnsData = rowsData = searchParameters = queryData = "";

        class StringConcatenator {
            public String concatenateQUOTES(String textItem) {
                String finalString = "'" + textItem + "'";
                return finalString;
            }

            public String concatenateBRACKETS(String textItem) {
                String finalString = "(" + textItem + ")";
                return finalString;
            }

            public String concatenateAND(String... textItems) {
                String finalString = "";
                int index = 0;

                for (String item : textItems) {
                    if ((index + 1) == textItems.length) {
                        finalString += item;
                        break;
                    }
                    finalString += item + " AND ";
                    index++;
                }
                return finalString;
            }

            public String concatenateOR(String... textItems) {
                String finalString = "";
                int index = 0;

                for (String item : textItems) {
                    if ((index + 1) == textItems.length) {
                        finalString += item;
                        break;
                    }
                    finalString += item + " OR ";
                    index++;
                }
                return finalString;
            }

            public String concatenateEQUAL(String... textItems) {
                String finalString = "";
                int index = 0;

                for (String item : textItems) {
                    if ((index + 1) == textItems.length) {
                        finalString += item;
                        break;
                    }
                    finalString += item + " = ";
                    index++;
                }
                return finalString;
            }

            public String concatenateCOMMA(String... textItems) {
                String finalString = "";
                int index = 0;

                for (String item : textItems) {
                    if ((index + 1) == textItems.length) {
                        finalString += item;
                        break;
                    }
                    finalString += item + " , ";
                    index++;
                }
                return finalString;
            }

            public boolean isNumber(String text) {
                return android.text.TextUtils.isDigitsOnly(text);
            }
        }


        StringConcatenator stringConcatenator = new StringConcatenator();

        switch (flag) {
            case 0:
                String concatenatedValues = "";
                if (rows.length > 1){
                    concatenatedValues = rows[0];
                    int index = 0;
                    for (String item:rows){
                        if(index < 1){
                            if(!stringConcatenator.isNumber(concatenatedValues)){
                                concatenatedValues = stringConcatenator.concatenateQUOTES(concatenatedValues);
                            }
                            index++;
                            continue;
                        }
                        if(!stringConcatenator.isNumber(item)){
                            item = stringConcatenator.concatenateQUOTES(item);
                        }
                        concatenatedValues = stringConcatenator.concatenateCOMMA(concatenatedValues, item);
                    }
                }else if (rows.length == 1){
                    concatenatedValues = rows[0];
                }

                rowsData = concatenatedValues;
                queryData = String.format("INSERT INTO %s VALUES (%s);",table, rowsData);

                return queryData;

            case 1:
                //FOR column headers
                concatenatedValues = "";
                if (columns.length > 1){
                    concatenatedValues = columns[0];
                    int index = 0;
                    for (String item:columns){
                        if(index < 1){
                            index++;
                            continue;
                        }
                        concatenatedValues = stringConcatenator.concatenateCOMMA(concatenatedValues, item);
                    }
                }else if (columns.length == 1){
                    concatenatedValues = columns[0];
                }
                columnsData = concatenatedValues;

                //FOR searchcolumn headers and data
                concatenatedValues = "";
                if(searchColumns.length > 1){
                    for(int i = 0; i < searchColumns.length; i++) {
                        String currentColumn = searchColumns[i];
                        String currentRow = searchRows[i];
                        if (!stringConcatenator.isNumber(currentRow)) {
                            currentRow = stringConcatenator.concatenateQUOTES(currentRow);
                        }

                        if (i == 0) {
                            concatenatedValues = stringConcatenator.concatenateEQUAL(currentColumn, currentRow);
                            continue;
                        }

                        String temporaryString = stringConcatenator.concatenateEQUAL(currentColumn, currentRow);
                        concatenatedValues = stringConcatenator.concatenateAND(concatenatedValues, temporaryString);
                    }

                }else if (searchColumns.length == 1){
                    String currentColumn = searchColumns[0];
                    String currentRow = searchRows[0];
                    if (!stringConcatenator.isNumber(currentRow)) {
                        currentRow = stringConcatenator.concatenateQUOTES(currentRow);
                    }
                    concatenatedValues = stringConcatenator.concatenateEQUAL(currentColumn, currentRow);
                }

                searchParameters = concatenatedValues;
                searchParameters = stringConcatenator.concatenateBRACKETS(searchParameters);
                queryData = String.format("SELECT %s FROM %s WHERE %s;",columnsData, table, searchParameters);


                Log.d("WEEEEEEEEEEEEEEEEEEE002",queryData);
                return queryData;

            case 2:
            case 3:
            default:;
        }


        //String mysql_qry_update = String.format("UPDATE %s SET %s=%s WHERE %s=%s;",table, columnsData, rowsData, searchColumnsData, searchRowsData);
        //String mysql_qry_delete = String.format("DELETE FROM %s WHERE %s=%s;",table, searchColumnsData, searchRowsData);

        return "";
        //ring test = stringConcatenator.concatenateBRACKETS("Esther");
       //og.d("WEEEEEEEEEEEEEEEEEEEEE",test );
    }

}
