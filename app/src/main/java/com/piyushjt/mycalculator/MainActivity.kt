package com.piyushjt.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn0 = findViewById<AppCompatButton>(R.id.btn0)
        val btn1 = findViewById<AppCompatButton>(R.id.btn1)
        val btn2 = findViewById<AppCompatButton>(R.id.btn2)
        val btn3 = findViewById<AppCompatButton>(R.id.btn3)
        val btn4 = findViewById<AppCompatButton>(R.id.btn4)
        val btn5 = findViewById<AppCompatButton>(R.id.btn5)
        val btn6 = findViewById<AppCompatButton>(R.id.btn6)
        val btn7 = findViewById<AppCompatButton>(R.id.btn7)
        val btn8 = findViewById<AppCompatButton>(R.id.btn8)
        val btn9 = findViewById<AppCompatButton>(R.id.btn9)
        val btn00 = findViewById<AppCompatButton>(R.id.btn00)
        val btnDot = findViewById<AppCompatButton>(R.id.btnDot)

        fun doo(){
            btn0.setOnClickListener {
                addNo("0")
            }
            btn1.setOnClickListener {
                addNo("1")
            }
            btn2.setOnClickListener {
                addNo("2")
            }
            btn3.setOnClickListener {
                addNo("3")
            }
            btn4.setOnClickListener {
                addNo("4")
            }
            btn5.setOnClickListener {
                addNo("5")
            }
            btn6.setOnClickListener {
                addNo("6")
            }
            btn7.setOnClickListener {
                addNo("7")
            }
            btn8.setOnClickListener {
                addNo("8")
            }
            btn9.setOnClickListener {
                addNo("9")
            }
            btn00.setOnClickListener {
                addNo("00")
            }
            btnDot.setOnClickListener {
                addNo(".")
            }
        }

        doo()
    }
    fun addNo(no: String){
        val mainText= findViewById<TextView>(R.id.MainText)
        val dispTxt= mainText.text

        if (dispTxt!="0"){
            if (no=="." && dispTxt.contains(".")){
            }
            else {
                val txt= dispTxt.toString() + no
                mainText.text = txt
            }
        }
        else {
            mainText.text = no
        }
    }
}