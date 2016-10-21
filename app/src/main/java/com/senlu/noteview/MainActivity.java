package com.senlu.noteview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyNoteView myNote;
    Boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myNote = (MyNoteView) findViewById(R.id.my_note);
        myNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    myNote.setColor(Color.YELLOW);
                    flag = false;
                }else {
                    myNote.setColor(Color.GREEN);
                    flag = true;
                }

            }
        });
    }
}
