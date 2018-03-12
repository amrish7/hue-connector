package com.github.hue.connector;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HueBridgeConnector.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HueBridgeConnector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HueBridgeConnector extends Fragment implements OnMapReadyCallback
{
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private static final String TAG = "HUE_BRIDGE_CONNECTOR";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;


  public HueBridgeConnector()
  {
    // Required empty public constructor
  }


  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment HueBridgeConnector.
   */
  // TODO: Rename and change types and number of parameters
  public static HueBridgeConnector newInstance(String param1, String param2)
  {
    HueBridgeConnector fragment = new HueBridgeConnector();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }


  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri)
  {
    if (mListener != null)
    {
      mListener.onFragmentInteraction(uri);
    }
  }


  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener)
    {
      mListener = (OnFragmentInteractionListener) context;
    }
    else
    {
      throw new RuntimeException(context.toString()
                                     + " must implement OnFragmentInteractionListener");
    }
  }


  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    if (getArguments() != null)
    {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View rootView = inflater.inflate(R.layout.fragment_hue_bridge_connector, container, false);

    // TODO: https://stackoverflow.com/a/38405063/289970
    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    return rootView;
  }


  @Override
  public void onDetach()
  {
    super.onDetach();
    mListener = null;
  }


  @Override
  public void onMapReady(GoogleMap googleMap)
  {
    try
    {
      // Customise the styling of the base map using a JSON object defined
      // in a raw resource file.
      boolean success = googleMap.setMapStyle(
          MapStyleOptions.loadRawResourceStyle(
              getActivity(), R.raw.style_json));

      LatLng latLng = new LatLng(37.4068040, -121.9363780); // 130 Descanso Dr
      CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15.0f).build();
      CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
      googleMap.moveCamera(cameraUpdate);

      if (!success)
      {
        Log.e(TAG, "Style parsing failed.");
      }
    }
    catch (Resources.NotFoundException e)
    {
      Log.e(TAG, "Can't find style. Error: ", e);
    }
  }


  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener
  {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
