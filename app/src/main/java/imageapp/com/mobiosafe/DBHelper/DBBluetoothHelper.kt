package imageapp.com.mobiosafe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBBluetoothHelper(var context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VER
) {
    companion object {
        private val DATABASE_NAME = "mobisafe.db"
        private val DATABASE_VER = 1

        private val BLUETOOTH_TABLE_NAME = "Bluetooth"
        private val ID = "id"
        private val MASTER_ID = "master_id"
        private val BLUETOOTH_ADDRESS ="bluetooth_address"
        private val BLUETOOTH_NAME = "bluetooth_name"
        private val BLUETOOTH_CLASS = "bluetooth_class"
        private val BLUETOOTH_RSSI = "bluetooth_rssi"
        private val BLUETOOTH_DISTANCE = "bluetooth_distance"
        private val UNIQUE_KEY = "unique_key"
        private val TIMESTAMP = "timestamp"
        private val IS_EXTERNAL_UPDATED = "is_external_updated"
        private val IS_INTERNAL_UPDATED = "is_internal_updated"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val DROP_TABLE = ("DROP TABLE $BLUETOOTH_TABLE_NAME")
        //db!!.execSQL(DROP_TABLE)
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $BLUETOOTH_TABLE_NAME( " +
                "$ID INT, " +
                "$MASTER_ID INT," +
                "$BLUETOOTH_ADDRESS VARCHAR(20), " +
                "$BLUETOOTH_NAME VARCHAR(40)," +
                "$BLUETOOTH_DISTANCE VARCHAR(40)," +
                "$BLUETOOTH_CLASS INT(5)," +
                "$BLUETOOTH_RSSI INT," +
                "$UNIQUE_KEY VARCHAR(20)," +
                "$TIMESTAMP LONG," +
                "$IS_EXTERNAL_UPDATED BOOLEAN," +
                "$IS_INTERNAL_UPDATED BOOLEAN," +
                "CONSTRAINT bluetooth_pk PRIMARY KEY ($ID))")
        db!!.execSQL(CREATE_TABLE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun insertData(bluetoothData: BluetoothData) {
        val db = this.readableDatabase
        val query = "Select * from $BLUETOOTH_TABLE_NAME where $ID = ${bluetoothData.getId()}"
        val result1 = db.rawQuery(
            query,
            null
        )
        if( 0 == result1.count ) {

            val database = this.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(ID, bluetoothData.getId())
            contentValues.put(MASTER_ID, bluetoothData.getMasterId())
            contentValues.put(BLUETOOTH_ADDRESS, bluetoothData.getBluetoothAddress())
            contentValues.put(BLUETOOTH_NAME, bluetoothData.getBluetoothName())
            contentValues.put(BLUETOOTH_DISTANCE, bluetoothData.getBluetoothDistance())
            contentValues.put(BLUETOOTH_CLASS, bluetoothData.getBluetoothClass())
            contentValues.put(BLUETOOTH_RSSI, bluetoothData.getBluettothRssi())
            contentValues.put(UNIQUE_KEY, bluetoothData.getBluetoothUniqueKey())
            contentValues.put(TIMESTAMP, bluetoothData.getTimeStamp())
            contentValues.put(IS_EXTERNAL_UPDATED, bluetoothData.getExternlUpdated())
            contentValues.put(IS_INTERNAL_UPDATED, bluetoothData.getInternalUpdated())

            val result = database.insert(BLUETOOTH_TABLE_NAME, null, contentValues)
            if (result == (0).toLong()) {
                // Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                //Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun readData(): MutableList<BluetoothData> {
        val list: MutableList<BluetoothData> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $BLUETOOTH_TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var blData:BluetoothData = BluetoothData()
                blData.setId(result.getInt(result.getColumnIndex(ID)))
                blData.setMasterId(result.getInt(result.getColumnIndex(MASTER_ID)))
                blData.setBluetoothAddress(result.getString(result.getColumnIndex(BLUETOOTH_ADDRESS)))
                blData.setBluetoothName(result.getString(result.getColumnIndex(BLUETOOTH_NAME)))
                blData.setBluetoothDistance(result.getString(result.getColumnIndex(
                    BLUETOOTH_DISTANCE)))
                blData.setBluetoothClass(result.getString(result.getColumnIndex(BLUETOOTH_CLASS)))
                blData.setBluettothRssi(result.getInt(result.getColumnIndex(BLUETOOTH_RSSI)))
                blData.setBluetoothUniqueKey(result.getString(result.getColumnIndex(UNIQUE_KEY)))
                blData.setTimeStamp(result.getLong(result.getColumnIndex(TIMESTAMP)))
                // blData.setExternlUpdated(result.getInt(result.getColumnIndex(IS_EXTERNAL_UPDATED)))
                //blData.setInternalUpdated(result.getInt(result.getColumnIndex(IS_INTERNAL_UPDATED)))
                println("Values from DB: ${blData.getBluetoothName()}")
                list.add(blData)
            }
            while (result.moveToNext())
        }
        return list
    }

    fun readBlDataID( id:Int): BluetoothData {
        println("Values from DB to search : $id")
        val list: MutableList<BluetoothData> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $BLUETOOTH_TABLE_NAME where $ID='$id'"
        val result = db.rawQuery(
            query,
            null
        )
        var blData:BluetoothData = BluetoothData()

        if (result.moveToFirst()) {
            do {
                blData.setMasterId(result.getInt(result.getColumnIndex(MASTER_ID)))
                blData.setBluetoothAddress(result.getString(result.getColumnIndex(BLUETOOTH_ADDRESS)))
                blData.setBluetoothName(result.getString(result.getColumnIndex(BLUETOOTH_NAME)))
                blData.setBluetoothDistance(result.getString(result.getColumnIndex(
                    BLUETOOTH_DISTANCE)))
                blData.setBluetoothClass(result.getString(result.getColumnIndex(BLUETOOTH_CLASS)))
                blData.setBluettothRssi(result.getInt(result.getColumnIndex(BLUETOOTH_RSSI)))
                blData.setBluetoothUniqueKey(result.getString(result.getColumnIndex(UNIQUE_KEY)))
                blData.setTimeStamp(result.getLong(result.getColumnIndex(TIMESTAMP)))
               // blData.setExternlUpdated(result.getInt(result.getColumnIndex(IS_EXTERNAL_UPDATED)))
                //blData.setInternalUpdated(result.getInt(result.getColumnIndex(IS_INTERNAL_UPDATED)))
                println("Device Values from DB : ${blData.getBluetoothName()}")
            }while (result.moveToNext())
        }

        return blData
    }

}