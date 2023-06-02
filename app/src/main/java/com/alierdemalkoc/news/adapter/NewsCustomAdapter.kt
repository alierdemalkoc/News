package com.alierdemalkoc.news.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.alierdemalkoc.news.R
import com.alierdemalkoc.news.model.New
import com.bumptech.glide.Glide

class NewsCustomAdapter (private val context: Activity, private val list: List<New>) : ArrayAdapter<New>(context, R.layout.custom_list_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_list_item, null, true)

        val r_title = rootView.findViewById<TextView>(R.id.r_title)
        val r_image = rootView.findViewById<ImageView>(R.id.r_img)

        val new = list.get(position)

        r_title.text = "${new.title}"
        Glide.with(rootView).load(new.img).into(r_image)

        return rootView
    }

}