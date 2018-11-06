package org.pondar.dialogfragmentdemokotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(),MyDialogFragment.OnPositiveListener
    {

        private lateinit var dialog: MyDialogFragment
        private lateinit var context: Context


        private var bag = ArrayList<String>()

        //This method is the one we need to implement from the
        //interface. It will be called when the user has clicked the
        //positive button (yes button):
        override fun onPositiveClicked() {
            //Do your update stuff here to the listview
            //and the bag etc
            //just to show how to get arguments from the bag.
            val toast = Toast.makeText(context,
                    "positive button clicked", Toast.LENGTH_LONG)
            toast.show()
            bag.clear() //here you can do stuff with the bag and
            //adapter etc.
            dataText.text = "" //clearing the data
        }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            context = this
            setContentView(R.layout.activity_main)
        }

        //This is the event handler for the show button
        //This is specified in the xml file that this should
        //be the event handler
        fun showDialog(v: View) {
            //showing our dialog.

            dialog = MyDialog()
            //Here we show the dialog
            //The tag "MyFragement" is not important for us.
            dialog.show(supportFragmentManager,"myFragment")
           // dialog.show(fragmentManager, "MyFragment")
        }

        class MyDialog : MyDialogFragment() {


            override fun negativeClick() {
                //Here we override the method and can now do something
                val toast = Toast.makeText(activity,
                        "negative button clicked", Toast.LENGTH_SHORT)
                toast.show()
            }


        }

    }