package find.blutooth.app4practice;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SetNScanBluetooth extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    public final int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_nscan_bluetooth);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(SetNScanBluetooth.this, "Bluetooth is not supported in this device", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SetNScanBluetooth.this, "Bluetooth is supported in this device", Toast.LENGTH_SHORT).show();
            if(!bluetoothAdapter.isEnabled()){
                Intent btintent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(btintent, 0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Toast.makeText(SetNScanBluetooth.this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
            askforvisibility();
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(SetNScanBluetooth.this, "Bluetooth is Still OFF either due to error or user denied permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void askforvisibility() {
        Intent visible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(visible, 0);
    }
}
