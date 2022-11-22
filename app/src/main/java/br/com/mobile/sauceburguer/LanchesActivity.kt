package br.com.mobile.sauceburguer

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.mobile.sauceburguer.databinding.ActivityLanchesBinding
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient


class LanchesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLanchesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nome_usuario = Prefs.getString("nomeUsuario")

        if (nome_usuario == "adm") {
            binding.deletarLanche.visibility = View.VISIBLE
        }

        var d = intent.extras?.getSerializable("lanches") as Lanches

        binding.addCarrinho.setOnClickListener {
            val c = Carrinho()
            c.nome = d.nome
            c.preco = d.preco

            Thread {
                CarrinhoService.addCarrinho(c)
                runOnUiThread {
                    finish()
                }
            }.start()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lanches", d.nome)
            NotificationUtil.create(1, intent, "Adicionado ao carrinho:",
                 "Lanche: ${d.nome}")
        }


        binding.deletarLanche.setOnClickListener {
            Thread {
                LanchesService.deleteLanche(d)
                runOnUiThread {
                    finish()
                }
            }.start()
        }

        Picasso.with(this).load(d.foto).into(binding.imageLanche)

        binding.nomeLanche.text = d.nome
        binding.precoLanche.text = d.preco
        binding.pesoCarne.text = "Tamanho: ${d.peso_carne}"

        supportActionBar?.title = "Informações sobre o produto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }

}