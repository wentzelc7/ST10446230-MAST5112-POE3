package com.example.mast_poe3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

class AddMenuItemActivity : AppCompatActivity() {

    private val menuItems = mutableListOf<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu_item)

        // Set up the course spinner
        val courseSpinner = findViewById<Spinner>(R.id.courseSpinner)
        val courseAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Appetizer", "Main Course", "Dessert"))
        courseSpinner.adapter = courseAdapter

        // Set up the add button
        val addButton = findViewById<Button>(R.id.addMenuButton)
        addButton.setOnClickListener {
            val nameEditText = findViewById<EditText>(R.id.itemName)
            val priceEditText = findViewById<EditText>(R.id.itemPrice)
            val course = courseSpinner.selectedItem.toString()

            val name = nameEditText.text.toString()
            val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (name.isNotEmpty() && price > 0) {
                menuItems.add(MenuItem(name, course, price))
                nameEditText.text.clear()
                priceEditText.text.clear()
            }
        }

        // Save the menu items and return to the home screen
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("menuItems", ArrayList(menuItems)) // Send the updated menu list
            startActivity(intent)
        }

        // Button to go back to MainActivity
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Close the current activity and return to the previous one
        }
    }

    // Data class for menu items
    data class MenuItem(val name: String, val course: String, val price: Double)
}