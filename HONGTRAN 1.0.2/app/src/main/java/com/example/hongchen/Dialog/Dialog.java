package com.example.hongchen.Dialog;

import android.app.Activity;
import android.app.ProgressDialog;

public class Dialog {
    public ProgressDialog progressDialog ;

    public Dialog(){

    }

    public Dialog(Activity activity){
        this.progressDialog = new ProgressDialog(activity);
        this.progressDialog.setMessage("Loading");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setCanceledOnTouchOutside(true);
        this.progressDialog.setInverseBackgroundForced(false);

    }

    public void show(){
        this.progressDialog.show();
    }

    public void dismiss(){
        this.progressDialog.dismiss();
    }
}
