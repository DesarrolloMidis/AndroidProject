package com.gob.midis.appmidis.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.gob.midis.appmidis.R;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import static android.content.Context.LOCATION_SERVICE;
import static com.google.android.gms.common.util.WorkSourceUtil.TAG;


public class HogarNewFragment extends Fragment {
    private Button btn_TomarFoto;
    private ImageView avatar;
    private TextView txtLatitud;
    private TextView txtLongitud;
    private TextView txtnSats;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;

    private Location mLastLocation;
    public LocationManager mLocationManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hogar_new, container, false);
        btn_TomarFoto = (Button) view.findViewById(R.id.btn_TomarFoto);
        txtLatitud = (TextView) view.findViewById(R.id.txtLatitud);
        txtLongitud = (TextView) view.findViewById(R.id.txtLongitud);
        txtnSats = (TextView) view.findViewById(R.id.txtnSats);

        avatar = (ImageView) view.findViewById(R.id.avatar);

        ActivarGPS();

        btn_TomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        return view;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //code
            System.out.println("onLocationChanged");
            mLastLocation = location;
            txtLatitud.setText(String.valueOf(location.getLatitude()));
            txtLongitud.setText(String.valueOf(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("onStatusChanged");
        }
        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("onProviderEnabled");
        }
        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("onProviderDisabled");
            //turns off gps services
        }
    };

    private final GpsStatus.Listener mLocationListenerGPS = new GpsStatus.Listener () {
        @Override
        public void onGpsStatusChanged(int event) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "App MIDIs, no cuenta con los permisos de GPS, favor de activar para esta aplicación", Toast.LENGTH_LONG).show();
                return;
            }

            switch (event) {
                case GpsStatus.GPS_EVENT_STARTED:
                    Log.e(TAG, "onGpsStatusChanged started");
                    break;

                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.e(TAG, "onGpsStatusChanged stopped");
                    break;

                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    Log.e(TAG, "onGpsStatusChanged first fix");
                    break;

                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    GpsStatus status = mLocationManager.getGpsStatus(null);
                    if(event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){
                        int maxSatellites = status.getMaxSatellites();
                        Iterator<GpsSatellite> it = status.getSatellites().iterator();
                        int count = 0;
                        while (it.hasNext() && count <= maxSatellites) {
                            GpsSatellite s = it.next();
                            count++;
                        }
                        txtnSats.setText(String.valueOf(count));
                    }
                    Log.e(TAG, "onGpsStatusChanged status");
                    break;
            }
        }
    };


    private void ActivarGPS(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this.getActivity(), "App MIDIs, no cuenta con los permisos de GPS, favor de activar para esta aplicación", Toast.LENGTH_LONG).show();
            return;
        }
        int LOCATION_REFRESH_TIME = 1000;
        int LOCATION_REFRESH_DISTANCE = 5;
        mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);

        LocationManager locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.addGpsStatusListener(mLocationListenerGPS);
    }



    private void dispatchTakePictureIntent() {
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityFromFragment(HogarNewFragment.this, cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK && data != null) {

                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

            /*
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);
            */

                    avatar.setImageBitmap(bmp);

                }
            }
        }catch(Exception e){
            Toast.makeText(this.getActivity(), e+"Something went wrong", Toast.LENGTH_LONG).show();

        }
    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Do something with imagePath

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                avatar.setImageBitmap(photo);
                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri selectedImage = getImageUri(getActivity(), photo);
                String realPath=getRealPathFromURI(selectedImage);
                selectedImage = Uri.parse(realPath);
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getActivity().getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }*/


/*    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                avatar.setImageBitmap(bitmap);

            }
        }*/




/*        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            avatar.setImageBitmap(imageBitmap);
        }*/



}
