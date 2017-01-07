package com.skapp.lj.mates;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    Button btngetRoomate;
    String si, gudo;
    ImageButton ibtn_search;
    EditText editT_schValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mtextView.setText("Select City");
        editT_schValue = (EditText)findViewById(R.id.editT_schValue);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btngetRoomate = (Button)findViewById(R.id.btn_getRommmate);
        btngetRoomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = marker.getPosition().latitude;
                double longitude = marker.getPosition().longitude;

                String address = findAddress(latitude,longitude);
                String[] addresssplit = address.split(" ");
                si = addresssplit[1];
                gudo = addresssplit[2];
                Toast.makeText(getApplicationContext(), "주소 : "+ si + " / "+gudo , Toast.LENGTH_SHORT).show();
            }
        });

        ibtn_search = (ImageButton)findViewById(R.id.btn_search);
        ibtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findGeoPoint(editT_schValue.getText().toString());
            }
        });
    }
    private String findAddress(double lat, double lng) {
        StringBuffer bf = new StringBuffer();
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        List<Address> address;
        String currentLocationAddress;
        try {
            if (geocoder != null) {
                // 세번째 인수는 최대결과값인데 하나만 리턴받도록 설정했다
                address = geocoder.getFromLocation(lat, lng, 1);
                // 설정한 데이터로 주소가 리턴된 데이터가 있으면
                if (address != null && address.size() > 0) {
                    // 주소
                    currentLocationAddress = address.get(0).getAddressLine(0).toString();

                    // 전송할 주소 데이터 (위도/경도 포함 편집)
                    bf.append(currentLocationAddress).append("#");
                    bf.append(lat).append("#");
                    bf.append(lng);
                }
            }

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "주소취득 실패"
                    , Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        return bf.toString();
    }
    private void findGeoPoint(String address) {
        Geocoder geocoder = new Geocoder(this);
        Address addr;
        try {
            List<Address> listAddress = geocoder.getFromLocationName(address, 1);
            if (listAddress.size() > 0) { // 주소값이 존재 하면
                addr = listAddress.get(0); // Address형태로
                Double lat = addr.getLatitude();
                Double lng = addr.getLongitude();
                LatLng schLatLng = new LatLng(lat,lng);
                createMarker(schLatLng);

                Log.d("t", "주소로부터 취득한 위도 : " + lat + ", 경도 : " + lng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng startingPoint = new LatLng(37.56654,126.97797);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint,15));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                  createMarker(latLng);
            }
        });
    }
    public void createMarker(LatLng latLng)
    {
        if(marker!=null)
        deleteMarker();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_shadow));
        markerOptions.position(latLng);

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        marker = mMap.addMarker(markerOptions);
    }
    public void deleteMarker()
    {
        marker.remove();
    }
}
