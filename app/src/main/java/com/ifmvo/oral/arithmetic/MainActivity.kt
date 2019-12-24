package com.ifmvo.oral.arithmetic

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = Navigation.findNavController(this, R.id.fragment)

//        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        when {
            controller.currentDestination?.id == R.id.qustionFragment -> {
                val dialog = AlertDialog.Builder(this)
                        .setTitle("你确定要退出？")
                        .setPositiveButton("确定") { _, _ ->
                            controller.navigateUp()
                        }
                        .setNegativeButton("取消") { _, _ ->

                        }.create()
                dialog.show()
            }
            controller.currentDestination?.id == R.id.titleFragment -> return super.onSupportNavigateUp()
            else -> controller.navigate(R.id.titleFragment)
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}
