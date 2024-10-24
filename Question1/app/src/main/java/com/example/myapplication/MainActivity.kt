package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.ApiService
import com.example.myapplication.models.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvRates: TextView
    private lateinit var etInput: EditText
    private lateinit var spinnerCurrency: Spinner
    private lateinit var CurrencyAdapter: CurrencyAdapter
    private val currencyList = mutableListOf<String>()
    private val rateList = mutableListOf<Float>()

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    tvResult = findViewById(R.id.tv_result)
    tvRates = findViewById(R.id.tv_rate)
    etInput = findViewById(R.id.etInput)
    spinnerCurrency = findViewById(R.id.spinCurrency)
    CurrencyAdapter = CurrencyAdapter(this, R.layout.item_selected, currencyList)
    spinnerCurrency.setAdapter(CurrencyAdapter)

    ApiService.apiService.ratecurrency("4262a80a613046068bceb52af8ec813f")
        .enqueue(object : Callback<Currency> {
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                val currencyResponse = response.body()
                if (response.isSuccessful && currencyResponse != null) {
                    currencyList.clear()
                    rateList.clear()
                    currencyResponse.rates?.let { rates ->
                        for ((currency, rate) in rates.toMap()) {
                            currencyList.add(currency)
                            rateList.add(rate)
                        }
                    }
                    val combinedList = currencyList.mapIndexed { index, currency ->
                        Pair(currency, rateList[index])
                    }
                    val sortedCombinedList = combinedList.sortedBy { it.first }
                    currencyList.clear()
                    rateList.clear()
                    sortedCombinedList.forEach {
                        currencyList.add(it.first)
                        rateList.add(it.second)
                    }
                    CurrencyAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
            if (i < rateList.size) {
                val rate = rateList[i]
                tvRates.text = "${DecimalFormat("#,###.00").format(rate)}"
                updateExchangeRate(rate)
            }
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }

    etInput.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val selectedIndex = spinnerCurrency.selectedItemPosition
            if (selectedIndex < rateList.size) {
                val rate = rateList[selectedIndex]
                updateExchangeRate(rate)
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

    private fun updateExchangeRate(rate: Float) {
        val inputText = etInput.text.toString()
        if (inputText.isNotEmpty()) {
            try {
                val inputValue = inputText.toFloat()
                val exchangedValue = inputValue * rate
                tvResult.text = "${DecimalFormat("#,###.00").format(exchangedValue)}"
            } catch (e: NumberFormatException) {
                tvResult.text = "Invalid input"
            }
        } else {
            tvResult.text = ""
        }
    }
}

