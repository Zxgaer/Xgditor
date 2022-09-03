package com.zxgaer.xgditor

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class generateView() {
    fun main(context: Context, jsonObject: JSONObject): View {
        var method = constInit(context).METHOD_CODE_BLOCK_VIEW
        var text:TextView = TextView(context)
        if(jsonObject.getString("type") == "method") {
            for(index2 in 0 until jsonObject.getJSONArray("blocks").length()) {
                when(jsonObject.getJSONArray("blocks").getJSONObject(index2).getString("block_type")) {
                    "text" -> {
                        text = TextView(context)
                        text.text = jsonObject.getJSONArray("blocks").getJSONObject(index2).getString("text")
                        text.textSize = 24.0F
                    }
                    "input" -> {
                        text = EditText(context)
                    }
                    "input2" -> {
                        text = EditText(context)
                        text.maxLines = 10
                    }
                    else -> {
                        text = TextView(context)
                    }
                }
                method.findViewById<LinearLayout>(R.id.methodll).addView(text)
            }
            return method
        }
        return method
    }
}