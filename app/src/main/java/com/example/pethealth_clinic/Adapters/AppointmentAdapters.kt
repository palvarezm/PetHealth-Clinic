package com.example.pethealth_clinic.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pethealth_clinic.R
import com.google.gson.JsonArray

class AppointmentAdapters(private val fragment: Fragment) : RecyclerView.Adapter<AppointmentAdapters.ViewHolder>() {
    override fun getItemCount(): Int{
        return cardInfo!!.size()
    }

    private var cardInfo: JsonArray? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_appointment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jsonCardInfo = cardInfo!!.get(position).asJsonObject
        val fullDate = jsonCardInfo.get("appointment").asJsonObject.get("appt_date").asString
        val fullStartTime = jsonCardInfo.get("appointment").asJsonObject.get("start_t").asString

        holder.veterinaryTextView.text = jsonCardInfo.get("veterinary").asJsonObject.get("name").asString
        holder.vetTextView.text = jsonCardInfo.get("veterinarian").asJsonObject.get("name").asString
        holder.dateTextView.text = fullDate.substring(0, Math.min(fullDate.length, 10))
        holder.descriptionTextView.text = jsonCardInfo.get("appointment").asJsonObject.get("desc").asString
        holder.startTimeTextView.text = fullStartTime.substring(11, Math.min(fullDate.length, 16))

    }

    fun setAppointments(cardInfo: JsonArray) {
        this.cardInfo = cardInfo
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var vetTextView: TextView
        internal var veterinaryTextView: TextView
        internal var dateTextView: TextView
        internal var startTimeTextView: TextView
        internal var descriptionTextView: TextView
        internal var veterinarianButton: Button

        init {
            vetTextView = itemView.findViewById<View>(R.id.vetTextView) as TextView
            veterinaryTextView = itemView.findViewById<View>(R.id.veterinaryTextView) as TextView
            dateTextView = itemView.findViewById<View>(R.id.dateTextView) as TextView
            startTimeTextView = itemView.findViewById<View>(R.id.startTimeTextView) as TextView
            descriptionTextView = itemView.findViewById<View>(R.id.descriptionTextView) as TextView
            veterinarianButton = itemView.findViewById<View>(R.id.veterinarianButton) as Button
        }
    }
}
