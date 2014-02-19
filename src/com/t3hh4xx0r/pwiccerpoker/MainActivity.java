package com.t3hh4xx0r.pwiccerpoker;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText) findViewById(R.id.post_content);
		
		findViewById(R.id.post).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				requestImage(input.getText().toString());				
			}
		});

	}

	private void requestImage(String string) {
		Intent i = new Intent("com.t3hh4xx0r.pwiccer.requestImagePost");
		i.putExtra("POST_CONTENT", string);
		startActivityForResult(i, 420);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 420) {
			if (resultCode == RESULT_OK) {
				final String path = data.getStringExtra("RESULT");
				AlertDialog.Builder b = new AlertDialog.Builder(this);
				b.setTitle("Got a result from Pwiccer");
				b.setMessage(data.getStringExtra("RESULT_TEXT"));
				b.setPositiveButton("View it", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Intent intent = new Intent();
						intent.setAction(android.content.Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.fromFile(new File(path)),
								"image/png");
						startActivity(intent);
					}
				});
				b.create().show();

			}
			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Pwiccer failed to generate a post", Toast.LENGTH_LONG).show();
			}
		}
	}
}
