package com.example.pethealth_clinic.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pethealth_clinic.Adapters.AppointmentAdapters
import com.example.pethealth_clinic.Persistence.SharedPreferencesManager
import com.example.pethealth_clinic.R
import com.example.pethealth_clinic.network.LoggerCallback
import com.example.pethealth_clinic.network.RestClient
import com.example.pethealth_clinic.network.RestView
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *b
 */
class AppointmentsFragment : Fragment() {

    private var appointmentRecyclerView: RecyclerView? = null
    private var appointmentAdapters: AppointmentAdapters? = null
    private var appointmentLayoutManager: RecyclerView.LayoutManager? = null
    private val fragment = this

    private var answer: RestView<JsonArray>? = null
    private var sharedPreferencesManager: SharedPreferencesManager? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //sharedPreferencesManager = SharedPreferencesManager.getInstance(this.context)
        val view = inflater.inflate(R.layout.fragment_appointments, container, false)
        updateAppointment()
        appointmentRecyclerView = view.findViewById(R.id.appointmentRecyclerView)
        appointmentLayoutManager = GridLayoutManager(view.getContext(), 1)
        return view
    }

    override fun onResume() {
        super.onResume()
        updateAppointment()
    }

    private fun updateAppointment() {
        val call = RestClient().webServices
            .getAppts(sharedPreferencesManager?.accessToken, sharedPreferencesManager?.user?.id)
        call.enqueue(object : LoggerCallback<RestView<JsonArray>>() {
            override fun onResponse(call: Call<RestView<JsonArray>>, response: Response<RestView<JsonArray>>) {
                super.onResponse(call, response)
                answer = response.body()
                appointmentAdapters = AppointmentAdapters(fragment)
                appointmentRecyclerView?.adapter = appointmentAdapters
                appointmentRecyclerView?.layoutManager = appointmentLayoutManager
                appointmentAdapters!!.setAppointments(answer!!.data)
                appointmentAdapters!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<RestView<JsonArray>>, t: Throwable) {
                super.onFailure(call, t)
                Log.d("FAILURE", t.toString())
            }
        })

    }

}
