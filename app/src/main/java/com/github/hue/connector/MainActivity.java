package com.github.hue.connector;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements HueBridgeConnector.OnFragmentInteractionListener
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public void onFragmentInteraction(Uri uri)
  {

  }
}
