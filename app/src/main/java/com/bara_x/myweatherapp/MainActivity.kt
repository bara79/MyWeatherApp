package com.bara_x.myweatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {


    private var userField: EditText? = null
    private var button: Button? = null
    private var result_info: TextView? = null
    private var ico_weater: ImageView? = null



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userField = findViewById(R.id.user_field)
        button = findViewById(R.id.button)
        result_info = findViewById(R.id.result_info)
        ico_weater = findViewById(R.id.ico_weater)


        button?.setOnClickListener {
            if (userField?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Введите город", Toast.LENGTH_LONG).show()
            else {
                val city: String = userField!!.text.toString()
                val key: String = "c73a6e09051a4027d56ba0f13784aee2"
                val url: String =
                    "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"

                doAsync {
                    val apiResponse = URL(url).readText()
                    Log.d("INFO", apiResponse)

                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")

                    val iconWeater = JSONObject(apiResponse).getJSONArray("weather")
                    val icon = weather.getString(Int)


                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")


                    result_info?.text = "Температура: $temp\n$desc"
                    ico_weater?.setImageResource(icon)
                }
            }
        }
    }
}

