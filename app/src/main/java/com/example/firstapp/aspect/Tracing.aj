package com.example.firstapp.aspect;

import android.content.Context;
import android.widget.Toast;
import com.example.firstapp.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public aspect Tracing {

    Context context = getC

    private pointcut click():
            execution(public void GPS(View));

    after() : click(){
        System.out.println("interceptado!!");
       Toast
    }

}