package com.example.newsbear2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsbear2.R;

public class SettingsActivity extends AppCompatActivity
{
    //defaults values for language and max number of articles per search
    String language = "&languageCode=en-US";
    String maxNum = "5";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Language");

        //set the subtitle
        String subtitle = "Configure your settings.";
        TextView tv = findViewById(R.id.textView6);
        tv.setText(subtitle);

        //sends values back to Search Activity
        Intent doneIntent = new Intent(SettingsActivity.this, SearchActivity.class);

        //get the language spinner from the xml
        Spinner languageDropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"English", "French", "Spanish"};
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        languageDropdown.setAdapter(adapter);

        //max num spinner (same as above)
        Spinner numDropdown = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"5", "10", "20", "30"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        numDropdown.setAdapter(adapter2);

        Intent searchIntent = getIntent();

        if(searchIntent.hasExtra("com.example.newsbear2.SET_LANGUAGE")) //gets previous language setting change
        {
            //sets spinner to display as the previous setting
            language = searchIntent.getStringExtra("com.example.newsbear2.SET_LANGUAGE");
            if(language.equals("&languageCode=es-ES"))
            {
                languageDropdown.setSelection(2);
            }
            else if(language.equals("&languageCode=fr-FR"))
            {
                languageDropdown.setSelection(1);
            }
            else
            {
                languageDropdown.setSelection(0);
            }
        }
        if(searchIntent.hasExtra("com.example.newsbear2.SET_MAX_NUM")) //gets previous max num setting change
        {
            //sets spinner to display as the previous setting
            maxNum = searchIntent.getStringExtra("com.example.newsbear2.SET_MAX_NUM");
            if(maxNum.equals("30"))
            {
                numDropdown.setSelection(3);
            }
            else if(maxNum.equals("20"))
            {
                numDropdown.setSelection(2);
            }
            else if(maxNum.equals("10"))
            {
                numDropdown.setSelection(1);
            }
            else
            {
                numDropdown.setSelection(0);
            }
        }

        //when the item is selected, add that value as extra
        languageDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) //i = position, l = row id
            {
                //checks which item is chosen and sets the value to language
                if(languageDropdown.getItemAtPosition(i).equals("English"))
                {
                    language = "&languageCode=en-US"; //in the form of API call parameter so it can be added on later
                }
                else if(languageDropdown.getItemAtPosition(i).equals("French"))
                {
                    language = "&languageCode=fr-FR";
                }
                else if(languageDropdown.getItemAtPosition(i).equals("Spanish"))
                {
                    language = "&languageCode=es-ES";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) //previous value
            { }
        });

        numDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) //i = position, l = row id
            {
                if(numDropdown.getItemAtPosition(i).equals("5")) //checks which item is chosen and sets value to maxNum
                {
                    maxNum = "5";
                }
                else if(numDropdown.getItemAtPosition(i).equals("10"))
                {
                    maxNum = "10";
                }
                else if(numDropdown.getItemAtPosition(i).equals("20"))
                {
                    maxNum = "20";
                }
                else if(numDropdown.getItemAtPosition(i).equals("30"))
                {
                    maxNum = "30";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) //previous value
            { }
        });

        //button to confirm changes in settings
        Button doneButton = findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) //once user clicks done, sends back to search activity
            {
                doneIntent.putExtra("com.example.newsbear2.LANGUAGE", language);
                doneIntent.putExtra("com.example.newsbear2.MAXNUM", maxNum);
                SettingsActivity.this.startActivity(doneIntent);
            }
        });
    }
}
