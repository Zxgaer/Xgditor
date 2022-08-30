package com.zxgaer.xgditor

import android.content.Context
import android.view.View
import android.widget.LinearLayout

class constInit(con:Context) {
    val ADDON_LIST_VIEW: View= LinearLayout.inflate(con,R.layout.llayout,null)
    val CONST_CODE_BLOCK_VIEW: View = LinearLayout.inflate(con,R.layout.const_code_block,null)
    val VAR_CODE_BLOCK_VIEW: View = LinearLayout.inflate(con,R.layout.var_code_block,null)
    val METHOD_CODE_BLOCK_VIEW: View = LinearLayout.inflate(con,R.layout.method_code_block,null)
    val METHOD2_CODE_BLOCK_VIEW: View = LinearLayout.inflate(con,R.layout.method2_code_block,null)
    val ANNOTATION_CODE_BLOCK_VIEW: View = LinearLayout.inflate(con,R.layout.annotation_code_block,null)
 }