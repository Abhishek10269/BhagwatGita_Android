package com.example.bhagwatgita

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.viewpager2.widget.ViewPager2
import com.example.bhagwatgita.models.GetAllVerses
import com.example.bhagwatgita.models.GetAllVersesItem
import com.example.bhagwatgita.models.GetParticularChap
import com.example.bhagwatgita.models.GetParticularVerse
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var obj= ApiCall()
        val chap= intent.getIntExtra("chap", 1)
        GlobalScope.launch(Dispatchers.IO) {
            val gson = Gson()
            val resp = obj.getAllVerses(chap)
            val jsonString = resp.body?.string()
            GlobalScope.launch(Dispatchers.Main) {

                val verses: List<GetAllVersesItem> = gson.fromJson(jsonString, Array<GetAllVersesItem>::class.java).toList()
                val view_pager2= findViewById<ViewPager2>(R.id.view_pager2)
                view_pager2.adapter= VerseAdapter(verses)
                view_pager2.orientation= ViewPager2.ORIENTATION_VERTICAL

            }
        }


//        homeBtn.setOnClickListener{
//            supportFragmentManager.commit {
//                replace<VerseContent>(R.id.)
//            }
//        }


    }
}