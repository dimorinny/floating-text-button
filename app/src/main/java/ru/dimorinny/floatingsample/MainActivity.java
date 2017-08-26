package ru.dimorinny.floatingsample;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;


import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class MainActivity extends AppCompatActivity {


    private static final Interpolator HIDE_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final Long HIDE_DURATION = 250L;

    private ViewPropertyAnimatorCompat animation = null;
    private ViewPropertyAnimatorCompat animation2 = null;
    ArrayList<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CoordinatorLayout container = (CoordinatorLayout) findViewById(R.id.container);

        final FloatingTextButton callButton = (FloatingTextButton) findViewById(R.id.call_button);


        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);

        contacts = Contact.createContactsList(20);

        ContactsAdapter adapter = new ContactsAdapter(this, contacts);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));




        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(container, "Call button clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


}
