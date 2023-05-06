package com.example.fixitbyoreo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.fixitbyoreo.databinding.ActivityCustomerHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CustomerHomeActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCustomerHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance("https://fixit-by-oreo-default-rtdb.asia-southeast1.firebasedatabase.app").getReference()

        binding = ActivityCustomerHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarCustomerHome.toolbar)



        binding.appBarCustomerHome.fab.setOnClickListener { view ->
            showDialog()

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_customer_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottompopup)

        val titleEditText = dialog.findViewById<Spinner>(R.id.popup_title_spinner)
        val descriptionEditText = dialog.findViewById<EditText>(R.id.popup_description_input)
        val submitButton = dialog.findViewById<Button>(R.id.form_submit_button)

        submitButton.setOnClickListener {
            val title = titleEditText.selectedItem.toString()
            val name = "Shamry Shiraz"
            val description = descriptionEditText.text.toString().trim()
            val location = "Kandy"

            if (title.isBlank() || description.isBlank()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                descriptionEditText.error = "Description is required"
                return@setOnClickListener
            }

            val user1 = Client(title,name,description,location)
            val newRef = database.child("job_request").push() // generate new unique key
            newRef.setValue(user1) // add new item as child with unique key under "Users" parent node
            dialog.dismiss()
            Toast.makeText(this@CustomerHomeActivity, "Looking for agent", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.customer_home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_customer_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}