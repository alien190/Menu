package com.example.ivanovnv.menu;

import android.app.Application;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTv;
    public static final int GROUP_ID = Menu.NONE;
    public static final int MENU_ITEM_ID = 42;
    public static final int ORDER = Menu.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTv = findViewById(R.id.tv_hello);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                Toast.makeText(this,"select item1",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ID:
                Toast.makeText(this, "select context item", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.item1);

        if(item!=null)
        {
            int val = Integer.parseInt(item.getTitle().toString());
            val++;
            item.setTitle(String.valueOf(val));
        }

        return super.onPrepareOptionsMenu(menu);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.contextmenu,menu);
        if(v.getId() == R.id.tv_hello) {
            menu.add(GROUP_ID,MENU_ITEM_ID,ORDER,"context menu");
        }
        else
        {
            super.onCreateContextMenu(menu, v, menuInfo);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerForContextMenu(mHelloTv);
        if(BuildConfig.DEBUG) {
            Log.d("onResume","registerForContextMenu");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterForContextMenu(mHelloTv);
        if(BuildConfig.DEBUG) {
            Log.d("onPause","unregisterForContextMenu");
        }
    }
}
