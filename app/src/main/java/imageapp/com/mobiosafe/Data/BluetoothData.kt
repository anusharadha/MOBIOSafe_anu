package imageapp.com.mobiosafe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.BoringLayout
import android.widget.Toast

class BluetoothData(
    private var id:Int = 0,
    private var master_id:Int = 0,
    private var bluetoothAddress:String ="",
    private var bluetoothName:String = "",
    private var bluetoothClass: String = "",
    private var bluetooth_distance: String = "",
    private var bluetoothRssi:Int = 0,
    private var unique_key:String = "",
    private var timestamp:Long = 0,
    private var is_external_updated:Boolean = false,
    private var is_internal_updated:Boolean = false
){

    fun getId() : Int
    {
        return id
    }

    fun getMasterId() : Int
    {
        return master_id
    }

    fun getBluetoothAddress() : String
    {
        return bluetoothAddress
    }

    fun getBluetoothName() : String
    {
        return bluetoothName
    }

    fun getBluetoothClass() : String
    {
        return bluetoothClass
    }

    fun getBluetoothDistance() : String
    {
        return bluetooth_distance
    }

    fun getBluettothRssi() : Int
    {
        return bluetoothRssi
    }

    fun getBluetoothUniqueKey() : String
    {
        return unique_key
    }

    fun getTimeStamp() : Long
    {
        return timestamp
    }

    fun getExternlUpdated() : Boolean
    {
        return is_external_updated
    }

    fun getInternalUpdated() : Boolean
    {
        return is_internal_updated
    }


    fun setId(id_val:Int)
    {
        id = id_val
    }

    fun setMasterId(masterId_val:Int)
    {
        master_id = masterId_val
    }

    fun setBluetoothAddress(bluetoothAddress_val:String)
    {
        bluetoothAddress = bluetoothAddress_val
    }

    fun setBluetoothName(bluetoothName_val:String)
    {
        bluetoothName = bluetoothName_val
    }

    fun setBluetoothClass(bluetoothClass_val : String)
    {
        bluetoothClass = bluetoothClass_val
    }

    fun setBluetoothDistance(bluetoothDistance_val : String)
    {
        bluetooth_distance = bluetoothDistance_val
    }

    fun setBluettothRssi(bluetoothRssi_val : Int)
    {
        bluetoothRssi = bluetoothRssi_val
    }

    fun setBluetoothUniqueKey(uniqueKey_val: String)
    {
        unique_key = uniqueKey_val
    }

    fun setTimeStamp(timeStamp_val: Long)
    {
        timestamp = timeStamp_val
    }

    fun setExternlUpdated(externalUpdated_val : Boolean)
    {
        is_external_updated = externalUpdated_val
    }

    fun setInternalUpdated(internalUpdated_val : Boolean)
    {
        is_internal_updated = internalUpdated_val
    }



}