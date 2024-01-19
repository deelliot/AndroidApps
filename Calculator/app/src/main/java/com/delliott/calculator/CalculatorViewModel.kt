package com.delliott.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class CalculatorViewModel : ViewModel() {

    private var operand1: Double? = null
    private var pendingOperation = "="

    private val result = MutableLiveData<Double>()
    val stringResult: LiveData<String>
        get() = result.map { value -> value.toString() }

    private val newNumber = MutableLiveData<String>()
    val stringNewNumber: LiveData<String>
        get() = newNumber

    private val operation = MutableLiveData<String>()
    val stringOperation: LiveData<String>
        get() = operation


    fun digitPressed(caption: String) {
        if (newNumber.value != null) {
            newNumber.value += caption
        } else
            newNumber.value = caption
    }

    fun operandPressed(op: String) {
        try {
            val value = newNumber.value?.toDouble()
            if (value != null) {
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value = ""
        }
        pendingOperation = op
        operation.value = pendingOperation
    }

    fun negPressed() {
        val newNumberText = newNumber.value.toString()
        if (newNumberText.isEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                val value = newNumberText.toDouble() * -1
                newNumber.value = value.toString()
            } catch (e: NumberFormatException) {
                //new number was "-" or ".", so need to clear it
                newNumber.value = ""
            }
        }
    }

    fun clear() {
        operand1 = null
        newNumber.value = ""
        result.value = null
        pendingOperation = ""
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

        result.value = operand1
        newNumber.value = ""
    }
}