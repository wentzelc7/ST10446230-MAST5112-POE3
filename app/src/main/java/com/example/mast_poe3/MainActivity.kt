package com.example.mast_poe3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val menuItems = mutableListOf<MenuItem>() // Store menu items here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to navigate to the "AddMenuItemActivity"
        val addMenuButton = findViewById<Button>(R.id.addMenuButton)
        addMenuButton.setOnClickListener {
            val intent = Intent(this, AddMenuItemActivity::class.java)
            startActivity(intent)
        }

        // Button to navigate to the "FilterActivity"
        val filterButton = findViewById<Button>(R.id.filterButton)
        filterButton.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }

        // Display menu items on the home screen
        displayMenuItems()
    }

    // Function to display all menu items
    private fun displayMenuItems() {
        val menuTextView = findViewById<TextView>(R.id.menuTextView)
        val menuList = StringBuilder()

        for (item in menuItems) {
            menuList.append("${item.name} - ${item.course} - $${item.price}\n")
        }

        menuTextView.text = menuList.toString()
    }

    // Function to update the list of menu items (called from AddMenuItemActivity)
    fun updateMenuItems(items: List<MenuItem>) {
        menuItems.clear()
        menuItems.addAll(items)
        displayMenuItems()
    }

    // Data class for menu items
    data class MenuItem(val name: String, val course: String, val price: Double)
}