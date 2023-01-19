package com.example.quotes

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList : Array<Quote> = emptyArray()
    private var index = 0
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
        return myGson.fromJson(myJson , Array<Quote>::class.java)
    }
    fun getQuote() = quoteList[index]
//    fun nextQuote() = quoteList[++index]
    fun nextQuote() : Quote {
        if (index == quoteList.size - 1) {
            Toast.makeText(context,"Quote List Ended",Toast.LENGTH_LONG).show()
            return quoteList[quoteList.size - 1]
        } else {
            return quoteList[++index]
        }
    }

//    fun previousQuotes() = quoteList[--index]
    fun previousQuote() : Quote {
        return if (index == 0) {
            Toast.makeText(context,"Press NEXT for more Quotes",Toast.LENGTH_LONG).show()
            quoteList[0]
        } else {
            quoteList[--index]
        }
    }

}