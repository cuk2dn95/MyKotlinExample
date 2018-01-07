package com.example.ka.mykotlinexample.presenter

import com.example.ka.mykotlinexample.model.Image
import java.text.FieldPosition

/**
 * Created by KA on 1/7/2018.
 */
interface IPresenter {
    fun addImage(image : Image)
    fun deleteImage(position: Int,image: Image)
}