package com.simple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {

	private DatabaseHelper helper;
	private SQLiteDatabase database;

	public static final String CONTACTOS_TABLE = "contactos";
	public static final String CONTACTOS_ID = "_id";
	public static final String NOMBRE_COL = "nombre";
	public static final String NUMERO_COL = "NUMERO";
	public static final String COOL_COL = "COOL";

	public DatabaseManager(Context context) {
		helper = new DatabaseHelper(context);
	}

	public SQLiteDatabase open() {
		database = helper.getWritableDatabase();
		return database;
	}

	public long ingresar(String nombre, String numero, float cool) {
		open();
		ContentValues values = new ContentValues();
		values.put(NOMBRE_COL, nombre);
		values.put(NUMERO_COL, numero);
		values.put(COOL_COL, cool);

		return database.insert(CONTACTOS_TABLE, null, values);
	}

	public static class DatabaseHelper extends SQLiteOpenHelper {
		private static final int VERSION = 2;
		private static final String DATABASE_NAME = "aplicacion_simple";

		private static final String CONTACTOS_TABLE_CREATE = "Create table "
				+ CONTACTOS_TABLE + " ( " + CONTACTOS_ID + " int primary key,"
				+ NOMBRE_COL + " TEXT NOT NULL," + NUMERO_COL
				+ " TEXT NOT NULL ," + COOL_COL + " FLOAT NOT NULL" + ")";

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CONTACTOS_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d("LOG", "upgrading");
			if (oldVersion < newVersion) {
				db.beginTransaction();

				db.execSQL("ALTER TABLE " + CONTACTOS_TABLE + " COLUMN "
						+ NUMERO_COL + " TEXT");

				db.setTransactionSuccessful();
				db.endTransaction();
			}
		}
	}

	public Cursor listarContactos() {
		open();
		Cursor c = database.query(CONTACTOS_TABLE, new String[] { CONTACTOS_ID,
				NOMBRE_COL, NUMERO_COL, COOL_COL }, null, null, null, null,
				null);
		return c;

	}
}
