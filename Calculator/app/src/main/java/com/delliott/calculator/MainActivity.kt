package com.delliott.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.delliott.calculator.databinding.ActivityMainBinding

private const val STATE_PENDING_OP = "PendingOperation"
private const val STATE_OPERAND1 = "operand1"
private const val STATE_OPERAND_STORED = "operandStored"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            binding.newNumber.append(b.text)
        }
        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.buttonDot.setOnClickListener(listener)

        val negListener = View.OnClickListener { _ ->
            val newNumberText = binding.newNumber.text.toString()
            if (newNumberText.isEmpty()) {
                binding.newNumber.setText("-")
            } else {
                try {
                    val value = newNumberText.toDouble() * -1
                    binding.newNumber.setText(value.toString())
                } catch (e: NumberFormatException) {
                    //new number was "-" or ".", so need to clear it
                    binding.newNumber.setText("")
                }
            }
        }
        binding.buttonNegative.setOnClickListener(negListener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = binding.newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                binding.newNumber.setText("")
            }
            pendingOperation = op
            binding.operation.text = pendingOperation
        }

        binding.buttonEqual.setOnClickListener(opListener)
        binding.buttonDivide.setOnClickListener(opListener)
        binding.buttonMultiply.setOnClickListener(opListener)
        binding.buttonMinus.setOnClickListener(opListener)
        binding.buttonPlus.setOnClickListener(opListener)
    }

    private fun performOperation(value: Double, operation: String) {

        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN
                } else {
                    operand1!! / value
                }

                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        binding.result.setText(operand1.toString())
        binding.newNumber.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND_STORED, true)
        }
        outState.putString(STATE_PENDING_OP, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OP, "")
        binding.operation.text = pendingOperation
        operand1 = if (savedInstanceState.getBoolean(STATE_OPERAND_STORED, false)) {
            savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            null
        }
    }
}