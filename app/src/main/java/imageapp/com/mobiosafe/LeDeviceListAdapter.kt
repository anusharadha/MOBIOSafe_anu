package imageapp.com.mobiosafe

import android.R
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class LeDeviceListAdapter(private val cxt: Context ) : BaseAdapter() {
    private var mLeDevices: ArrayList<BluetoothDevice> = ArrayList()
    val mInflator: LayoutInflater = LayoutInflater.from(cxt)

    fun addDevice(device: BluetoothDevice) {
        if (!mLeDevices!!.contains(device)) {
            mLeDevices!!.add(device)
        }
        println("Device List:$mLeDevices")
    }

    fun getDevice(position: Int): BluetoothDevice? {
        return mLeDevices!![position]
    }

    fun clear() {
        mLeDevices!!.clear()
    }

    override fun getCount(): Int {
        return mLeDevices!!.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getItem(i: Int): Any? {
        return mLeDevices!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    /*override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        var view: View? = view
        val viewHolder: ViewHolder
        // General ListView optimization code.
        if (view == null) {
            view = mInflator.inflate(R.layout.listitem_device, null)
            viewHolder = ViewHolder()
            viewHolder.deviceAddress = view.findViewById(R.id.device_address) as TextView
            viewHolder.deviceName = view.findViewById(R.id.device_name) as TextView
            view.setTag(viewHolder)
        } else {
            viewHolder = view.getTag()
        }
        val device = mLeDevices!![i]
        val deviceName = device.name
        if (deviceName != null && deviceName.length > 0) viewHolder.deviceName.setText(deviceName) else viewHolder.deviceName.setText(
            R.string.unknown_device
        )
        viewHolder.deviceAddress.setText(device.address)
        return view
    }*/

}
