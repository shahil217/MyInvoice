package com.usc.task2.myinvoices;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

//import com.google.android.gms.common.api.GoogleApiClient;

//import com.google.android.gms.location.LocationListener;

//import com.google.android.gms.location.LocationRequest;

//import com.google.android.gms.location.LocationServices;



import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * Author Shahil Maharjan (1105414)
 * Task 2 of App development class
 */

public class InvoiceFragment  extends Fragment {
    private static final int REQUEST_DATE = 0;
    static final int REQUEST_PHOTO = 1;

    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_INVOICE_ID = "invoice_id";
    private Invoice invoInvoice;
    private EditText invoTitleField;
    private EditText invoShopName;
    private EditText invoComment;
    private Button invoLocation;
    private Button invoDateButton;
    private ImageButton invoButtonPhoto;
    private ImageView invoViewPhoto;
    private Button invoSave;
    private Button invoCancel;
    private CheckBox mSolvedCheckBox;
    private File invoPhotoFile;
    private PackageManager packageManager;
    private Spinner mType;


    /**

   private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }

    }

     */




    public static InvoiceFragment newInstance(UUID invoiceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INVOICE_ID, invoiceId);
        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID invoiceId = (UUID) getArguments().getSerializable(ARG_INVOICE_ID);
        invoInvoice = InvoiceDetail.get(getActivity()).getInvoices(invoiceId);
        invoPhotoFile = InvoiceDetail.get(getActivity()).getPhotoFile(invoInvoice);
       /** invoLocator = new GoogleApiClient.Builder(getActivity())

                .addApi(LocationServices.API)

                .addConnectionCallbacks(this)

                .build();
**/

    }
    @Override public void onPause() {
        super.onPause();
        InvoiceDetail.get(getActivity()).updateInvoice(invoInvoice);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice, container, false);
        /**
         * For the title field
         */
        invoTitleField = (EditText) v.findViewById(R.id.invoice_title);
        invoTitleField.setText(invoInvoice.getTitle());
        invoTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setTitle(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for the shop field
         */
        invoShopName = (EditText) v.findViewById(R.id.invoice_shop);
        invoShopName.setText(invoInvoice.getShopName());
        invoShopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setShopName(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice Comment
         */
        invoComment = (EditText) v.findViewById(R.id.invoice_cmnt);
        invoComment.setText(invoInvoice.getComment());
        invoComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setComment(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice location
         */
      invoLocation = (Button) v.findViewById(R.id.location);
      invoLocation.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = Location.newIntent(getActivity());
              startActivity(intent);

          }
      });
/**
 * for Invoice Type
 */


        /**
         * for Date Button
         */
        invoDateButton = (Button) v.findViewById(R.id.invoice_date);
        updateDate();
        invoDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(invoInvoice.getDate());
                dialog.setTargetFragment(InvoiceFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        /**

         *
         *for cancel button
         */
        invoCancel = (Button) v.findViewById(R.id.cancel_button);
        invoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvoiceDetail.get(getActivity()).deleteInvoice(invoInvoice);
                startActivity(SingleFragmentActivity.newIntent(getActivity()));
            }
        });

        /**
         * for save button
         *
         */
        invoSave = (Button) v.findViewById(R.id.save_button);
        invoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InvoiceDetail.get(getActivity()).updateInvoice(invoInvoice);
                startActivity(SingleFragmentActivity.newIntent(getActivity()));
            }
        });

invoButtonPhoto = (ImageButton) v.findViewById(R.id.invoice_camera);
     /** final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = invoPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;    invoButtonPhoto.setEnabled(canTakePhoto);
                invoButtonPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = FileProvider.getUriForFile(getActivity(),
                                "com.usc.task2.myinvoices.fileprovider",
                                invoPhotoFile);
                        captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        List<ResolveInfo> cameraActivities = getActivity()
                                .getPackageManager().queryIntentActivities(captureImage,
                                        PackageManager.MATCH_DEFAULT_ONLY);
                        for (ResolveInfo activity : cameraActivities) {
                            getActivity().grantUriPermission(activity.activityInfo.packageName,
                                    uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        }
            startActivityForResult(captureImage, REQUEST_PHOTO);        }    });**/

        invoViewPhoto = (ImageView) v.findViewById(R.id.invoice_photo);

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            /**Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            invoViewPhoto.setImageBitmap(imageBitmap);
             **/

        }
        if (requestCode == REQUEST_DATE) {        Date date = (Date) data
                .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        invoInvoice.setDate(date);
        updateDate();    }



    }
    /**@Override

    public void onStart(){

        super.onStart();

        mClient.connect();

    }
    **/




    private void updateDate() {

        invoDateButton.setText(invoInvoice.getDate().toString());
    }

   /** @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }
   **/


    /**@Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = new LocationRequest().create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            invoLocation.setText("No Permission");
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                invoLocation.setText(location.getLatitude()+","+location.getLongitude());
                invoInvoice.setLocation(location.getLatitude()+","+location.getLongitude());
            }
        });


    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    **/
    }
