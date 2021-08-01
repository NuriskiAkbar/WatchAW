package com.riquest.watchaw.sign.signup


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.riquest.watchaw.R
import com.riquest.watchaw.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference
    private lateinit var mDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener{

            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if(sUsername.equals("")){
                et_username.error = "Silahkan input username anda"
            } else if(sPassword.equals("")){
                et_password.error = "Silahkan input password anda"
            } else if(sNama.equals("")){
                et_nama.error = "Silahkan input nama anda"
            } else if(sEmail.equals("")){
                et_email.error = "Silahkan input email anda"
            } else {
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }
        }

    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if(sUsername != null){
            CheckingUsername(sUsername , user)
        }
    }

    private fun CheckingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goSignUpPhotoscreen = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhotoscreen)
                }else{
                    Toast.makeText(this@SignUpActivity,"User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity,""+databaseError.message, Toast.LENGTH_LONG).show()
        }
    })
    }
}
