package com.example.hive.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hive.R;
import com.example.hive.model.User;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    MapView mapView;
    GoogleMap map;
    View customMarker;
    private MapsFragmentInterface mapsFragmentInterface;
    private static final String KEY_USERS = "KEY_USERS";
    private ArrayList<User>users;

    public static MapsFragment newInstance(@NonNull ArrayList<User> users){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_USERS,users);
        MapsFragment mapsFragment = new MapsFragment();
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
         users = getArguments().getParcelableArrayList(KEY_USERS);

        mapsFragmentInterface = (MapsFragmentInterface) getActivity();
         customMarker = inflater.inflate(R.layout.custom_marker,null);
        CircleImageView markerImage = customMarker.findViewById(R.id.user_dp);
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);


        return v;
    }

    public static Bitmap createCustomMarker(Context context,  String resource, String _name) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);

        CircleImageView markerImage = marker.findViewById(R.id.user_dp);

        Picasso.get().load(resource)
                .into(markerImage);

        TextView txt_name = marker.findViewById(R.id.name);
        txt_name.setText(_name);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        map = googleMap;
        googleMap.setMyLocationEnabled(true);
        addMarkers();
        LatLng latLng = new LatLng(53.47723, -2.25487);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                latLng,15);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(location);



    }

    private void addMarkers() {
        for(User user :users){
            LatLng latLng = new LatLng(user.getLatitude(),user.getLongitute());
            map.addMarker(new MarkerOptions().position(latLng)
            ).setIcon(BitmapDescriptorFactory.fromBitmap(
                    createCustomMarker(getContext(),user.getPictureUri(),user.getUsername())));
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mapsFragmentInterface.openUserProfile(marker.getPosition());
        return false;
    }
    public interface MapsFragmentInterface{
        void openUserProfile(LatLng location);
    }
}
