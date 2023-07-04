package com.hfad.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks);
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        listDrinks.setAdapter(listAdapter);

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
}