package com.example.hive.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapView mapView;
    private GoogleMap map;
    private View customMarker;
    private MapsFragmentInterface mapsFragmentInterface;
    private static final String KEY_USERS = "KEY_USERS";
    private ArrayList<User> users;

    public static MapsFragment newInstance(@NonNull ArrayList<User> users) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_USERS, users);
        MapsFragment mapsFragment = new MapsFragment();
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        users = getArguments().getParcelableArrayList(KEY_USERS);

        mapsFragmentInterface = (MapsFragmentInterface) getActivity();
        customMarker = inflater.inflate(R.layout.custom_marker, null);
        CircleImageView markerImage = customMarker.findViewById(R.id.user_dp);
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);


        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        map = googleMap;
        //googleMap.setMyLocationEnabled(true);
        addMarkers();

        LatLng latLng = new LatLng(53.47723, -2.25487);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                latLng, 15);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(location);

    }

    private void addMarkers() {
        for (User user : users) {
            LatLng latLng = new LatLng(user.getLatitude(), user.getLongitute());
            Glide.with(getContext())
                    .asBitmap()
                    .load(user.getPictureUri())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(resource, 100,
                                    100, true);
                            map.addMarker(new MarkerOptions().position(latLng)).setIcon(
                                    BitmapDescriptorFactory.fromBitmap(
                                            resizedBitmap));
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });


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

    public interface MapsFragmentInterface {
        void openUserProfile(LatLng location);
    }
}
