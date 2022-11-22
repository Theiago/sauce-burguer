package br.com.mobile.sauceburguer

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mobile.sauceburguer.databinding.ActivityCadastroLancheBinding


class CadastroLanche : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroLancheBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        supportActionBar?.title = "Cadastrar novo lanche"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.cadastrarLanche.setOnClickListener {
            val d = Lanches()
            d.nome = binding.inserirNome.text.toString()
            d.preco = binding.inserirPreco.text.toString()
            d.foto = binding.inserirUrlFoto.text.toString()
            d.peso_carne = binding.inserirTamanhoProduto.text.toString()

            if (d.nome == "" || d.preco == "" || d.foto == "" || d.peso_carne == "") {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_LONG).show()
            } else {
                Thread {
                    LanchesService.saveLanche(d)
                    runOnUiThread {
                        finish()
                    }
                }.start()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }

}