package app.halfmouth.android.viewmodel

import android.util.Log
import app.halfmouth.android.data.firebase.RealTimeItemFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotificationViewModel : ViewModel() {

    private var _listNotifications = MutableStateFlow(emptyList<RealTimeItemFirebase>())
    val listNotifications: StateFlow<List<RealTimeItemFirebase>> = _listNotifications

    init {
        getInfoFirebaseRealTimeDatabase()
    }

    private fun getInfoFirebaseRealTimeDatabase() {
        val firebaseDB = FirebaseDatabase.getInstance()
        val ref = firebaseDB.getReference("notifications")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val matches = dataSnapshot.getValue<HashMap<String, RealTimeItemFirebase>>()
                    matches?.let {
                        val item = it.values
                        item.forEach { notifications ->
                            val tempList = _listNotifications.value.toMutableList()
                            tempList.add(notifications)
                            _listNotifications.value = tempList
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Error", "Error $e")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Error to read Firebase!", error.toException())
            }
        })
    }
}
