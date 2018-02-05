package com.bachors.instadownloader;

<<<<<<< HEAD
import android.Manifest;
=======
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
=======
import android.os.Bundle;
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

<<<<<<< HEAD
import com.bachors.instadownloader.instadownloader.InstaClipBoard;
import com.bachors.instadownloader.instadownloader.InstaDownloader;
=======
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
import com.bachors.prefixinput.EditText;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * @author Ican Bachors
<<<<<<< HEAD
 * @version 1.1
=======
 * @version 1.0
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
 * Source: https://github.com/bachors/Insta-Downloader
 */

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private final String intaLink = "https://www.instagram.com/p/";
    private InstaDownloader insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // marshmallow
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
            }
        }

        // config
        insta = new InstaDownloader(this);
<<<<<<< HEAD
        insta.setAccessToken("4211870052.3a81a9f.bbc4c3aec0c8433b8855c3f101a4cae1");
=======
        insta.setAccessToken("2227436581.3a81a9f.4e37b9951fb344ffbbd57bac6aa0dca1");
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
        insta.setDir("/InstaDownloader");

        // input url
        input = (EditText) findViewById(R.id.input);
        input.setPrefix(intaLink);
        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String url = input.getText().toString().trim();
                    String id = url.replace(intaLink, "");
                    if(!id.isEmpty())
                        insta.get(url);
                    else
                        Toast.makeText(getApplicationContext(), "URL not valid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        // clipboard listener
<<<<<<< HEAD
        Intent svc = new Intent(MainActivity.this, InstaClipBoard.class);
        startService(svc);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        assert clipboard != null;
=======
        Intent svc = new Intent(MainActivity.this, ClipBoardService.class);
        startService(svc);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
>>>>>>> c715af0bb49f5ecabe518fa2f2f5a479e94820d3
        if ((clipboard.hasPrimaryClip())) {
            if ((clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {
                final ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                String paste = item.getText().toString();
                if(paste.matches(intaLink + "(.*)")){
                    input.setText(item.getText().toString());
                    insta.get(item.getText().toString().trim());
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem actionViewItem = menu.findItem(R.id.instagram);
        // Retrieve the action-view from menu
        View v = MenuItemCompat.getActionView(actionViewItem);
        // Find the button within action-view
        Button x = (Button) v.findViewById(R.id.btn_instagram);
        // Handle button click here
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                if (launchIntent != null)
                {
                    try
                    {
                        startActivity(launchIntent);
                    }
                    catch (ActivityNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
}
