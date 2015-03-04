package com.example.jason.quizbangmysql;

/**
 * Created by Jason on 2/22/2015.
 */

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class JSONUseActivity extends Activity {
    EditText username;   // To take birthyear as input from user
    Button submit;
    TextView tv;      // TextView to show the result of MySQL query

    AlertDialog alertDialog;
    String returnString;   // to store the result of MySQL query after decoding JSON

    ArrayList<Player> player;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.showresult);

        player = new ArrayList<>();
        getPlayerDb();
        // a button to show the pop up with a list view
        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.buttonShowPopUp:
                        showPopUp();


                        break;
                }
            }
        };

        findViewById(R.id.buttonShowPopUp).setOnClickListener(handler);

        // define the action when user clicks on submit button
        // submit.setOnClickListener(new View.OnClickListener(){
        //   public void onClick(View v) {

    }
    public void showPopUp(){

        // our adapter instance
        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.list_view_row_item, player);

        // create a new ListView, set the adapter and item click listener
        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(adapter);
        listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());

        // put the ListView in the pop up
       alertDialog = new AlertDialog.Builder(JSONUseActivity.this)
                .setView(listViewItems)

                .setTitle("\t\t\t\t\t\t\t\t Players")
                .show();

    }
    // Gets players from the database
    public void getPlayerDb(){
        // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user
        /****
         *  this part is what is sent to php to get the results from the birthyear
         */
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        // define the parameter
        postParameters.add(new BasicNameValuePair("birthyear",
                username.getText().toString()));
        String response = null;

        // call executeHttpPost method passing necessary parameters
        try {
            response = CustomHttpClient.executeHttpPost(
                    //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
                    "http://www.quizbang.net/jsonscript.php",  // in case of a remote server
                    postParameters);

            // store the result returned by PHP script that runs MySQL query
            String result = response;

            //parse json data
            try{
                returnString = "";
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);

                    Log.i("log_tag",", name: "+json_data.getString("name"));
                    //Get an output to the screen
                    returnString += "\n" + json_data.getString("name")+ json_data.getInt("id");
                    player.add( new Player(json_data.getString("name"),json_data.getInt("id")-1) );

                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }

/*            try{
                tv.setText(returnString);
            }
            catch(Exception e){
                Log.e("log_tag","Error in Display!" + e.toString());;
            }
            */
        }
        catch (Exception e) {
            Log.e("log_tag","Error in http connection!!" + e.toString());
        }
    }
}