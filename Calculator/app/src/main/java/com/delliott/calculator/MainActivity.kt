package com.delliott.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.delliott.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel: CalculatorViewModel by viewModels()
        viewModel.result.observe(this) { stringResult ->
            binding.result.setText(stringResult)
        }
        viewModel.newNumber.observe(this) { stringNumber ->
            binding.newNumber.setText(stringNumber)
        }
        viewModel.operation.observe(this) { stringOperation ->
            binding.operation.text = stringOperation
        }

        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
            viewModel.negPressed()
        }
        binding.buttonNegative.setOnClickListener(negListener)

        val opListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())
        }

        binding.buttonEqual.setOnClickListener(opListener)
        binding.buttonDivide.setOnClickListener(opListener)
        binding.buttonMultiply.setOnClickListener(opListener)
        binding.buttonMinus.setOnClickListener(opListener)
        binding.buttonPlus.setOnClickListener(opListener)

        val clear = View.OnClickListener {
            viewModel.clear()
        }
        binding.buttonClear.setOnClickListener(clear)
    }
}