package com.example.shulz.camerav2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /*****************************************/
    /**Alternate code for the real Camera API_23*/
    /*****************************************/

    public static final int REQUEST_CAP = 123;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button capture = (Button)findViewById(R.id.capture);
        image = (ImageView)findViewById(R.id.imageView);


        //if user has no camera
        if(!hasCamera())
        {
            capture.setEnabled(false);
        }
    }

    public boolean hasCamera()
    {
       return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void cap(View v)
    {
        Intent imageResult = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(imageResult,REQUEST_CAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAP && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            image.setImageBitmap(photo);
        }
    }
}
