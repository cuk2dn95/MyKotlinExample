package com.example.ka.mykotlinexample.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.ka.mykotlinexample.R
import com.example.ka.mykotlinexample.model.Image
import org.jetbrains.anko.find

/**
 * Created by KA on 1/7/2018.
 */
class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {


    public  interface ActivityListener{
        fun deleteImage(position: Int,image: Image)
    }


    var listener : ActivityListener? = null
    set(value) {field =value}

    var listImage : MutableList<Image>? = null
    set(value) {field = value}

    var cxt : Context?=null
    set(value) {field=value}


    public fun  addImage(image: Image) {
        listImage?.add(image)
        notifyItemChanged(listImage!!.size +1)
        
    }

    public  fun  deleteImage(position: Int){
        listImage?.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var image : Image = listImage!!.get(position)

        var arrayImage : ByteArray = Base64.decode(image.imageBase64,Base64.DEFAULT)

        Glide.with(cxt!!).load(arrayImage).into(holder!!.image!!)

        holder!!.name!!.text = image.name

        holder!!.btnDelete!!.setOnClickListener() {

            listener?.deleteImage(position,image)
        }



            }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
       var  view : View = LayoutInflater.from(parent?.context).inflate(R.layout.item,
                            parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

       return  listImage!!.size


    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var name : TextView? = null
        var image : ImageView? = null
        var btnDelete : Button? = null


        init {
          name = view.findViewById(R.id.item_name)
          image = view.findViewById(R.id.item_image)
            btnDelete = view.findViewById(R.id.btnDelete)
        }
    }
}