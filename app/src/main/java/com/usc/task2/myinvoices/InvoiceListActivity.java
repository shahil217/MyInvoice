package com.usc.task2.myinvoices;

import android.support.v4.app.Fragment;

public class InvoiceListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new InvoiceListFragment();
    }
}
