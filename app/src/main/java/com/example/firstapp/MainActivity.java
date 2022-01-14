package com.example.firstapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.firstapp.location.Location;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.GasolinePrice = findViewById(R.id.EditGasolinePrice);
        this.mViewHolder.Liters = findViewById(R.id.EditLiters);
        this.mViewHolder.ButtonCalculate = findViewById(R.id.ButtonCalculate);
        this.mViewHolder.Price = findViewById(R.id.Price);

        this.mViewHolder.ButtonCalculate.setOnClickListener(this);

        
    }

    public void GPS(View v) {

        System.out.println("teste");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager mLocManager  = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener mLocListener = new Location();

        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);

        if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String texto = "Latitude.: " + Location.latitude + "\n" +
                    "Longitude: " + Location.longitude + "\n";
            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {

        GPS(v);

        if(v.getId() == R.id.ButtonCalculate){
            String value = this.mViewHolder.GasolinePrice.getText().toString();
            String value2 = this.mViewHolder.Liters.getText().toString();
            if("".equals(value) || "".equals(value2)) {
                Toast.makeText(this, this.getString(R.string.Informe_Valor), Toast.LENGTH_LONG).show();
            }else{

                double result = Calculator(Double.valueOf(value),Double.valueOf(value2));
                this.mViewHolder.Price.setText(String.format("%.2f",result));
            }
        }
    }

    public double Calculator(double val1,double val2){
        double result = val1 * val2;
        return result;
    }

    private static class ViewHolder {
        EditText GasolinePrice;
        EditText Liters;
        TextView Price;
        Button ButtonCalculate;
    }

}
