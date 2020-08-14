package com.fadzrian.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), Calculator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDigit(view: View) {
        if (text_result.text.toString().trim() == "0") text_result.text = ""
        text_result.append((view as Button).text.toString())
    }

    override fun onOperator(view: View) {
        text_result.append("${(view as Button).text}")
    }

    override fun onClear(view: View) {
        text_result.text = "0"
    }

    override fun onEqual(view: View) {
        try {
            val expression = ExpressionBuilder(text_result.text.toString()).build()
            val result = expression.evaluate()
            text_result.text = (if (result % 1 > 0) result else result.toInt()).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Operator cannot be executed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBreaketStart(view: View) {
        text_result.append("(")
    }

    override fun onBreaketEnd(view: View) {
        text_result.append(")")
    }

    override fun onDecimal(view: View) {
        text_result.append(".")
    }

    override fun onDelete(view: View) {
        val resultAfterDel = text_result.text.toString()
        if (resultAfterDel.isNotEmpty()) text_result.text = resultAfterDel.dropLast(1) else text_result.text = "0"
    }
}
