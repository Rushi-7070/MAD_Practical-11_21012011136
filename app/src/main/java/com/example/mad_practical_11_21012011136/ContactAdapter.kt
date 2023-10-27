package com.example.mad_practical_11_21012011136

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class ContactAdapter (context:Context,val array:ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(val bindingView: View): RecyclerView.ViewHolder(bindingView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        view.findViewById<TextView>(R.id.name1).text=array.get(position).name
        view.findViewById<TextView>(R.id.no1).text=array.get(position).phoneNo
        view.findViewById<TextView>(R.id.email1).text=array.get(position).emailid
        view.findViewById<TextView>(R.id.address1).text=array.get(position).Address
        return view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.data_items,parent,false)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        with(holder){
            with(array[position]){
                bindingView.findViewById<TextView>(R.id.no1).text = this.phoneNo
                bindingView.findViewById<TextView>(R.id.name1).text = this.name
                bindingView.findViewById<TextView>(R.id.email1).text = this.emailid
                bindingView.findViewById<TextView>(R.id.address1).text = this.Address
                val obj = this as Serializable
                bindingView.findViewById<Button>(R.id.button1).setOnClickListener {
                    Intent(this@PersonAdapter.context, MapActivity::class.java).apply {
                        putextra("Object",obj)
                        this@PersonAdapter.context.startActivity(this)
                    }
                    //Toast.makeText(this@PersonAdapter.context, "Clicked on "+binding.textViewName+", Location: Lat:"+this.Latitude+"Long:"+this.Longitude, Toast.LENGTH_SHORT).show()
                }
                bindingView.findViewById<Button>(R.id.button_delete).setOnClickListener {
                    context.deletePerson(holder.adapterPosition)
                }
            }
        }
    }
}