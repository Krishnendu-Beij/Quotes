package com.example.quotes

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList : Array<Quote> = emptyArray()
    private var index = 0
    private var totalData = 0
    init {
        quoteList = loadDataFromAssets()
    }

    private fun loadDataFromAssets(): Array<Quote> {
        val inputData = context.assets.open("quotes.json") // getting data from the json file
//        val inputData = context.assets.open("quotes_test.json") // getting data from the json file
        val size : Int = inputData.available() // getting the size of inputData
        val buffer = ByteArray(size) // storing the size
        inputData.read(buffer)
        inputData.close()

        val myJson = String(buffer, Charsets.UTF_8)
        val myGson = Gson()
        totalData = myJson.length
        Log.d("Checking","${myJson.length}")
        Log.d("Checking","$totalData")
        return myGson.fromJson(myJson , Array<Quote>::class.java)

    }
    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
//    fun nextQuote() = if (index < totalData) quoteList[++index] else quoteList[1]
    fun previousQuotes() = quoteList[--index]
}