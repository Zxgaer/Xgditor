package com.zxgaer.xgditor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder


class addonEditActivity : AppCompatActivity() {
    private fun openFile(filename:String): String {
        var isn = BufferedReader(InputStreamReader(this.assets.open(filename))).useLines { lines ->
            val results = StringBuilder()
            lines.forEach { results.append(it) }
            results.toString()
        }
        return isn
    }

    var codearray:ArrayList<JSONObject> = ArrayList<JSONObject>()
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
                if(resultCode == RESULT_OK && data != null) {
                    var block = data.getIntExtra("block",0)
                    var codeArea = findViewById<RecyclerView>(R.id.code_ar)
                    codearray.add(JSONObject().put("block",block))
                    codeArea.adapter = codeAdapter(
                        codearray,
                        this,
                        JSONObject(openFile("codes.json")).getJSONArray("codes").getJSONObject(block))
                }
            }
        }
    }
}
