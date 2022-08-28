package com.zxgaer.xgditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.*


class addonEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addon_edit)
        var toolbar = supportActionBar
        toolbar?.title = intent.getStringExtra("addonName") + getString(R.string.edit)
    }
    fun add(v:View) {
        var ina = Intent()
        ina.setClass(this,codeBlocksListActivity::class.java)
        startActivityForResult(ina,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                if(resultCode == RESULT_OK) {
                    var toolbar = supportActionBar
                    toolbar?.title = data?.getStringExtra("dta")
                }
            }
        }
    }
}