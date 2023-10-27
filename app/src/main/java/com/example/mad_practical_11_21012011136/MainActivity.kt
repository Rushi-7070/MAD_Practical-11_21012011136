package com.example.mad_practical_11_21012011136


import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fav=findViewById<FloatingActionButton>(R.id.fav1)
        fav.setOnClickListener {
            sendDatatoListview()
//            Intent(this,MapsActivity::class.java).apply { startActivity(this) }
        }
    }

    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<Contact>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Contact(jsonObject)
                personList.add(person)
            }
            val personListView=findViewById<ListView>(R.id.listview1)
            personListView.adapter = ContactAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
    fun sendDatatoListview(){
//        val personListView=findViewById<ListView>(R.id.listview1)
//        val personList= arrayListOf<Contact>(
//            Contact("1","SRK","Abc@gmail.com","54123697871","Ganpat University",20.5937,78.9629),
//            Contact("2","VP","Xyz@gmail.com","456476845484","Ganpat University",20.5937,78.9629),
//            Contact("3","RP","Rhp@gmail.com","41756567457","Ganpat University",20.5937,78.9629),
//            Contact("4","SV","Sjv@gmail.com","54587545847","Ganpat University",20.5937,78.9629),
//
//
//        )
//        personListView.adapter=ContactAdapter(this,personList)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = HttpRequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                withContext(Dispatchers.Main) {
                    try {
                        if(data != null)
                            runOnUiThread{getPersonDetailsFromJson(data)}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}