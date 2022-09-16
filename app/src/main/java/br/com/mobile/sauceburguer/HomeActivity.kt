package br.com.mobile.sauceburguer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.mobile.sauceburguer.databinding.HomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        HomeBinding.inflate(layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                val toast = Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT)
                toast.show()
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        val id = item.itemId
        if (id == R.id.action_atualizar) {
            binding.progressAtualizar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    binding.progressAtualizar.visibility = View.GONE
                },
                10000
            )
        } else if (id == R.id.action_sair) { startActivity(intent) }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val params = this.intent.extras
        val nome_usuario = params?.getString("nome_usuario")

    }
}