package com.simple;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class ListarContactosActivity extends ListActivity{
	
	private SimpleCursorAdapter adapter;
	private DatabaseManager databaseManager;
	public static final int AGREGAR_CONTACTOS=0; 
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_contactos);
		
		databaseManager=new DatabaseManager(this);
		Cursor c=databaseManager.listarContactos();
		
		String from[]={DatabaseManager.NOMBRE_COL,DatabaseManager.NUMERO_COL};
		int to[]={R.id.textNombre,R.id.textNumero};
		
		
		adapter=new SimpleCursorAdapter(this,R.layout.listar_contactos_item,c,from,to );
		setListAdapter(adapter);
		startManagingCursor(c);
	}
	
	public boolean onCreateOptionsMenu(Menu menu){		
		getMenuInflater().inflate(R.menu.menu_app, menu);		
		return true; 		
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		
		case R.id.mnuAgregar:
			Intent intent=new Intent(ListarContactosActivity.this,AplicacionSimpleActivity.class);
			startActivityForResult(intent,AGREGAR_CONTACTOS);
			break;		
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	

}
