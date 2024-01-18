package com.delliott.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.delliott.calculator.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private const val STATE_PENDING_OP = "PendingOperation"
private const val STATE_OPERAND1 = "operand1"
private const val STATE_OPERAND_STORED = "operandStored"


class MainActivity : AppCompatActivity() {
//    private lateinit var result: EditText //non nullable variable initiated later
//    private lateinit var newNumber: EditText
//    private val displayOperation: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.operation) }

    //variables to hold operands and types of calculations
    private var operand1: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)
//
//        // data input buttons
//        val button0: Button = findViewById(R.id.button0)
//        val button1: Button = findViewById(R.id.button1)
//        val button2: Button = findViewById(R.id.button2)
//        val button3: Button = findViewById(R.id.button3)
//        val button4: Button = findViewById(R.id.button4)
//        val button5: Button = findViewById(R.id.button5)
//        val button6: Button = findViewById(R.id.button6)
//        val button7: Button = findViewById(R.id.button7)
//        val button8: Button = findViewById(R.id.button8)
//        val button9: Button = findViewById(R.id.button9)
//        val buttonDot: Button = findViewById(R.id.buttonDot)
//
//        //operation buttons
//        val buttonEquals: Button = findViewById(R.id.buttonEqual)
//        val buttonDivide: Button = findViewById(R.id.buttonDivide)
//        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
//        val buttonMinus: Button = findViewById(R.id.buttonMinus)
//        val buttonPlus: Button = findViewById(R.id.buttonPlus)

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