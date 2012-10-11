package com.innoraft.subhojitpaul.bollywoodactress;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class DisplayActressPhoto extends Activity implements SensorEventListener {
	// Array containing Priyanka Chopra Photo resources.
	private int[] priyankaChopraPhotos = {
		R.drawable.priyanka_chopra1,
		R.drawable.priyanka_chopra2,
		R.drawable.priyanka_chopra3,
		R.drawable.priyanka_chopra4,
		R.drawable.priyanka_chopra5,
	};
	
	// Array containing Kareena Kapoor Photo resources.
	private int[] kareenaKapoorPhotos = {
		R.drawable.kareena_kapoor1,
		R.drawable.kareena_kapoor2,
		R.drawable.kareena_kapoor3,
		R.drawable.kareena_kapoor4,
		R.drawable.kareena_kapoor5,
	};
	
	// Array containing Sonakshi Sinha Photo resources.
	private int[] sonakshiSinhaPhotos = {
		R.drawable.sonakshi_sinha1,
		R.drawable.sonakshi_sinha2,
		R.drawable.sonakshi_sinha3,
		R.drawable.sonakshi_sinha4,
		R.drawable.sonakshi_sinha5,
	};
	
	// Current position of the resource arrays.
	private int posPriyankaChopra;
	private int posKareenaKapoor;
	private int posSonakshiSinha;
	
	// Image view object that will render the actress photos.
	private ImageView imgviewActress;
	
	// Button ID that started this activity.
	private int actressID;
	
	// Flag that checks the current size of image.
	private boolean flagImageSize = true;
	
	// Sensor manager and accelerometer.
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	// Variables for detecting shake event.
	private long lastUpdate = -1;
	private long currentTime = -1;
	private float last_x, last_y, last_z;
	private float current_x, current_y, current_z;
	private static final int FORCE_THRESHOLD = 400;
	
	// Vibrator object.
	Vibrator vibrate;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_actress_photo);

        // Initialize the object and attach an event to it.
        imgviewActress = (ImageView) findViewById(R.id.imageviewActressPhoto);
        imgviewActress.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				toggleImageSize();
				return true;
			}
		});
        
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        
        // Acquire sensor manager.
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
		// Get the actress id that started this activity.
        actressID = getIntent().getIntExtra("ActressID", 0);
        
        // Call the desired function for displaying actress photo.
        switch (actressID) {
		case R.id.buttonActress1:
			displayPriyankaChopraPhotos();
			break;
			
		case R.id.buttonActress2:
			displayKareenaKapoorPhotos();
			break;
			
		case R.id.buttonActress3:
			displaySonakshiSinhaPhotos();
			break;
		}
    }
    
    // Display Priyanka Chopra's photo.
    protected void displayPriyankaChopraPhotos() {
    	imgviewActress.setImageResource(priyankaChopraPhotos[posPriyankaChopra]);
    }
    
    // Display Kareena Kapoor's photo.
    protected void displayKareenaKapoorPhotos() {
    	imgviewActress.setImageResource(kareenaKapoorPhotos[posKareenaKapoor]);
    }
    
    // Display Sonakshi Sinha's photo.
    protected void displaySonakshiSinhaPhotos() {
    	imgviewActress.setImageResource(sonakshiSinhaPhotos[posSonakshiSinha]);
    }
    
    // Get position of previous photo.
    public void previousPhoto(View view) {
    	switch (actressID) {
		case R.id.buttonActress1:
			if (posPriyankaChopra == 0) {
				posPriyankaChopra = priyankaChopraPhotos.length - 1;
			} else {
				posPriyankaChopra--;
			}
			displayPriyankaChopraPhotos();
			break;
			
		case R.id.buttonActress2:
			if (posKareenaKapoor == 0) {
				posKareenaKapoor = kareenaKapoorPhotos.length - 1;
			} else {
				posKareenaKapoor--;
			}
			displayKareenaKapoorPhotos();
			break;
			
		case R.id.buttonActress3:
			if (posSonakshiSinha == 0) {
				posSonakshiSinha = sonakshiSinhaPhotos.length - 1;
			} else {
				posSonakshiSinha--;
			}
			displaySonakshiSinhaPhotos();
			break;
		}
    }
    
    // Get position of next photo.
    public void nextPhoto(View view) {
    	switch (actressID) {
		case R.id.buttonActress1:
			if (priyankaChopraPhotos.length == (posPriyankaChopra + 1)) {
				posPriyankaChopra = 0;
			} else {
				posPriyankaChopra++;
			}
			displayPriyankaChopraPhotos();
			break;
			
		case R.id.buttonActress2:
			if (kareenaKapoorPhotos.length == (posKareenaKapoor + 1)) {
				posKareenaKapoor = 0;
			} else {
				posKareenaKapoor++;
			}
			displayKareenaKapoorPhotos();
			break;
			
		case R.id.buttonActress3:
			if (sonakshiSinhaPhotos.length == (posSonakshiSinha + 1)) {
				posSonakshiSinha = 0;
			} else {
				posSonakshiSinha++;
			}
			displaySonakshiSinhaPhotos();
			break;
		}
    }
    
    protected void onResume() {
    	super.onResume();
    	// Register sensor with desired flags.
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    protected void onPause() {
        super.onPause();
        // Unregister sensor.
        mSensorManager.unregisterListener(this);
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			currentTime = System.currentTimeMillis();
			
			// Only allow one update every 100ms.
			if ((currentTime - lastUpdate) > 100) {
				long diffTime = currentTime - lastUpdate;
				lastUpdate = currentTime;
				
				current_x = event.values[SensorManager.DATA_X];
				current_y = event.values[SensorManager.DATA_Y];
				current_z = event.values[SensorManager.DATA_Z];
				
				float currentForce = Math.abs(current_x + current_y + current_z - last_x - last_y -last_z) / diffTime * 10000;
				
				if (currentForce > FORCE_THRESHOLD) {
					// Device has been shaken.
					nextPhoto(this.findViewById(R.id.imageviewActressPhoto));
				}
				
				last_x = current_x;
				last_y = current_y;
				last_z = current_z;
			}
		}
	}
	
	// This function toggles the size of image.
	public void toggleImageSize() {
		vibrate.vibrate(100);
		if (flagImageSize) {
			imgviewActress.setScaleType(ScaleType.FIT_CENTER);
			flagImageSize = false;
		} else {
			imgviewActress.setScaleType(ScaleType.CENTER_INSIDE);
			flagImageSize = true;
		}
	}
}
