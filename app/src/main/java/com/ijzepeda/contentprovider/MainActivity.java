package com.ijzepeda.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAddGame(View view){
        //add new record
        ContentValues contentValues=new ContentValues();

        contentValues.put(GameProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        contentValues.put(GameProvider.GAME_ID,((EditText)findViewById(R.id.editText3)).getText().toString());

        Uri uri=getContentResolver().insert(GameProvider.CONTENT_URI,contentValues);

        Toast.makeText(this, "URI:"+uri.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onClickRetrieveGame(View view){
        //Retrienve game records

        Uri games=Uri.parse(Utils.CONTENT_URL);
        Cursor cursor= managedQuery(games,null,null,null,"name");//retrieve based on name

        if(cursor.moveToFirst()){
            do{
                Toast.makeText(this,
                        cursor.getString(cursor.getColumnIndex(GameProvider._ID))+
                                ", "+cursor.getString(cursor.getColumnIndex(GameProvider.NAME))+
                                ", "+cursor.getString(cursor.getColumnIndex(GameProvider.GAME_ID))+
                                "."
                        , Toast.LENGTH_SHORT).show();
            }while(cursor.moveToNext());
        }
    }





}
