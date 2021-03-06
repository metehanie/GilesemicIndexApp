package com.metehanbolat.gliserichomework.viewmodel

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.metehanbolat.gliserichomework.view.activity.MainActivity

class LoginFragmentViewModel: ViewModel() {

    val loadingControl = MutableLiveData<Boolean>()

    fun logInFirebase(activity: Activity, view: View, email: String, password: String, firebase: Firebase) {
        val auth = firebase.auth
        loadingControl.value = true
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Intent(activity.applicationContext, MainActivity::class.java).apply {
                    activity.startActivity(this)
                    activity.finish()
                }
            }else{
                loadingControl.value = false
                task.exception?.let {
                    Snackbar.make(view, it.localizedMessage!!, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}