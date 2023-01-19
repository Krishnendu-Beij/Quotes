package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText : TextView get() = findViewById(R.id.quoteText)
    private val quoteAuthor : TextView get() = findViewById(R.id.quoteAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
//      for any type of configuration change our activity will destroyed and re-initialized, to avoid it application object is used in above line
//      as application object will not destroy for any type of configuration change, unless the app is close be user or android system
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }
    fun onShare(view: View) {
        val appIntent = Intent(Intent.ACTION_SEND)
        appIntent.type = "text/plain"
        appIntent.putExtra(Intent.EXTRA_TEXT, "${mainViewModel.getQuote().text} by ${mainViewModel.getQuote().author}")
        startActivity(appIntent)
    }
}