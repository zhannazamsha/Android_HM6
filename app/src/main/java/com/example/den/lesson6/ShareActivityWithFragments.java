package com.example.den.lesson6;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.den.lesson6.Fragments.InfoFragment;
import com.example.den.lesson6.Fragments.ShareFragment;
import com.example.den.lesson6.Interfaces.PhotoItem;

import static com.example.den.lesson6.ShareActivity.PHOTO_ITEM_KEY;

public class ShareActivityWithFragments extends Activity implements ShareFragment.ShareFragmentListener, InfoFragment.InfoFragmentListener {

    //TODO 1: Pass PhotoImage object to info InfoFragment
    //TODO 2: Show on InfoFragment UI author name
    //TODO 3: Add button "close info" to InfoFragment
    //TODO 4: Add "close info" button press listener to ShareActivityWithFragments
    //TODO 5: Handle "close info" in ShareActivityWithFragments, on its press show ShareFragment
    //TODO 6: Handle back button in fragments :)

    private PhotoItem photoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragments);
        this.photoItem = (PhotoItem)getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
        showShareFragment();
    }

    private void showShareFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ShareFragment fragment = new ShareFragment();
        fragment.photoItem = this.photoItem;
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void showInfoFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        InfoFragment fragment = new InfoFragment();
        fragment.photoItem = this.photoItem;
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void closeInfoFragment() {
        showShareFragment();
    }


    // Share Fragment Listener
    @Override
    public void onInfoPress() {
        showInfoFragment();
    }

    @Override
    public void onSharePress() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        startActivity(Intent.createChooser(i, "Share URL"));
    }

    // Info Fragment Listener

    @Override
    public void onInfoClosePress() {
        closeInfoFragment();
    }

    @Override
    public void onBackPressed() {
        Fragment current = getFragmentManager().getFragments().get(0);
        if(current instanceof ShareFragment){
            finish();
        } else  if(current instanceof InfoFragment){
            showShareFragment();
        } else {
            super.onBackPressed();
        }
      }


}
