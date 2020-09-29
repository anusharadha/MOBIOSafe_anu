package imageapp.com.mobiosafe

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    private val bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().bluetoothLeScanner
    private var mScanning = false
    private val handler = Handler()

    private var leDeviceListAdapter: LeDeviceListAdapter? = null
    lateinit var mainHandler: Handler
    private val REQUEST_ENABLE_BT = 1
    lateinit var dbHelper: DBBluetoothHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // Bottom Navigation
        navController = findNavController(R.id.hostFragment)
        bottom_navigation.setupWithNavController(navController)

        // Navigation UP button
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        // Drawer Navigation
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        NavigationUI.setupWithNavController(navigationView, navController)

        //trail
        //var headerView:View = LayoutInflater.from(this).inflate(R.layout.drawer_header,null)
        //navigationView.addHeaderView(headerView)
        //navigationView.getHeaderView(0).setVisibility(View.GONE)


        // Initializes Bluetooth adapter.
        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter

        // Ensures Bluetooth is available on the device and it is enabled. If not,
// displays a dialog requesting user permission to enable Bluetooth.
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        mainHandler = Handler(Looper.getMainLooper())
        dbHelper = DBBluetoothHelper(applicationContext)
        StartScan()
    }

    fun startService(v: View?) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        startService(serviceIntent)
    }

    fun stopService(v: View?) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



    private fun scanLeDevice() {
        val SCAN_PERIOD: Long = 1000
        leDeviceListAdapter = LeDeviceListAdapter(applicationContext)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1001
        )

        val leScanCallback: ScanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {

                println("Device scan result: ")
                var blData: BluetoothData = BluetoothData();
                blData.setId(result.advertisingSid)
                blData.setMasterId(result.advertisingSid)
                blData.setBluettothRssi(result.rssi)
                blData.setBluetoothAddress(result.device.address)
                blData.setBluetoothName(result.device.name)
                blData.setBluetoothClass(result.device.bluetoothClass.toString())
                dbHelper.insertData(blData)
                println("Device scan result name: ${result.device.name}")

                super.onScanResult(callbackType, result)

                leDeviceListAdapter!!.addDevice(result.device)
                leDeviceListAdapter!!.notifyDataSetChanged()
            }
        }
        if (!mScanning) { // Stops scanning after a pre-defined scan period.
            handler.postDelayed({
                mScanning = false
                println("Device scan postdelayed")
                bluetoothLeScanner.stopScan(leScanCallback)
            }, SCAN_PERIOD)
            mScanning = true
            println("Device scan started")
            bluetoothLeScanner.startScan(leScanCallback)
        } else {
            println("Device scan stopped")
            mScanning = false
            bluetoothLeScanner.stopScan(leScanCallback)
        }

    }


    fun StartScan()
    {
        val delay = 10000 //milliseconds


        val runnableCode = object: Runnable {
            override fun run() {
                scanLeDevice()
                println("*********Device scan postdelayed**********")
                mainHandler.postDelayed(this, delay.toLong())
            }
        }
        mainHandler.post(runnableCode)
// Stops scanning after 10 seconds.
        // Device scan callback.
    }
}