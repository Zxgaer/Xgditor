package com.zxgaer.xgditor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class codeAdapter(val codeList: List<JSONObject>,context: Context,jsonObject: JSONObject): RecyclerView.Adapter<codeAdapter.codeViewHolder>() {
    var contextI = context
    var jsonObject = jsonObject
    class codeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun openFile(context: Context,filename:String): String {
            var isn = BufferedReader(InputStreamReader(context.assets.open(filename))).useLines { lines ->
                val results = StringBuilder()
                lines.forEach { results.append(it) }
                results.toString()
            }
            return isn
        }
        fun bind(jsonObject: JSONObject) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): codeViewHolder {
        val vie = generateView().main(parent.context,jsonObject)
        val view = LayoutInflater.from(contextI).inflate(R.layout.method_code_block,parent,false)
        view.findViewById<LinearLayout>(R.id.methodll).addView(vie)
        return codeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return codeList.size
    }

    override fun onBindViewHolder(holder: codeViewHolder, position: Int) {
        holder.bind(codeList[position])
    }
}
