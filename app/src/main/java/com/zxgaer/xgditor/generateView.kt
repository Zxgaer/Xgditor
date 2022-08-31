package com.zxgaer.xgditor

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class generateView(context: Context,jsonArray: JSONArray,index: Int) {
    var context = context
    var jsonArray = jsonArray
    var index = index
    var block = jsonArray.getJSONObject(index)
    var blockVar = block.getJSONArray("blocks")
    var method = constInit(context).METHOD_CODE_BLOCK_VIEW
    var method2 = constInit(context).METHOD2_CODE_BLOCK_VIEW
    var constV = constInit(context).CONST_CODE_BLOCK_VIEW
    var varV = constInit(context).VAR_CODE_BLOCK_VIEW
    var annotation = constInit(context).ANNOTATION_CODE_BLOCK_VIEW
    fun main(): View {
        if(block.getString("type") == "method") {
            for(index2 in 0 until blockVar.length()) {
                var text2:View = when(blockVar.getJSONObject(index2).getString("block_type")) {
                    "text" -> {
                        var text = TextView(context)
                        text.text = blockVar.getJSONObject(index2).getString("text")
                        text.textSize = 24.0F
                        text
                    }
                    "input" -> {
                        var text = EditText(context)
                        text
                    }
                    "input2" -> {
                        var text = EditText(context)
                        text.maxLines = 10
                        text
                    }
                    else -> {
                        var text = TextView(context)
                        text
                    }
                }
                method.findViewById<LinearLayout>(R.id.methodll).addView(text2)
            }
            return method
        }
        return method
    }
}