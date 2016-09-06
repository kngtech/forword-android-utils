package com.example.gauravnivsarkar.projectutils.activities;

import android.app.Fragment;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.gauravnivsarkar.projectutils.views.RetrySnackBar;

/**
 * Created by gauravnivsarkar on 06/09/16.
 */
public abstract class Base_Fragment extends Fragment {

    private ProgressDialog progressDialog;
    private Toast toast;

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
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(isCancelable);
            progressDialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
    }

    public void showToast(String message, int duration) {
        if (message == null || (duration < 0 || duration > 1)) return;
        toast = Toast.makeText(getActivity(), message, duration);
        toast.show();
        toast = null;
    }

    protected abstract void handleError(Throwable error, int requestCode);

    public void showSnackBar(String message, final boolean retry, final int requestId) {

        final Snackbar snackbar = RetrySnackBar.make(0, this.getActivity().findViewById(android.R.id.content), message, retry ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_SHORT);

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
    public abstract void retryRequest(int requestCode);

}
