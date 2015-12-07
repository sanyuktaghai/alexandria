package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScanBookActivityFragment extends Fragment implements ZXingScannerView.ResultHandler {

    public ZXingScannerView mScannerView;
    public ScanBookActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_scan_book, container, false);
        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent myIntent = new Intent(getActivity(),MainActivity.class);
        Bundle b = new Bundle();
        b.putString("ISBN",result.getText());
        myIntent.putExtras(b);
        startActivity(myIntent);
        //Toast.makeText(getActivity(),"Content "+result.getText()+" Format "+result.getBarcodeFormat().toString(),Toast.LENGTH_LONG).show();
    }
}
