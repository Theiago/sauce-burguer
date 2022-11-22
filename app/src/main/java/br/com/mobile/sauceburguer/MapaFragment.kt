package br.com.mobile.sauceburguer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapaFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null
    override fun onMapReady(googleMap: GoogleMap?) {
        this.map = googleMap
        val location = LatLng(-20.453945135436452, -54.61204474956154)
        val update = CameraUpdateFactory.newLatLngZoom(location, 18f)
        map?.moveCamera(update)
        map?.addMarker(
            MarkerOptions()

            .title("Sauce Burguer")
            .snippet("Hamburgueria Gourmet")
            .position(location)

        )
        map?.mapType=GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,

                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment)

                as SupportMapFragment

        mapFragment.getMapAsync(this)
        return view
    }

}