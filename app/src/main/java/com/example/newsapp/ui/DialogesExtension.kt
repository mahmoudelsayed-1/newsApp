package com.example.newsapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showDialog(message:String,
                        posActionName:String?=null,
                        posAction : DialogInterface.OnClickListener?=null,
                        NegActionName:String?=null,
                        NegAction : DialogInterface.OnClickListener?=null,

                        ): AlertDialog {
    val dialog = AlertDialog.Builder(context)
    dialog.setMessage(message)
    if(posActionName!=null){

        dialog.setPositiveButton(posActionName,posAction)
    }
    if (NegActionName!=null){

        dialog.setNegativeButton(NegActionName,NegAction)
    }
    return dialog.show()
}

fun Activity.showDialog(message:String,
                        posActionName:String?=null,
                        posAction : DialogInterface.OnClickListener?=null,
                        NegActionName:String?=null,
                        NegAction : DialogInterface.OnClickListener?=null,

                        ): AlertDialog {
    val dialog = AlertDialog.Builder(this)
    dialog.setMessage(message)
    if(posActionName!=null){

        dialog.setPositiveButton(posActionName,posAction)
    }
    if (NegActionName!=null){

        dialog.setNegativeButton(NegActionName,NegAction)
    }
    return dialog.show()
}