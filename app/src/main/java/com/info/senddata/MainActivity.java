package com.info.senddata;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.net.URI;
import java.util.jar.Manifest;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button, call, map, whatsapp, whatsappimage,Link;
    EditText editText;
    Editable s1;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout kk = (RelativeLayout) findViewById(R.id.abc);

        button = (Button) findViewById(R.id.button);
        map = (Button) findViewById(R.id.map);
        whatsapp = (Button) findViewById(R.id.whatsapp);
        call = (Button) findViewById(R.id.call);
        editText = (EditText) findViewById(R.id.editText);
        whatsappimage = (Button) findViewById(R.id.whatsappimage);
        Link = (Button) findViewById(R.id.Link);
        button.setOnClickListener(this);
        call.setOnClickListener(this);
        map.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        whatsappimage.setOnClickListener(this);
        Link.setOnClickListener(this);
    }


    void request() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s = {"harleenchauhan555@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, s);
                i.putExtra(Intent.EXTRA_SUBJECT, "CONTENT");
                i.putExtra(Intent.EXTRA_TEXT, "HELLO");
                i.setType("message/rfc822");
                Intent chosser = Intent.createChooser(i, "LauncherEmail");
                startActivity(chosser);
                break;

            case R.id.call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                s1 = editText.getText();
                callIntent.setData(Uri.parse("tel:" + s1));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please Grant the permission", Toast.LENGTH_SHORT).show();

                    request();

                } else

                {
                    startActivity(callIntent);
                }
                break;


            case R.id.map:
                String geoURI = "geo:37.422,-122.OB42Z=12";
                Uri geo = Uri.parse(geoURI);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geo);
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;

            case R.id.whatsapp:
                Intent i1 = new Intent(Intent.ACTION_SEND);
                i1.setType("text/plain");
                i1.putExtra(Intent.EXTRA_TEXT, "hlo guys");
                i1.setPackage("com.whatsapp");
                startActivity(i1);
                break;

            case R.id.Link:
                Intent a = new Intent(Intent.ACTION_SEND);
                a.setType("text/plain");
                a.putExtra(Intent.EXTRA_TEXT, "https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl" +
                        "=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
                a.setPackage("com.whatsapp");
                startActivity(a);
                break;





            case R.id.whatsappimage:

                Intent ab = new Intent(Intent.ACTION_PICK);
                ab.setType("image/*");
                startActivityForResult(ab, 1);

                break;



        }
    }
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            image = data.getData();
            Toast t = Toast.makeText(getApplicationContext(), image.toString(), Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 0);
            t.show();
            Intent i8 = new Intent(android.content.Intent.ACTION_SEND);

            i8.setType("image/*");
            i8.putExtra(Intent.EXTRA_STREAM, image);
            i8.setPackage("com.whatsapp");
            startActivity(i8);
        }
    }

}


