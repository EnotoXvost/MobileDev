package ru.mirea.afanaseviv.mireaproject;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public MapFragment() {
        // Required empty public constructor
    }
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private MapView mapView;
    List<MapPlaces> places = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue("ru.mirea.afanaseviv.mireaprojec");

        places.add(new MapPlaces(" KRASNAYA PLOSHAD",
                "Красиво там)", "Красная площадь",
                new GeoPoint(55.753544, 37.621202)));

        places.add(new MapPlaces("GALLERY",
                "Государственная третьяковская галерея", "ул. Крымский вал, 10",
                new GeoPoint(55.735013, 37.605742)));

        places.add(new MapPlaces("MUSEUM",
                "Музей ВС РФ", "ул. Советской Армии, 2с1",
                new GeoPoint(55.784822, 37.616914)));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = view.findViewById(R.id.map);
        EditText searchField = view.findViewById(R.id.search_field);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(10);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        addMarkersToMap();

        searchField.setOnEditorActionListener((v, actionId, event) -> {
            String searchText = v.getText().toString();
            searchPlaces(searchText);
            return true;
        });

        return view;
    }

    private void addMarkersToMap() {
        for (MapPlaces place : places) {
            addMarker(place.getLocation(), place.getName(), place.getDescription(), place.getAddress());
        }
    }


    private void addMarker(GeoPoint point, String title, String description, String address) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(title + "\n" + description + "\nАдрес: " + address);
        mapView.getOverlays().add(marker);
    }


    private void searchPlaces(String query) {
        mapView.getOverlays().clear();
        if (query.isEmpty()) {
            addMarkersToMap();
        } else {
            for (MapPlaces place : places) {
                if (place.getName().toLowerCase().contains(query.toLowerCase())) {
                    addMarker(place.getLocation(), place.getName(), place.getDescription(), place.getAddress());
                }
            }
        }

        mapView.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}