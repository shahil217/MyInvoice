package com.usc.task2.myinvoices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvoiceDetail {

    private static InvoiceDetail sInvoiceDetail;

    private Context invoContext;
    private SQLiteDatabase invoDatabase;
    public static InvoiceDetail get(Context context) {
        if (sInvoiceDetail == null) {
            sInvoiceDetail = new InvoiceDetail(context);
        }        return sInvoiceDetail;
    }
    private InvoiceDetail(Context context) {
        invoContext = context.getApplicationContext();
        invoDatabase = new InvoiceBaseHelper(invoContext).getWritableDatabase();


    }
    public void addInvoice(Invoice i) {
        ContentValues values = getContentValues(i);
        invoDatabase.insert(InvoiceDatabase.InvoiceTable.NAME, null, values);

    }

    public List<Invoice> getInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        InvoiceCrsrWrapper cursor = queryInvoices(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                invoices.add(cursor.getInvoice());
            cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return invoices;
    }

    public Invoice getInvoices(UUID id) {

        InvoiceCrsrWrapper cursor = queryInvoices(
                InvoiceDatabase.InvoiceTable.Columns.UUID + " = ?",
                new String[] { id.toString() }
                );  try {
                    if (cursor.getCount() == 0) {
                        return null;
                    }
                cursor.moveToFirst();
            return cursor.getInvoice();
                } finally {
                    cursor.close();    }
    }
    public File getPhotoFile(Invoice invoice) {
        File filesDir = invoContext.getFilesDir();
        return new File(filesDir, invoice.getImageFilename());
    }


    public void updateInvoice(Invoice invoice) {
        String uuidString = invoice.getId().toString();
        ContentValues values = getContentValues(invoice);
        invoDatabase.update(InvoiceDatabase.InvoiceTable.NAME, values,
            InvoiceDatabase.InvoiceTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }
    public void deleteInvoice(Invoice myInvoice){
        String uuidString = myInvoice.getId().toString();
        invoDatabase.delete(InvoiceDatabase.InvoiceTable.NAME, InvoiceDatabase.InvoiceTable.Columns.UUID + " =?", new String[] { uuidString });
    }


    private InvoiceCrsrWrapper queryInvoices(String whereClause, String[] whereArgs) {
        Cursor cursor = invoDatabase.query(            InvoiceDatabase.InvoiceTable.NAME,            null,
                // columns - null selects all columns
                whereClause,
                whereArgs,            null,
            // groupBy
                 null,
            // having
                null
            // orderBy
            );
        return new InvoiceCrsrWrapper(cursor);
    }
    private static ContentValues getContentValues(Invoice invoice) {
        ContentValues values = new ContentValues();
        values.put(InvoiceDatabase.InvoiceTable.Columns.UUID, invoice.getId().toString());
        values.put(InvoiceDatabase.InvoiceTable.Columns.TITLE, invoice.getTitle());
        values.put(InvoiceDatabase.InvoiceTable.Columns.DATE, invoice.getDate().getTime());
        values.put(InvoiceDatabase.InvoiceTable.Columns.COMMENT, invoice.getComment());
        values.put(InvoiceDatabase.InvoiceTable.Columns.SHOP, invoice.getShopName());
        values.put(InvoiceDatabase.InvoiceTable.Columns.LOCATION,invoice.getLocation());
        values.put(InvoiceDatabase.InvoiceTable.Columns.INVOICE_TYPE,invoice.getType());


        values.put(InvoiceDatabase.InvoiceTable.Columns.SOLVED, invoice.isSolved() ? 1 : 0);
    return values;    }

}

