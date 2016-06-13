package com.lpd.android.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * UserName
     */
    private EditText mEdtUserName;
    /**
     * UserPhone
     */
    private EditText mEdtUserPhone;
    /**
     * UserPassword
     */
    private EditText mEdtUserPassword;
    /**
     * User info save
     */
    private Button mBtnSave, mBtnQuery, mBtnDelete, mBtnUpdate;

    private MyHelper mMyHelper;

    private TextView mTxtShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEdtUserName = (EditText) findViewById(R.id.et_userName);
        mEdtUserPhone = (EditText) findViewById(R.id.et_userPhone);
        mEdtUserPassword = (EditText) findViewById(R.id.et_userPassword);

        findViewById(R.id.tv_infoShow);

        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMySaveInfo();
            }
        });

        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyQueryInfo();
            }
        });

        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyDeleteInfo();
            }
        });
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyUpdateInfo();
            }
        });
        //创建数据库对象
        mMyHelper = new MyHelper(getApplicationContext(), "my_db", null, 1);

    }
private ArrayList<Person> perList = new ArrayList<>();
    private void onMyQueryInfo() {

        SQLiteDatabase writeDB = mMyHelper.getWritableDatabase();

        Cursor cursor = writeDB.rawQuery("select * from person",null);

        int columnCount = cursor.getColumnCount();

        Person person = null;
        for (int i = 0; i < columnCount; i++) {

            person = new Person();

            String name = cursor.getString(cursor.getColumnIndex("name"));
            int phone = cursor.getInt(cursor.getColumnIndex("phone"));
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            person.setName(name);
            person.setPhone(phone);
            person.setPassword(pwd);

            perList.add(person);
        }

        mTxtShowInfo.setText(perList.toString());
        cursor.close();
        writeDB.close();

    }

    private void onMyUpdateInfo() {

        SQLiteDatabase writeDB = mMyHelper.getWritableDatabase();

        writeDB.close();


    }

    private Toast mToast;

    private void onMyDeleteInfo() {

        SQLiteDatabase writeDB = mMyHelper.getWritableDatabase();
        String deleteInfo = mEdtUserName.getText().toString().trim();
        if (deleteInfo == null || deleteInfo == "") {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            } else {
                mToast.setText("删除条件不能为空！！！");
            }

            mToast.show();

            return;
        }

        writeDB.delete("person", "name", new String[]{});
        writeDB.close();

    }

    /**
     * 保存数据到数据库中
     */
    private void onMySaveInfo() {

        SQLiteDatabase writeDB = mMyHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", mEdtUserName.getText().toString().trim());
        contentValues.put("phone", mEdtUserPhone.getText().toString().trim());
        contentValues.put("password", mEdtUserPassword.getText().toString().trim());

        writeDB.insert("person", "name,phone,password", contentValues);
        writeDB.close();
        /**
         * clear EditText info
         */
        mEdtUserName.setText("");
        mEdtUserPassword.setText("");
        mEdtUserPhone.setText("");

    }
}
