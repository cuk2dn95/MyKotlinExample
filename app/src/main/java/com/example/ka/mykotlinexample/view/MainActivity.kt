package com.example.ka.mykotlinexample.view

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.util.Base64
import com.example.ka.mykotlinexample.R
import com.example.ka.mykotlinexample.R.id.name
import com.example.ka.mykotlinexample.model.Image
import com.example.ka.mykotlinexample.presenter.IPresenter
import com.example.ka.mykotlinexample.presenter.PresenterImp
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.net.URL


class MainActivity : AppCompatActivity(),IView,Adapter.ActivityListener {

    override fun deleteImage(position: Int, image: Image) {

        presenter?.deleteImage(position,image)

    }


    var adapter : Adapter? = null
    var presenter : IPresenter? = null



    override fun addImageSuccessfully(image: Image) {

        adapter?.addImage(image)
        recycler_view.scrollToPosition(adapter!!.itemCount -1 )
    }

    override fun deleteImageSuccessfully(position: Int) {

        adapter?.deleteImage(position)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set up recycler view
        recycler_view?.layoutManager = LinearLayoutManager(this)
        adapter = Adapter()
        adapter!!.listImage = Image.listAll(Image::class.java)
        adapter!!.cxt = this
        adapter!!.listener = this
        recycler_view?.adapter = adapter



        presenter = PresenterImp(this)

        btnUpload?.setOnClickListener {
            if (name.text.toString() != "" && image.text.toString() != "")
            {

                toast("dang tai anh")

                var imgField = image.text.toString()
                var nameField = name.text.toString()

                var imgBundle = Bundle()
                imgBundle.putString("name",nameField)
                imgBundle.putString("img",imgField)

                var asyncTask = LoadingImage(presenter!!)
                asyncTask.execute(imgBundle)

                name.text.clear()
                image.text.clear()



            }
            else {
                toast("vui long dien day thu thong tin")
            }

        }

    }

    // asyncTask  loading image
    class LoadingImage(var presenter: IPresenter?) : AsyncTask<Bundle,Unit,Image>(){



        override fun doInBackground(vararg p0: Bundle?): Image {
         var url = URL(p0[0]?.getString("img"))
        var img = Image(p0[0]?.getString("name"),Base64.encodeToString(url.readBytes(),Base64.DEFAULT))

            return img
        }


        override fun onPostExecute(result: Image?) {
            super.onPostExecute(result)
            presenter?.addImage(result!!)

        }
    }

}
