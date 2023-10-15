package com.piyushjt.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
// Toast.makeText(applicationContext, cnt, Toast.LENGTH_SHORT).show()
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun doo(){

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

            val btnAC = findViewById<AppCompatButton>(R.id.btnAC)
            val btnC = findViewById<AppCompatButton>(R.id.btnC)

            val btnPls= findViewById<AppCompatButton>(R.id.btnPls)
            val btnMns= findViewById<AppCompatButton>(R.id.btnMns)
            val btnMltpl= findViewById<AppCompatButton>(R.id.btnMltpl)
            val btnDvd= findViewById<AppCompatButton>(R.id.btnDvd)

            val btnAns= findViewById<AppCompatButton>(R.id.btnAns)


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
                addPnt()
            }

            btnAC.setOnClickListener {
                clear("AC")
            }
            btnC.setOnClickListener {
                clear("C")
            }

            btnPls.setOnClickListener {
                oprtn("+")
            }
            btnMns.setOnClickListener {
                oprtn("-")
            }
            btnMltpl.setOnClickListener {
                oprtn("×")
            }
            btnDvd.setOnClickListener {
                oprtn("/")
            }

            btnAns.setOnClickListener {
                ans()
            }
        }

        doo()
    }
    fun addNo(no: String){
        val calcText= findViewById<TextView>(R.id.CalcTxt)
        val dispTxt= calcText.text

        if (dispTxt!="0"){
            if(dispTxt.length < 41) {
                val txt = dispTxt.toString() + no
                calcText.text = txt
            }

            else{
                Toast.makeText(applicationContext, "Maximum length reached", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            calcText.text = no
        }
    }

    @SuppressLint("SetTextI18n")
    fun addPnt(){
        val calcText= findViewById<TextView>(R.id.CalcTxt)
        val dispTxt= calcText.text.toString()


        val lasts = listOf<Int>(dispTxt.lastIndexOf("+"),
            dispTxt.lastIndexOf("-"),
            dispTxt.lastIndexOf("×"),
            dispTxt.lastIndexOf("/"))

        val lstIndx= lasts.max()

        val dsplTxtFrPnt = dispTxt.subSequence(lstIndx+1,dispTxt.length)

        if (dsplTxtFrPnt.contains(".")){
        }
        else{
            calcText.text= ("$dispTxt.")
        }

    }

    fun clear(CorAC: String){
        val calcText= findViewById<TextView>(R.id.CalcTxt)
        var dispTxt= calcText.text

        if(CorAC == "AC"){
            calcText.text= "0"
        }
        else if (CorAC == "C"){
            if (dispTxt.length != 1) {
                calcText.text = dispTxt.dropLast(1)
            }
            else{
                calcText.text= "0"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun oprtn(opp: String){
        val calcText= findViewById<TextView>(R.id.CalcTxt)
        val dispTxt= calcText.text.toString()

        if (dispTxt.last().toString() !in listOf<String>("+", "-", "×", "/")) {
            calcText.text = ("$dispTxt$opp")
        }
    }

    fun ans(){
        val calcText= findViewById<TextView>(R.id.CalcTxt)
        val dispTxt= calcText.text.toString()

        val ansText= findViewById<TextView>(R.id.AnsTxt)

        val operators= listOf("+", "-", "×", "/")

        if (operators.any { dispTxt.endsWith(it) }) {
            ansText.text = "Syntax Error"
        } else {
            try {
                val result = evaluateExpression(dispTxt)
                ansText.text = "$result"
            } catch (e: Exception) {
                ansText.text = "Error"
            }
        }


    }

    fun evaluateExpression(expression: String): Double {
        try {
            val parts = expression.split(Regex("(?=[+×/\\-])|(?<=[+×/\\-])"))

//            Toast.makeText(applicationContext, parts.toString(), Toast.LENGTH_LONG).show()

            val values = mutableListOf<Double>()
            val ops = mutableListOf<Char>()

            for (part in parts) {
                when {
                    part.matches(Regex("[0-9]+(\\.[0-9]+)?")) -> values.add(part.toDouble())
                    part in listOf("+", "-", "×", "/") -> ops.add(part[0])
                    else -> throw IllegalArgumentException("Invalid character: $part")
                }
            }

            while (ops.isNotEmpty()) {
                val op = ops.removeAt(0)
                val a = values.removeAt(0)
                val b = values.removeAt(0)
                values.add(performOperation(a, b, op))
            }

            if (values.size == 1) {
                return values[0]
            } else {
                throw IllegalArgumentException("Invalid expression")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun performOperation(a: Double, b: Double, operator: Char): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '×' -> a * b
            '/' -> a / b
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }
    }
}

