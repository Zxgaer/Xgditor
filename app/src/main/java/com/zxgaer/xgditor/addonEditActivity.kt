package com.zxgaer.xgditor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


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
        //var codea = findViewById<View>(R.id.code_area) as LinearLayout
        //var ith = LinearLayout.inflate(this, R.layout.const_code_block, null)
        //codea.addView(ith)
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