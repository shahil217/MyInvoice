package com.usc.task2.myinvoices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class InvoiceListFragment extends Fragment {


    private Button invoHelp;
    private RecyclerView invoInvoiceRecyclerView;
    private InvoiceAdapter invoAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, container, false);

        invoInvoiceRecyclerView = (RecyclerView) view.findViewById(R.id.invoice_recycler_view);
        invoInvoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_invoice_list, menu);
        MenuItem help = menu.findItem(R.id.show_help);


    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_invoice:
                Invoice invoice = new Invoice();
                InvoiceDetail.get(getActivity()).addInvoice(invoice);
                Intent intent = InvoicePagerActivity.newIntent(getActivity(), invoice.getId());
                startActivity(intent);
                return true;
            case R.id.show_help:

                        Intent helpIntent = HelpActivity.newIntent(getActivity());
                        startActivity(helpIntent);



                return true;
            default:
                return super.onOptionsItemSelected(item);
        } }

    private void updateUI() {
        InvoiceDetail invoiceDetail = InvoiceDetail.get(getActivity());
        List<Invoice> invoices = invoiceDetail.getInvoices();
        if (invoAdapter == null) {
            invoAdapter = new InvoiceAdapter(invoices);
            invoInvoiceRecyclerView.setAdapter(invoAdapter);
        } else {
            invoAdapter.setInvoices(invoices);
            invoAdapter.notifyDataSetChanged();
        }

    }

    /**
     * A view holder class for recycler View
     */
    private class CrimeHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        private TextView invoTitleTextView;
        private TextView invoDateTextView;
        private TextView invoShopTextView;
        private Invoice invoInvoice;
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_invoice, parent, false));
            itemView.setOnClickListener(this);
            invoTitleTextView = (TextView) itemView.findViewById(R.id.invoice_title);
            invoDateTextView = (TextView) itemView.findViewById(R.id.invoice_date);
            invoShopTextView = (TextView) itemView.findViewById(R.id.invoice_shop);
        }
        @Override    public void onClick(View view) {
            Intent intent = InvoicePagerActivity.newIntent(getActivity(), invoInvoice.getId());
            startActivity(intent);    }
        public void bind(Invoice invoice) {
            invoInvoice = invoice;
            invoTitleTextView.setText(invoInvoice.getTitle());
            invoDateTextView.setText(invoInvoice.getDate().toString());
            invoShopTextView.setText(invoInvoice.getShopName());
        }
    }

    /**
     * end of View Holder
     */

    /**
     *
     * An Adapter for RecyclerView
     */
    private class InvoiceAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Invoice> invoInvoices;
        public InvoiceAdapter(List<Invoice> invoices) {
            invoInvoices = invoices;        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Invoice invoice = invoInvoices.get(position);
            holder.bind(invoice);

        }
        @Override
        public int getItemCount() {
            return
                    invoInvoices.size();
        }
        public void setInvoices(List<Invoice> invoices) {
            invoInvoices = invoices;    }

    }
    /**
     * End of Adapter
     */

}




