package com.zxgaer.xgditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File

import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            var text = getExternalFilesDir(null)?.absolutePath + "/addonlist.json"  //list文件路径
            var addonListFile = File(text)
            addonListFile.createNewFile()

            var y = addonListFile.inputStream()
            var inputText = BufferedReader(InputStreamReader(y)).useLines { lines ->
                val results = StringBuilder()
                lines.forEach { results.append(it) }
                results.toString()
            } //读取文件
            if(inputText.isNotEmpty()) {
                var jsonText = JSONObject(inputText)
                var addonArray = JSONArray()
                for (index in jsonText.keys()) {
                    var addonLayout = LinearLayout.inflate(this,R.layout.llayout,null)
                    var temp = addonLayout.findViewById<View>(R.id.btitle) as TextView
                    temp.text = index
                    temp = addonLayout.findViewById<View>(R.id.bdescription) as TextView
                    temp.text = jsonText.getJSONArray(index).getString(0)
                    temp = addonLayout.findViewById<View>(R.id.bgame_version) as TextView
                    temp.text = jsonText.getJSONArray(index).getString(1)
                    var addonListLayout = findViewById<View>(R.id.llayout2) as LinearLayout
                    addonListLayout.addView(addonLayout)
                    addonArray.put(addonLayout)
                }//批量将文本放进addon列表里
            }
        }


    }
    fun addGametestItem(v:View) {
        var ina = Intent()
        ina.setClass(this,addItemsActivity::class.java)
        startActivity(ina)
    }

    fun clickGametestItem(v:View) {
        var temp = findViewById<View>(R.id.addAddonsButton) as Button

    }
}


