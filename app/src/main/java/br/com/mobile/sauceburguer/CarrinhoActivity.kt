package br.com.mobile.sauceburguer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.sauceburguer.databinding.ActivityCarrinhoBinding
import br.com.mobile.sauceburguer.databinding.ActivityLanchesBinding
import br.com.mobile.sauceburguer.databinding.AdapterCarrinhoBinding
import com.google.android.material.navigation.NavigationView

public class CarrinhoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarrinhoBinding.inflate(layoutInflater)
    }

    private val adapterbinding by lazy {
        AdapterCarrinhoBinding.inflate(layoutInflater)
    }

    private var carrinho = listOf<Carrinho>()
    private var cozinha =  mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarInclude.toolbar)
        supportActionBar?.title = "Carrinho"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerCarrinho?.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrinho?.setHasFixedSize(true)

        binding.enviarPedido.setOnClickListener {
            if (binding.mesaNumero.text.toString() == "") {
                if (adapterbinding.comboCheck.isActivated) {
                    Toast.makeText(this, "Funfou", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(this, "Insira o número da mesa.", Toast.LENGTH_SHORT).show()
            } else {
                cozinha.add("Mesa: " + binding.mesaNumero.text.toString())
                for (item in carrinho) cozinha.add(item.nome)

                Thread {
                    CarrinhoService.addCozinha(cozinha)
                    runOnUiThread {
                        finish()
                    }
                }.start()

            }
        }


    }

//    fun onClickCarrinho(lanches: Carrinho) {
//
//        var it = Intent(this, LanchesActivity::class.java)
//        val params = this.intent.extras
//        val nome_usuario = params?.getString("nome_usuario")
//
//        it.putExtra("lanches", lanches)
//        it.putExtra("nomeUsuario", nome_usuario)
//
//        startActivity(it)
//
//    }

    override fun onResume() {
        super.onResume()
        this.taskCarrinho()
    }


    private fun taskCarrinho() {
        Thread {
            carrinho = CarrinhoService.listaCarrinho()
            runOnUiThread {
                binding.recyclerCarrinho?.adapter =
                    CarrinhoAdapter(carrinho) { }
                // Notificação
                // val intent = Intent(this, MainActivity::class.java)
                // intent.putExtra("lanches", lanches[0])
                // NotificationUtil.create(1, intent, "Lanche Sauce",
                //     "Lanche: ${lanches[0].nome}")
            }
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }
}