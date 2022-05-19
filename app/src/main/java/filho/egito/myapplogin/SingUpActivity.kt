package filho.egito.myapplogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import filho.egito.myapplogin.databinding.ActivitySingUpBinding

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySingUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val senha = binding.passET.text.toString()
            val confirma = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty() && confirma.isNotEmpty()) {
                if (senha == confirma) {

                    firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {

                        if (it.isSuccessful) {
                            val intent = Intent(this, SingInActivity::class.java)
                            startActivity(intent)
                            } else {
                            Toast.makeText(this, "Usuário não cadastrado", Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Campos vazios não são permitidos !!!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

