package uz.bnabiyev.permission14

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.florent37.runtimepermission.RuntimePermission.askPermission
import com.github.florent37.runtimepermission.kotlin.askPermission
import uz.bnabiyev.permission14.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        askPermission(Manifest.permission.CALL_PHONE){
            //all permissions already granted or just granted
            Toast.makeText(this, "Ruxsat berildi", Toast.LENGTH_SHORT).show()
        }.onDeclined { e ->
            if (e.hasDenied()) {

                Toast.makeText(this, "Ruxsat berilmadi", Toast.LENGTH_SHORT).show()

                AlertDialog.Builder(this)
                    .setMessage("Please accept our permissions")
                    .setPositiveButton("yes") { dialog, which ->
                        e.askAgain();
                    } //ask again
                    .setNegativeButton("no") { dialog, which ->
                        dialog.dismiss();
                    }
                    .show();
            }

            if(e.hasForeverDenied()) {
                //the list of forever denied permissions, user has check 'never ask again'

                // you need to open setting manually if you really need it
                e.goToSettings();
            }
        }
    }
}