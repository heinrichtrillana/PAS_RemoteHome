package com.example.remotehome.ui.livingroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.remotehome.R
import com.example.remotehome.databinding.FragmentLivingroomBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LivingRoomFragment : Fragment() {

    private var ROOM : String = "salon"

    private lateinit var livingRoomViewModel: LivingRoomViewModel
    private lateinit var binding : FragmentLivingroomBinding

    private var db = FirebaseFirestore.getInstance();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_livingroom,
            container,
            false
        )

        livingRoomViewModel =
            ViewModelProviders.of(this).get(LivingRoomViewModel::class.java)

        binding.viewModel = livingRoomViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        initDevicesValues()
        initAmbientValues()


        return binding.root
    }

    private fun initAmbientValues(){
        //Hacer el get de la API del tiempo para simular la temperatura y humedad interior.
    }

    private fun initDevicesValues(){

        db.collection(ROOM).get().addOnSuccessListener { result ->
            for (document in result) {
                when(document.id){
                    "ac"        -> binding.acValue.value = (document.data.get("value") as Number).toFloat()
                    "tv"        -> binding.tvValue.isChecked = document.data.get("value") as Boolean
                    "mainLight" -> binding.mainLightValue.isChecked = document.data?.get("value") as Boolean
                    else ->{}
                }
            }
            initListeners() //Wait for the values to be set before initializing the listeners
        }
    }

    private fun initListeners(){
        binding.mainLightValue.setOnCheckedChangeListener{ _, isChecked ->
            Toast.makeText(context, isChecked.toString(), Toast.LENGTH_SHORT).show()
            updateDB("mainLight", isChecked)
            //TODO : POST METHOD
        }

        binding.tvValue.setOnCheckedChangeListener{ _, isChecked ->
            Toast.makeText(context, isChecked.toString(), Toast.LENGTH_SHORT).show()
            updateDB("tv", isChecked)
            //TODO : POST METHOD
        }

        //binding.acValue.addOnChangeListener();
        //binding.setValue(33);

        binding.acValue.addOnChangeListener{_,value,_ ->
            Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
            //TODO : Find a debouncer to limit the continous calls
            updateDB("ac", value)
            //TODO : POST METHOD
        }

    }

    private fun updateDB( dispositivo : String, value : Any){

        //Añadir al historico la traza correspondiente
        val traza = HashMap<String, Any>()

        traza["habitacion"] = ROOM
        traza["dispositivo"] = dispositivo
        traza["value"] = value
        traza["timestamp"] = FieldValue.serverTimestamp()

        db.collection("historico")
            .add(traza)
            .addOnSuccessListener {
                Log.i("LivingRoom", "Saved on Firestore successfully")
            }
            .addOnFailureListener { e ->
                Log.w("LivingRoom", "Error adding document to Firestore", e)
            }

        //Añadir al documento que guarda el estado actual, el cambio de valor.
        val actual = HashMap<String, Any>()
        actual["value"] = value
        db.collection(ROOM)
            .document(dispositivo)
            .set(actual)
    }
}
