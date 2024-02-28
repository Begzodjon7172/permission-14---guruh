package uz.bnabiyev.permission14

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import uz.bnabiyev.permission14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Ruxsat bor yo'qligini tekshiradi
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.tv.text = "Ruxsat bor"
        } else {
            binding.tv.text = "Ruxsat yo'q"
            // Dialog chiqaradi
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        }

        binding.btnCall.setOnClickListener {
            val inetnt = Intent(Intent.ACTION_CALL)
            inetnt.setData(Uri.parse("tel:933617472"))
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Ruxsat berildi", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Ruxsat berilmadi", Toast.LENGTH_SHORT).show()
            }
        }
    }

}