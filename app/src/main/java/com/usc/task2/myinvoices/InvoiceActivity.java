package com.usc.task2.myinvoices;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class InvoiceActivity extends SingleFragmentActivity{
    public static final String EXTRA_INVOICE_ID = "com.bignerdranch.android.myinvoices.invoice_id";
    public static Intent newIntent(Context packageContext, UUID invoiceId) {
        Intent intent = new Intent(packageContext, InvoiceActivity.class);
        intent.putExtra(EXTRA_INVOICE_ID, invoiceId);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        UUID invoiceId = (UUID) getIntent().getSerializableExtra(EXTRA_INVOICE_ID);
        return InvoiceFragment.newInstance(invoiceId);
    }


}
