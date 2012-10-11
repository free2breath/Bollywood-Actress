package com.innoraft.subhojitpaul.bollywoodactress;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    // This method is called after the user selects the name of an actress.
    public void onClickSelectActress(View view) {
    	Intent i = new Intent(getApplicationContext(), DisplayActressPhoto.class);
		i.putExtra("ActressID", view.getId());
		startActivity(i);
    }
}
