package com.example.mast_poe3

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FilterActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Spring Rolls", "Appetizer", 5.99),
        MenuItem("Caesar Salad", "Appetizer", 6.99),
        MenuItem("Steak", "Main Course", 18.99),
        MenuItem("Pasta", "Main Course", 15.99),
        MenuItem("Cheesecake", "Dessert", 7.99)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val courseSpinner = findViewById<Spinner>(R.id.courseSpinner)
        val courseAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("All", "Appetizer", "Main Course", "Dessert"))
        courseSpinner.adapter = courseAdapter

        val filterButton = findViewById<Button>(R.id.filterButton)
        val filteredMenuTextView = findViewById<TextView>(R.id.filteredMenuTextView)

        filterButton.setOnClickListener {
            val selectedCourse = courseSpinner.selectedItem.toString()
            val filteredItems = if (selectedCourse == "All") {
                menuItems
            } else {
                menuItems.filter { it.course == selectedCourse }
            }

            val menuText = StringBuilder()
            for (item in filteredItems) {
                menuText.append("${item.name} - ${item.course} - $${item.price}\n")
            }
            filteredMenuTextView.text = menuText.toString()
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