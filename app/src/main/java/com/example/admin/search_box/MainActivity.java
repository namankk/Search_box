package com.example.admin.search_box;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
List<String> arrayList;
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList();
        arrayList= Arrays.asList(getResources().getStringArray(R.array.countries_array));
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        adapter=new RecycleAdapter(arrayList);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);

    /*    MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(),"expand",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(),"collapse",Toast.LENGTH_SHORT).show();
                return true;
            }
        };*/
        MenuItem menu1= menu.findItem(R.id.searchbox);
        SearchView searchView= (SearchView) menu1.getActionView();
        searchView.setOnQueryTextListener(this);
//menu1.setOnActionExpandListener(onActionExpandListener);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userinput=s.toLowerCase();
        List<String> strings=new ArrayList<>();
        for (String name:arrayList){
            if (name.toLowerCase().contains(userinput)){
                strings.add(name);
            }
        }
adapter.updateuserlist(strings);
        return true;
    }
}
