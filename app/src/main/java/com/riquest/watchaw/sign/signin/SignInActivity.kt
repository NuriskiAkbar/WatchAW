package com.riquest.watchaw.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.riquest.watchaw.home.HomeActivity
import com.riquest.watchaw.R
import com.riquest.watchaw.sign.signup.SignUpActivity
import com.riquest.watchaw.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iusername:String
    lateinit var ipassword:String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences  = Preferences(this)

        preferences.setValues("onboarding","1")
        if(preferences.getValues("status").equals("1")){
            finishAffinity()
            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }
        btn_home.setOnClickListener{
            iusername = et_username.text.toString()
            ipassword = et_password.text.toString()

            if(iusername.equals("")){
                et_username.error = "Silahkan input username Anda"
                et_username.requestFocus()
            }else if(ipassword.equals("")){
                et_password.error = "Silahkan input password Anda"
                et_password.requestFocus()
            }else{
                pushLogin(iusername, ipassword)
            }
        }

        btn_daftar.setOnClickListener{
            var goSignup = Intent (this@SignInActivity, SignUpActivity::class.java)
            startActivity(goSignup)
        }
    }

    private fun pushLogin(iusername: String, ipassword: String) {
        mDatabase.child(iusername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseerror: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseerror.message,
                        Toast.LENGTH_LONG).show()  //panjang munculnya teks
            }
            override fun onDataChange(datasnapshot: DataSnapshot) {
                var user = datasnapshot.getValue(User::class.java)
                if(user == null){
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan",
                            Toast.LENGTH_LONG).show()  //panjang munculnya teks
                }else{

                    if(user.password.equals(ipassword)){

                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("username", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("saldo", user.saldo.toString())
                        preferences.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda salah",
                                Toast.LENGTH_LONG).show()  //panjang munculnya teks
                    }

                }
            }

        })
    }
}
