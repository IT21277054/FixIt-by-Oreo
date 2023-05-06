package com.example.fixit;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.fixit.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginRedirectText.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.signupBtn.setOnClickListener{
            validateData()
        }

        var btnCancel = findViewById<ImageButton>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            var intent = Intent(this, LogInSignUp::class.java)
            startActivity(intent)
            finish()
        }

    }

    private var name:String = ""
    private var email:String = ""
    private var password:String = ""
    private var age:String = ""
    private var number:String = ""
    private var type:String = ""
    private var gender:String = ""

    private fun validateData() {

        name = binding.signupName.text.toString()
        email = binding.signupEmail.text.toString()
        age = binding.signupAge.text.toString()
        number = binding.signupNumber.text.toString()
        type = binding.signupType.selectedItem.toString()
        gender = binding.signupGender.selectedItem.toString()
        password = binding.signupPassword.text.toString()
        val reEnter = binding.signupRePassword.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter your Email or Invalid Email Pattern", Toast.LENGTH_SHORT).show()
        } else if (age.isEmpty()) {
            Toast.makeText(this, "Enter your Age", Toast.LENGTH_SHORT).show()
        } else if (number.isEmpty() && isValidPhoneNumber(number)) {
            Toast.makeText(this, "Enter your Number or Invalid Number", Toast.LENGTH_SHORT).show()
        }else if (type.isEmpty()) {
            Toast.makeText(this, "Enter your Type (Worker / Customer)", Toast.LENGTH_SHORT).show()
        }else if (gender.isEmpty()) {
            Toast.makeText(this, "Enter your Gender (Male / Female)", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show()
        } else if (reEnter.isEmpty()) {
            Toast.makeText(this, "Enter your Confirm Password", Toast.LENGTH_SHORT).show()
        } else if (password != reEnter) {
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
        }else {
            createUserAccountFirebaseA()
            //Toast.makeText(this, "Your Account has been Created", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

        private fun createUserAccountFirebaseA() {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            createUserAccountRealtimeDb()
                        }
                .addOnFailureListener{e->
                    Toast.makeText(this,"Failed creating account due to ${e.message}", Toast.LENGTH_SHORT).show()
                }

            }

     fun isValidPhoneNumber(number: String): Boolean {
        val regex = Regex("^\\+(?:[0-9] ?){6,14}[0-9]\$")
        return regex.matches(number)
    }

    private fun createUserAccountRealtimeDb() {

        val uid = firebaseAuth.uid

        val hashMap: HashMap<String,Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["age"] = age
        hashMap["number"] = number
        hashMap["userType"] = type
        hashMap["gender"] = gender

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this,"$name, Your Account has been Created", Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener{ e->
                Toast.makeText(this,"Failed creating account due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

}

