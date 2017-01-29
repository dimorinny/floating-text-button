package ru.dimorinny.floatingsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingTextButton callButton = (FloatingTextButton) findViewById(R.id.call_button);
        FloatingTextButton sendButton = (FloatingTextButton) findViewById(R.id.send_button);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Call clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 240);
                toast.show();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Send clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 240);
                toast.show();
            }
        });
    }
}
