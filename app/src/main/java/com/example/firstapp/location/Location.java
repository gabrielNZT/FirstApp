package com.example.firstapp.location;

import android.location.LocationListener;
import android.os.Bundle;
import androidx.annotation.NonNull;

public class Location implements LocationListener {

    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
