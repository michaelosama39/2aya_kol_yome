package com.example.a2ayakolyome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public String s;
    Long time;

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar. getInstance () ;
    Button btnDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDate = findViewById(R.id.btn) ;

        final ArrayList<String> arrAya = new ArrayList<>();
        arrAya.add("مبارك شعبي مصر");
        arrAya.add("كلمه الصليب عند الهالكين جهاله اما عندنا فنحن مخلصين فهي قوه الله");
        arrAya.add("افليست الاذن تمتحن الاقوال كما ان الحنك يستطعم طعامه");
        arrAya.add("لا اريد ان تجهلوا ايها الاخوه من جهه الراقدين لكن لا تحزنوا كالباقين الذين لا رجاء لهم");

        Random rand = new Random();
        int n = rand.nextInt(3);
        final TextView textView = findViewById(R.id.aya_TV);
        textView.setText(arrAya.get(n));

        // Date
        final TextView datetv = findViewById(R.id.date_TV);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        s ="Date Now" + "  " + format.format(c.getTime());
        datetv.setText(s);

        // share
        ImageView imageView = findViewById(R.id.share_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this , Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED){
                    String elaya =  datetv.getText().toString() +"\n"+ textView.getText().toString() ;
                    Intent intent = new Intent(Intent.ACTION_SEND );
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT , elaya);
                    Intent.createChooser(intent , "share" );
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "The internet is offline", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_menu:
                String packageName ="https://play.google.com/store/apps/details?id=com.example.a2ayakolyome";
                Intent intent = new Intent(Intent.ACTION_SEND );
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT , packageName);
                Intent.createChooser(intent , "share" );
                startActivity(intent);
                break;

            case R.id.about_menu:
                AboutFragment fragment =  new AboutFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainActivity, fragment);
                transaction.commit();
                break;

            case R.id.exit_menu:
                Toast.makeText(this, "okkkkkkk", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Close");
                builder.setMessage("click yes to exit");
                builder.setCancelable(false);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}