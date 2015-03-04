package com.example.jason.quizbangmysql;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnItemClickListenerListViewItem implements OnItemClickListener {

	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Context context = view.getContext();
		
		TextView textViewItem = ((TextView) view.findViewById(R.id.player_name_txt));
		
        // get the clicked item name
        String player_name_tx = textViewItem.getText().toString();
        
        // get the clicked item ID
       // String listItemId = textViewItem.getTag().toString();
        
        // just toast it
        Toast.makeText(context, "Player Name: " + player_name_tx , Toast.LENGTH_SHORT).show();

        ((JSONUseActivity) context).alertDialog.cancel();
        
    }
	
}
