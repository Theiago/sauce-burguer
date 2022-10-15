package br.com.mobile.sauceburguer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.mobile.sauceburguer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imagemLogin.setImageResource(R.drawable.sauce)
        binding.textoLogin.text = "Usuário: "
        binding.textoSenha.text = "Senha: "

        binding.botaoLogin.setOnClickListener {
        val intent = Intent(this, HomeActivity::class.java)
        val nome_usuario = binding.campoEmail.text.toString()
        val senha = binding.campoSenha.text.toString()

        intent.putExtra("nome_usuario", nome_usuario)
        if (senha == "impacta" && nome_usuario == "aluno") {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuário e/ou senhas incorretos.", Toast.LENGTH_SHORT).show()
        }
        }
    }
}