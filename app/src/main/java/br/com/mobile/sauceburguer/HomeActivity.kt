package br.com.mobile.sauceburguer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.mobile.sauceburguer.databinding.HomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        HomeBinding.inflate(layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        when (id) {
        R.id.action_buscar -> Toast.makeText(this, "Buscar", Toast.LENGTH_LONG).show()
            R.id.action_atualizar -> Toast.makeText(this, "Atualizar", Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val params = this.intent.extras
        val nome_usuario = params?.getString("nome_usuario")

    }
}