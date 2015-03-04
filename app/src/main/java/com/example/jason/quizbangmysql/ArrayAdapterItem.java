package com.example.jason.quizbangmysql;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// here's our beautiful adapter
public class ArrayAdapterItem extends ArrayAdapter<Player> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Player> player;
    int[] avatar_image_id ={R.drawable.superhero1, R.drawable.superhero2, R.drawable.superhero3, R.drawable.superhero4,
                            R.drawable.superhero5, R.drawable.superhero6, R.drawable.superhero7, R.drawable.superhero8,
                            R.drawable.superhero9, R.drawable.superhero10, R.drawable.superhero11};


    public ArrayAdapterItem(Context mContext, int layoutResourceId, ArrayList<Player> player) {

        super(mContext, layoutResourceId, player);
        
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.player = player;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    	/*
		 * The convertView argument is essentially a "ScrapView" as described is Lucas post 
		 * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
		 * It will have a non-null value when ListView is asking you recycle the row layout. 
		 * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
    	 */
        if(convertView==null){
	        // inflate the layout
	        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
	        convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        
        // player item based on the position
        Player pr = player.get(position);

        ImageView img = (ImageView)convertView.findViewById(R.id.player_image);

        img.setBackgroundResource(avatar_image_id[pr.getAvatar_id()]);

        // get the TextView and then set the text (player name)
        TextView player_tx = (TextView) convertView.findViewById(R.id.player_name_txt);
        TextView avatar_tx = (TextView)convertView.findViewById(R.id.avatar_txt);

        player_tx.setText(pr.getName());
        avatar_tx.setText(""+pr.getAvatar_id());
       // Log.i("AVATAR", "" + pr.getAvatar_id());

        return convertView;
        
    }
	
}
