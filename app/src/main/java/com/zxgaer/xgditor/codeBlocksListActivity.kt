package com.zxgaer.xgditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import com.zxgaer.xgditor.constInit

class codeBlocksListActivity : AppCompatActivity() {
    private fun openFile(filename:String): String {
        var isn = BufferedReader(InputStreamReader(this.assets.open(filename))).useLines { lines ->
            val results = StringBuilder()
            lines.forEach { results.append(it) }
            results.toString()
        }
        return isn
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_blocks_list)
        supportActionBar?.title = getString(R.string.code) + getString(R.string.list)


        val codeBlocksJson = JSONObject(openFile("codes.json")).getJSONArray("codes")
        val CODELIST = findViewById<View>(R.id.codeList) as LinearLayout
        for(index in 0 until codeBlocksJson.length()) {
            var block = codeBlocksJson.getJSONObject(index)
            var blockVar = block.getJSONArray("blocks")
            var methodView = generateView().main(this,block)
            methodView.setOnClickListener {
                var ina = Intent().putExtra("block",index)
                setResult(RESULT_OK,ina)
                finish()
            }
            CODELIST.addView(methodView)
        }
    }
}