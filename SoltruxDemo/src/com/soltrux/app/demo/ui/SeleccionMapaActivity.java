package com.soltrux.app.demo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
 
/**
 * MainActivity.
 * @author Ludovic
 *
 */
public class SeleccionMapaActivity  extends FragmentActivity implements LocationListener, LocationSource, OnMapClickListener
{
    /**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private double Longitud=-8.1090524;
    private double Latitud=-79.0215336;

    private OnLocationChangedListener mListener;
    private LocationManager locationManager;

    
  
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.seleccion_mapa);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
            if(locationManager != null)
            {
                boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                 
                if(gpsIsEnabled)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, this);
                }
                else if(networkIsEnabled)
                {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 10F, this);
                }
                else
                {
                    //Show an error dialog that GPS is disabled...
                }
            }
            else
            {
                //Show some generic error dialog because something must have gone wrong with location manager.
            }
         
        setUpMapIfNeeded();
    }
 
    @Override
    public void onPause()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(this);
        }
         
        super.onPause();
    }
     
    @Override
    public void onResume()
    {
        super.onResume();
         
        setUpMapIfNeeded();
         
        if(locationManager != null)
        {
            mMap.setMyLocationEnabled(true);
        }
    }
     

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) 
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
         
            // Check if we were successful in obtaining the map.
            
            if (mMap != null) 
            {
                setUpMap();
            }
 
            //This is how you register the LocationSource
            mMap.setLocationSource(this);
            
             if(Longitud==0 &&Latitud==0)
            {        
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-8.11158107020461,-79.02834892272949), 15));
            }
            else
            {
             mMap.clear();
             mMap.addMarker(new MarkerOptions().position(new LatLng(Longitud,Latitud)).
             icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
             mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Longitud,Latitud), 15));

            }
            
            
        }
    }
     
    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() 
    {
        mMap.setMyLocationEnabled(true);     
        mMap.setOnMapClickListener(this);       
    }
     
    @Override
    public void activate(OnLocationChangedListener listener) 
    {
        mListener = listener;
    }
     
    @Override
    public void deactivate() 
    {
        mListener = null;
    }
 
    @Override
    public void onLocationChanged(Location location) 
    {
         if( mListener != null )
    {
        mListener.onLocationChanged( location );
 
        LatLngBounds bounds = this.mMap.getProjection().getVisibleRegion().latLngBounds;
 
        if(!bounds.contains(new LatLng(location.getLatitude(), location.getLongitude())))
        {
            LatLng latlng=new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(15));
            Longitud=latlng.longitude;
            Latitud=latlng.longitude;

        }
    }
    }
 
    @Override
    public void onProviderDisabled(String provider) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "provider disabled", Toast.LENGTH_SHORT).show();
    }
 
    @Override
    public void onProviderEnabled(String provider) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "provider enabled", Toast.LENGTH_SHORT).show();
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) 
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "status changed", Toast.LENGTH_SHORT).show();
    }

    public void onMapClick(LatLng latlng) {
      mMap.clear();
      mMap.addMarker(new MarkerOptions().position(latlng).
         icon(BitmapDescriptorFactory
            .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
      Longitud=latlng.longitude;
      Latitud=latlng.longitude;   
      Toast.makeText(this,getString(R.string.str_latitud)+" "+Longitud+
              "\n"+getString(R.string.str_logintud)+" "+Longitud, Toast.LENGTH_LONG).show();
    }
    
    
     public void btn_Aceptar(View view) 
      {
         
             
      }
     

}