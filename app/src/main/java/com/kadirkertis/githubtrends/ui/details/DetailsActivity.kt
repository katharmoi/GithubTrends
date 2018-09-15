package com.kadirkertis.githubtrends.ui.details

import android.os.Bundle
import com.kadirkertis.githubtrends.R
import dagger.android.support.DaggerAppCompatActivity

class DetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Display Back Navigation icon
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}
