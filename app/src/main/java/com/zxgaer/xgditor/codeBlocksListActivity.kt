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
        var isn = BufferedReader(InputStreamReader(assets.open(filename))).useLines { lines ->
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
        var CONSTV = constInit(this).METHOD_CODE_BLOCK_VIEW
        val CODELIST = findViewById<View>(R.id.codeList) as LinearLayout
        for(index in 0 until codeBlocksJson.length()) {
            var block = codeBlocksJson.getJSONObject(index)
            var blockVar = block.getJSONArray("blocks")
            if(block.getString("type") == "method") {
                for(index2 in 0 until block.getJSONArray("blocks").length()) {
                    if(blockVar.getJSONObject(index2).getString("block_type") == "text") {
                        var text = TextView(this)
                        text.text = blockVar.getJSONObject(index2).getString("text")
                        text.textSize = 24.0F
                        CONSTV.findViewById<LinearLayout>(R.id.methodll).addView(text)
                    }
                    else if(blockVar.getJSONObject(index2).getString("block_type") == "input") {
                        var text = EditText(this)
                        CONSTV.findViewById<LinearLayout>(R.id.methodll).addView(text)
                    }
                    else if(blockVar.getJSONObject(index2).getString("block_type") == "input2") {
                        var text = EditText(this)
                        text.maxLines = 10
                        CONSTV.findViewById<LinearLayout>(R.id.methodll).addView(text)
                    }
                }
                CODELIST.addView(CONSTV)
                CONSTV = constInit(this).METHOD_CODE_BLOCK_VIEW
            }
        }



    }
    fun button1146514(v: View) {
        val inte = Intent().putExtra("dta","dat")
        setResult(RESULT_OK,inte)
        finish()
    }
}