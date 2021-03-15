package com.zubisofts.kickcounterex.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zubisofts.kickcounterex.model.Kick;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "kick_counter";
    final static String TABLE_NAME = "kicks_table";
    final static String COLUMN_ID = "id";
    final static String COLUMN_KICK_COUNT = "kick_count";
    final static String COLUMN_START_TIME = "start_time";
    final static String COLUMN_LAST_TIME = "last_time";
    final static String COLUMN_DURATION = "duration";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_KICK_COUNT + " INTEGER NOT NULL, "
                        + COLUMN_START_TIME + " LONG NOT NULL, " + COLUMN_LAST_TIME +
                        " LONG NOT NULL, " + COLUMN_DURATION + " LONG NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addKickData(Kick kick) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_KICK_COUNT, kick.getKicksCount());
        values.put(COLUMN_START_TIME, kick.getStartTime());
        values.put(COLUMN_LAST_TIME, kick.getLastTime());
        values.put(COLUMN_DURATION, kick.getDuration());

        return getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public ArrayList<Kick> getKickData() {
        ArrayList<Kick> kicks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                Kick kick = new Kick(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_KICK_COUNT)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_START_TIME)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_LAST_TIME)),
                        cursor.getLong(cursor.getColumnIndex(COLUMN_DURATION))
                );
                kick.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                kicks.add(kick);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return kicks;
    }
}
