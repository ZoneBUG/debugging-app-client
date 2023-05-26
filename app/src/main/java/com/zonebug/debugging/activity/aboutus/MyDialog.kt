package com.zonebug.debugging.activity.aboutus

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.zonebug.debugging.R

class MyDialog(context : Context) {
    private val dlg = Dialog(context)
    private lateinit var lblDesc : TextView
    private lateinit var btnOK : Button

    fun start(content : String) {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.my_dialog)
        dlg.setCancelable(false)

        lblDesc = dlg.findViewById(R.id.content)
        lblDesc.text = content

        btnOK = dlg.findViewById(R.id.ok)
        btnOK.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }

}