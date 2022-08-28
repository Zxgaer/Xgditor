package com.zxgaer.xgditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class codeBlocksListActivity : AppCompatActivity() {
    private fun openFile(filename:String): String {
        var isn = BufferedReader(InputStreamReader(assets.open(filename)))
        var isn3 = isn.readLines().toString()
        isn3.take(isn3.length - 1)
        return isn3.substring(1)
        isn.close()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_blocks_list)
        //supportActionBar?.title = getString(R.string.code) + getString(R.string.list)
        var codeBlocksJson = JSONObject(openFile("codes.json"))
        var f = codeBlocksJson.getJSONArray("codes").toString()
        supportActionBar?.title = f
    }
    fun button1146514(v: View) {
        val inte = Intent().putExtra("dta","dat")
        setResult(RESULT_OK,inte)
        finish()
    }
}