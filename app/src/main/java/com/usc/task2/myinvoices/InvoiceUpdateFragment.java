package com.usc.task2.myinvoices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.maps.MapFragment;

import java.util.UUID;

public class InvoiceUpdateFragment extends Fragment {
    private Invoice invoInvoice;
    private TextView mTitleField, mDateField, mDestinationField, mCommentField, mLocationField, mTypeField;
    private ImageView mImageView;

    private Button invoDeletebtn, mMapBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String activityID = "";


        Bundle bundle = this.getArguments();

        if (bundle != null) {

            activityID = bundle.getString(InvoiceActivity.EXTRA_INVOICE_ID, "");

        }

        invoInvoice = InvoiceDetail.get(getActivity()).getInvoices(UUID.fromString(activityID));


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_invoice_fragment, container, false);
        mTypeField = (TextView) v.findViewById(R.id.type);

        mTitleField = (TextView) v.findViewById(R.id.title);

        mCommentField = (TextView) v.findViewById(R.id.comment);

        mLocationField = (TextView) v.findViewById(R.id.location);

        mDestinationField = (TextView) v.findViewById(R.id.destination);


        mDateField.setText(invoInvoice.getDate().toString());

        mTypeField.setText(invoInvoice.getType());

        mTitleField.setText(invoInvoice.getTitle());

        mCommentField.setText(invoInvoice.getComment());

        mLocationField.setText(invoInvoice.getLocation());

        mDestinationField.setText(invoInvoice.getShopName());


        mMapBtn = (Button) v.findViewById(R.id.invoice_map);

        invoDeletebtn = (Button) v.findViewById(R.id.invoice_delete);

        mImageView = (ImageView) v.findViewById(R.id.imageView);


        invoDeletebtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                InvoiceDetail.get(getActivity()).deleteInvoice(invoInvoice);

                startActivity(SingleFragmentActivity.newIntent(getActivity()));

            }

        });

        mMapBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Toast.makeText(getContext(), "Show map", Toast.LENGTH_SHORT).show();


            }
        });


        return v;
            }
        }
