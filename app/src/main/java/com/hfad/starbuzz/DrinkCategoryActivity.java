package com.hfad.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this , //当前活动
                    android.R.layout.simple_list_item_1,//在列表视图中对应每一行显示一个值
                    cursor,//这是游标
                    new String[]{"NAME"},//使用游标的哪些列
                    new int[]{android.R.id.text1},//希望在那些视图中显示这些数据
                    0);//用来确定游标的行为，通常为0，这是默认值，也可以注册一个内容观察器
            listDrinks.setAdapter(listAdapter);
        }catch(SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(DrinkCategoryActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                //向意图增加所单击列表项的ID，第一个参数表示使用这个常量名表示意图中的额外信息名
                //这样就能知道DrinkCategoryActivity和DrinkActivity在使用同一个字符串
                //创建DrinkActivity活动时要增加这个常量。
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)id);
                startActivity(intent);
            }
        };

       listDrinks.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}