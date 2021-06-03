package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PermissionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PermissionsFragment extends Fragment {

    private int LOCATION_PERMISSION_CODE = 89;
    private int SMS_PERMISSION_CODE = 2;
    private int CONTACTS_PERMISSION_CODE = 3;
    private int DISPLAY_OVER_APPS_PERMISSION_CODE = 4;

    private Button buttonReq;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PermissionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PermissionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PermissionsFragment newInstance(String param1, String param2) {
        PermissionsFragment fragment = new PermissionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_permissions, container, false);
        buttonReq = (Button) v.findViewById(R.id.btnLocation);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION )== PackageManager.PERMISSION_GRANTED){
            buttonReq.setBackgroundColor(Color.GREEN);

        }
        buttonReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION )== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),"You've already granted this permission",Toast.LENGTH_SHORT).show();

                } else{
                    requestLocationPermission(v);
                }

            }
        });
        return v;

    }
    private void requestLocationPermission(View v){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity() ,Manifest.permission.ACCESS_COARSE_LOCATION)){

                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed to determine your location in order for the app to be able to send your coordinates upon request.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                buttonReq.setBackgroundColor(Color.GREEN);
        } else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
        }

        /*@Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case LOCATION_PERMISSION_CODE: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // permission was granted
                        // here request to get location
                        Toast.makeText(getActivity(),"Permission Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        // permission denied
                        Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }}*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

}