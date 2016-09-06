package com.example.gauravnivsarkar.projectutils.activities;


import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.gauravnivsarkar.projectutils.Utils;
import com.example.gauravnivsarkar.projectutils.views.RetrySnackBar;


/**
 * Created by Gaurav on 22/02/16.
 * Base class for all activities used.Provides common functionality like hanlders,progressdialog,loader management,etc
 */
public abstract class Base_Activity extends AppCompatActivity {

    protected Handler mHandler;
    private ProgressDialog progressDialog;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = getActivityToolbar();
        if (toolbar != null) {
            setToolbar(toolbar, getIfHomeAsUpEnabled());
        }
        String actionBarTitle = getActionBarTitle();
        if (actionBarTitle != null) {
            setActionBarTitle(getSupportActionBar(), actionBarTitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void setToolbar(@NonNull Toolbar toolbar, boolean homeAsUpEnabled) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(getToolbarElevation());
            actionBar.setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }
    }


    protected abstract Toolbar getActivityToolbar();


    protected boolean getIfHomeAsUpEnabled() {
        return false;
    }

    protected abstract String getActionBarTitle();
    protected float getToolbarElevationInDp() {
        return 4.0f;
    }

    private float getToolbarElevation() {
        return Utils.dpToPixels(this, getToolbarElevationInDp());
    }

    protected abstract void setActionBarTitle(ActionBar actionBar, String title) ;

    public void showSnackBar(String message, final boolean retry, final int requestId) {

            final Snackbar snackbar = RetrySnackBar.make(0, this.findViewById(android.R.id.content), message, retry ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_SHORT);

            snackbar.setAction(retry ? "Retry" : "Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (retry) {
                        //webservice retry callback
                        retryRequest(requestId);
                    }
                    snackbar.dismiss();
                }
            });
            snackbar.show();
    }

    protected abstract void handleError(Throwable error, int requestCode);
//    {
//
//        ErrorResponse err = ErrorResponse.getErrorResponse(error);
//        switch (err.getErrorType()) {
//            case NETWORK:
//                showSnackBar(getString(R.string.no_internet_found),true,requestCode);
//                break;
//            case BAD_REQUEST:
//                showSnackBar(getString(R.string.bad_request),false,requestCode);
//                break;
//            case UNKNOWN:
//            case FORBIDDEN:
//            case UNAUTHORIZED_TOKEN:
//                showSnackBar("Invalid credentials.",false,requestCode);
//
//                break;
//
//        }
//    }


    protected void initOrRestartLoader(int id, Bundle searchArgs, LoaderManager.LoaderCallbacks callback) {
        LoaderManager manager = getLoaderManager();
        if (manager.getLoader(id) == null) {
            manager.initLoader(id, searchArgs, callback);
        } else {
            manager.restartLoader(id, searchArgs, callback);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                progressDialog = null;
            }
        }
    }

    public void showProgressDialog(boolean isCancelable) {
        hideProgressDialog();
        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(isCancelable);
            progressDialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
    }

    public void showToast(String message, int duration) {
        if (message == null || (duration < 0 || duration > 1)) return;
        toast = Toast.makeText(this, message, duration);
        toast.show();
        toast = null;
    }

    protected abstract int getToolbarId();

    public abstract void retryRequest(int requestCode);
}