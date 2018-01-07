package com.example.ka.mykotlinexample.presenter

import com.example.ka.mykotlinexample.model.Image
import com.example.ka.mykotlinexample.view.IView
import java.text.FieldPosition

/**
 * Created by KA on 1/7/2018.
 */
class PresenterImp(var view : IView) : IPresenter {


    override fun addImage(image: Image) {
        image.save()
        view.addImageSuccessfully(image)
    }

    override fun deleteImage(position: Int,img : Image) {
        var image = Image.findById(Image::class.java,img.id)
        image.delete()
        view.deleteImageSuccessfully(position)
    }
}