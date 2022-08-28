package com.zxgaer.xgditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.View
import android.widget.Switch
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.lang.StringBuilder
import java.util.*

class addItemsActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_items)
        var toolbar = supportActionBar
        toolbar?.title = getString(R.string.addaddontitle) //设置标题栏文字

        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val newAddonPath = getExternalFilesDir(null)?.absolutePath
            val addonPathFile = newAddonPath + "/addons"
            val minecraftAddonPathFile = newAddonPath + "/minecraft"
            File(addonPathFile).mkdirs()
            File(minecraftAddonPathFile).mkdirs()
        }  //创建必要的文件夹
    }
    fun generateUUID(v: View) {
        (findViewById<View>(R.id.UUIDplain) as TextView).text = UUID.randomUUID().toString()
    }

    fun AddAddon(v: View) {
        val addonNameText = (findViewById<View>(R.id.addonNamePlain) as TextView).text.toString()
        val addonDescriptionText = (findViewById<View>(R.id.addonDescriptionPlain) as TextView).text.toString()
        val addonDeveloperText = (findViewById<View>(R.id.developer) as TextView).text.toString()
        val addonVersionText = (findViewById<View>(R.id.versionPlain) as TextView).text.toString()
        val minecraftVersion1 = (findViewById<View>(R.id.minecraftVersion1) as TextView).text.toString().toInt()
        val minecraftVersion2 = (findViewById<View>(R.id.minecraftVersion2) as TextView).text.toString().toInt()
        val minecraftVersion3 = (findViewById<View>(R.id.minecraftVersion3) as TextView).text.toString().toInt()
        val entryText = (findViewById<View>(R.id.entryplain) as TextView).text.toString()
        val uuidText = (findViewById<View>(R.id.UUIDplain) as TextView).text.toString()


        val a1 = findViewById<Switch>(R.id.minecraftmodule).isChecked
        val a2 = findViewById<Switch>(R.id.gametestmodule).isChecked
        val a3 = findViewById<Switch>(R.id.uimodule).isChecked
        val a4 = findViewById<Switch>(R.id.netmodule).isChecked
        val a5 = findViewById<Switch>(R.id.servermodule).isChecked
        //获取所有配置选项

        if(addonNameText.isNotEmpty() && addonDescriptionText.isNotEmpty() && addonDeveloperText.isNotEmpty() && addonVersionText.isNotEmpty()) {
            if(uuidText.isNotEmpty() && entryText.isNotEmpty()) {
                //当所有选项不为空时执行，text作为中间变量使用
                var text = getExternalFilesDir(null)?.absolutePath + "/addons/" + addonNameText
                File(text).mkdirs()
                text = getExternalFilesDir(null)?.absolutePath + "/addons/" + addonNameText + "/manifest.json"
                var manifestFile = File(text)
                manifestFile.createNewFile()
                var manifestJson = JSONObject()
                var modules = JSONArray().put(a1).put(a2).put(a3).put(a4).put(a5)
                var minecraftVersion = JSONArray().put(minecraftVersion1).put(minecraftVersion2).put(minecraftVersion3)

                manifestJson.put("name",addonNameText).put("description",addonDescriptionText)
                manifestJson.put("developer",addonDeveloperText).put("addon_version",addonVersionText)
                manifestJson.put("entry",entryText).put("minecraft_version",minecraftVersion)
                manifestJson.put("modules",modules).put("uuid",uuidText)

                manifestFile.writeText(manifestJson.toString(4))
                //设置manifest in addon文件夹的manifest.js格式

                text = getExternalFilesDir(null)?.absolutePath + "/addonlist.json"


                var y2 = File(text)
                var y = y2.inputStream()
                var inputText = BufferedReader(InputStreamReader(y)).useLines { lines ->
                    val results = StringBuilder()
                    lines.forEach { results.append(it) }
                    results.toString()
                }
                //文件不为空时直接读取json
                if(inputText.isNotEmpty()) {
                    var addonlist = JSONObject(inputText)
                    addonlist.put(addonNameText, JSONArray().put(addonDescriptionText).put(addonVersionText))
                    y2.writeText(addonlist.toString() + ",\n")
                }
                else {
                    var addonlist = JSONObject()
                    addonlist.put(addonNameText, JSONArray().put(addonDescriptionText).put(addonVersionText))
                    y2.writeText(addonlist.toString() + ",\n")
                }
                var ina = Intent()
                ina.setClass(this,addonEditActivity::class.java).putExtra("addonName",addonNameText)
                startActivity(ina)

            }
        }
    }
}