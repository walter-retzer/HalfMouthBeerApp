package app.halfmouth.android.viewmodel

import android.util.Log
import android.widget.Toast
import app.halfmouth.android.data.firebase.RealTimeItemFirebase
import app.halfmouth.android.main.AndroidApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel: ViewModel(){

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    init{
        getInfoFirebaseRealTimeDatabase()
    }

    private fun getInfoFirebaseRealTimeDatabase() {
        val context = AndroidApp.applicationContext
        val firebaseDB = FirebaseDatabase.getInstance()
        val ref = firebaseDB.getReference("notifications")
        var itemUpdate: RealTimeItemFirebase? = null

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeItemFirebase>>()
                    matches?.let {
                        val item = it.values

                        item.map { notifications ->
                            itemUpdate = notifications
                        }
                    }
                    _message.value = itemUpdate?.message.toString()

                    Toast.makeText(context, "Firebase - message: ${itemUpdate?.message}", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Firebase - data: ${itemUpdate?.date}", Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    Toast.makeText(context, "Erro $e", Toast.LENGTH_LONG).show()
                    Log.d("Error", "Error $e")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })

    }

}