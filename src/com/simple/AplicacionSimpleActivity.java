package com.simple;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AplicacionSimpleActivity extends Activity {
	/** Called when the activity is first created. */

	private EditText editNombre;
	private EditText editNumero;
	private RatingBar ratingCool;
	private DatabaseManager databaseManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		databaseManager = new DatabaseManager(this);

		editNombre = (EditText) findViewById(R.id.editNombre);
		editNumero = (EditText) findViewById(R.id.editNumero);
		ratingCool = (RatingBar) findViewById(R.id.ratingCool);

		Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnGuardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Log.d("LOG","Retorno "+databaseManager.ingresar(editNombre.getText().toString(),
						editNumero.getText().toString(), ratingCool.getRating()));
			}

		});
		
		

	}
}