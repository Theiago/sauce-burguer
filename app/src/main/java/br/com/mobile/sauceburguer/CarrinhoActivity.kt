package br.com.mobile.sauceburguer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.mobile.sauceburguer.databinding.ActivityCarrinhoBinding

class CarrinhoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarrinhoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarInclude.toolbar)
        supportActionBar?.title = "Carrinho"
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