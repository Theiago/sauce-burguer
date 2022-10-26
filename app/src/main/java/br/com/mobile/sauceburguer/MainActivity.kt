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

        val nomeUsuario = Prefs.getString("nomeUsuario")
        val senhaUsuario = Prefs.getString("senhaUsuario")
        val checkLogin = Prefs.getBoolean("checkLogin")

        binding.imagemLogin.setImageResource(R.drawable.sauce)
        binding.textoLogin.text = "Usuário: "
        binding.textoSenha.text = "Senha: "

        binding.campoEmail.setText(nomeUsuario)
        binding.campoSenha.setText(senhaUsuario)
        binding.checkLogin.isChecked = checkLogin

        binding.botaoLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val nome_usuario = binding.campoEmail.text.toString()
            val senha = binding.campoSenha.text.toString()

            intent.putExtra("nome_usuario", nome_usuario)

            val checkLogin = binding.checkLogin.isChecked
            if (checkLogin) {
                Prefs.setString("nomeUsuario", nome_usuario)
                Prefs.setString("senhaUsuario", senha)
            } else {
                Prefs.setString("nomeUsuario", "")
                Prefs.setString("senhaUsuario", "")
            }
                Prefs.setBoolean("checkLogin", checkLogin)

            if (nome_usuario == "adm" || nome_usuario == "aluno" && senha == "impacta" ) {
                Prefs.setString("nomeUsuario", nome_usuario)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuário e/ou senhas incorretos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}