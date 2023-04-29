package abrorbek.db

import abrorbek.Models.User
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class My_Db_Browser(context: Context) : SQLiteOpenHelper(context, DBNAME, null, 1) {

// data base ni yaratish
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement unique, $NAME text not null,$DATA text not null )"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //databasega yozish
    fun addContact(uset: User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, uset.name)
        contentValues.put(DATA, uset.date)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    //databasedan oqib olish
    fun getAllContacts(): ArrayList<User> {
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        val list = ArrayList<User>()
        if (cursor.moveToFirst()) {
            do {
                val uset = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(uset)
            } while (cursor.moveToNext())
        }

        return list
    }

    //recycleviewdagi biror bir malumotni ochiradi
    fun deleteUser(uset: User) {
        val database = writableDatabase
        database.delete(TABLE_NAME, "$NAME =?", arrayOf(uset.id.toString()))
    }


    //recycleviewdagi malumotni yangilaydi
    fun updateUser(uset: User): Int {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, uset.id)
        contentValues.put(NAME, uset.name)
        contentValues.put(DATA, uset.date)

        return db.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(uset.id.toString()))
    }


    //Malumotni saqlash uchun companion object

    companion object {
        val DBNAME = "Conatact"
        val ID = "id"
        val NAME = "name"
        val DATA = "data"
        val TABLE_NAME = "MyContacts"

    }


}


