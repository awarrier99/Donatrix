package edu.gatech.donatrix.model

import java.io.Serializable
import edu.gatech.donatrix.dao.Database
import android.content.Context


class Location : Serializable {
    var key: Int = 0 //Paul rename to id or uid & Davidson initialized it to 0 for Kotlin support
    var name: String? = null
        private set //Paul: rename to title
    var latitude: String? = null
        private set //Paul: extract into coordinate object
    var longitude: String? = null
        private set //Paul: extract into coordinate object
    var address: String? = null
        private set //Paul: extract into address object
    var city: String? = null
        private set //Paul: extract into address object
    var state: String? = null
        private set //Paul: extract into address object
    var zip: String? = null
        private set //Paul: extract into address object
    var locationType: LocationType? = null
        private set
    var number: String? = null
        private set //Paul: rename to phoneNumber or telephone
    var website: String? = null
        private set
    private val inventory: ItemManager? = null

    constructor(name: String) {
        this.name = name
    }

    //Paul: info needs to be of type HashMap<String><String>
    constructor(info: Array<String>) {
        this.key = Integer.parseInt(info[0])
        this.name = info[1]
        this.latitude = info[2]
        this.longitude = info[3]
        this.address = info[4]
        this.city = info[5]
        this.state = info[6]
        this.zip = info[7]
        //Paul: the line below violates LOD, changes in LocationType can break this file:
        this.locationType = LocationType.valueOf(info[8].replace(" ", "").toUpperCase())
        this.number = info[9]
        this.website = info[10]
    }

    fun addItem(item: Item, context: Context, employee: LocationEmployee) {
        Database.getInstance(context)!!.addItem(item, employee)
    }

    fun removeItem(item: Item) {
        this.inventory!!.removeItem(item)
    }

    override fun toString(): String {
        //Paul: ugly syntax
        return String.format("%s | %s | %s | %s | %s | %s | %s | %s | %s | %s", name, latitude, longitude, address, city, state, zip, locationType!!.type, number, website)
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj !is Location) {
            false
        } else {
            //Paul: ugly syntax
            this.key == obj.key && this.address == obj.address && this.latitude == obj.latitude && this.city == obj.city && this.locationType == obj.locationType && this.name == obj.name && this.longitude == obj.longitude && this.number == obj.number && this.state == obj.state && this.website == obj.website && this.zip == obj.zip
        }
    }

    override//Paul: not a proper hashcode method. Low priority
    fun hashCode(): Int {
        return this.key * this.name!!.length
    }
}
