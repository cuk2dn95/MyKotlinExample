package com.example.ka.mykotlinexample.view

import com.example.ka.mykotlinexample.model.Image


/**
 * Created by KA on 1/7/2018.
 */
interface IView {
    fun addImageSuccessfully(image: Image)
    fun deleteImageSuccessfully(id : Int)
}