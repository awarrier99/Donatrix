package edu.gatech.donatrix.model

import java.sql.Timestamp

import java.io.Serializable


class Item(var time: Timestamp?, var location: Location?, private var sDescription: String?, private var fDescription: String?,
           var value: Double, var category: ItemCategory?, var comments: String?) : Serializable {

    fun getsDescription(): String? {
        return sDescription
    }

    fun setsDescription(sDescription: String) {
        this.sDescription = sDescription
    }

    fun getfDescription(): String? {
        return fDescription
    }

    fun setfDescription(fDescription: String) {
        this.fDescription = fDescription
    }

    //    @Override
    //    public boolean equals(@androidx.annotation.Nullable Object obj) {
    //        return super.equals(obj);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return super.hashCode();
    //    }
}