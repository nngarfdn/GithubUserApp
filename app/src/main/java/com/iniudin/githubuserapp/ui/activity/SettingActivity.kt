package com.iniudin.githubuserapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.iniudin.githubuserapp.R
import com.iniudin.githubuserapp.utils.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        alarmReceiver = AlarmReceiver()

        change_language.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        btn_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val repeatTime = "09:00"
                val repeatMessage = "Yuk buka Github User App"
                alarmReceiver.setRepeatingAlarm(
                    this, AlarmReceiver.TYPE_REPEATING,
                    repeatTime, repeatMessage
                )
            } else {
                alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING)
            }
        }

    }
}