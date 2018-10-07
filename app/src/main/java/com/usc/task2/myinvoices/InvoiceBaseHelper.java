package com.usc.task2.myinvoices;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InvoiceBaseHelper  extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "invoiceBase.db";

    public InvoiceBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + InvoiceDatabase.InvoiceTable.NAME + "("+" _id integer primary key autoincrement," +
                InvoiceDatabase.InvoiceTable.Columns.UUID + "," +
                InvoiceDatabase.InvoiceTable.Columns.TITLE + "," +
                InvoiceDatabase.InvoiceTable.Columns.INVOICE_TYPE + ", "+

                InvoiceDatabase.InvoiceTable.Columns.DATE + "," +
                InvoiceDatabase.InvoiceTable.Columns.COMMENT + "," +
                InvoiceDatabase.InvoiceTable.Columns.SHOP + "," +
                InvoiceDatabase.InvoiceTable.Columns.LOCATION + ", "+

                InvoiceDatabase.InvoiceTable.Columns.SOLVED +
                ")");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
