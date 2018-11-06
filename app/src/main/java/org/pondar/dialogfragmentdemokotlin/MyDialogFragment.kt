package org.pondar.dialogfragmentdemokotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment

open class MyDialogFragment : DialogFragment() {


    private lateinit var mCallback: OnPositiveListener

    // Container Activity must implement this interface
    interface OnPositiveListener {
        fun onPositiveClicked()
    }

    //This method will be called when the dialog fragment is called
    //and "attached" to the current activity
    override fun onAttach(activity: Context) {
        super.onAttach(activity)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            //we can ONLY do this cast IF the activity
            //actually implements the interface.
            mCallback = activity as OnPositiveListener
        } catch (e: ClassCastException) {
            //This kills the program, because we have not
            //implemented the interface in the activity
            throw ClassCastException(activity.toString() + " must implement OnPositiveListener")
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Here we create a new dialogbuilder;
        val alert = AlertDialog.Builder(
                activity)
        alert.setTitle(R.string.confirmation)
        alert.setMessage(R.string.areYouSure)
        alert.setPositiveButton(R.string.yes, pListener)
        alert.setNegativeButton(R.string.no, nListener)

        return alert.create()
    }

    //This is our positive listener for when the user presses
    //the yes button
    private var pListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->
        // these will be executed when user click Yes button
        positiveClick()
    }

    //This is our negative listener for when the user presses
    //the no button.
    private var nListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ ->
        // these will be executed when user click No button
        negativeClick()
    }

    //This method ensures that we actually now call the implemented
    //method defined in the ACTIVITY!
    protected fun positiveClick() {

        mCallback.onPositiveClicked()
    }

    //This method is empty, because it will be overriden
    open fun negativeClick() {

    }
}