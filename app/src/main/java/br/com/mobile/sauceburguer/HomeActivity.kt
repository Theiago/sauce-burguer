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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.sauceburguer.databinding.HomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding by lazy {
        HomeBinding.inflate(layoutInflater)
    }

    private var lanches = listOf<Lanches>()

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
        val carrinho = Intent(this, CarrinhoActivity::class.java)
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
        } else if (id == R.id.action_sair) {
            finish()
        }
        else if (id == R.id.action_carrinho) {
            startActivity(carrinho)
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val params = this.intent.extras
        val nome_usuario = params?.getString("nome_usuario")
        Toast.makeText(this, "Bem vindo $nome_usuario", Toast.LENGTH_LONG).show()

        setSupportActionBar(binding.toolbarInclude.toolbar)

        supportActionBar?.title = "Monte seu pedido"

        configuraMenuLateral()

        binding.recyclerLanches?.layoutManager = LinearLayoutManager(this)
        binding.recyclerLanches?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        this.taskLanches()
    }

    private fun taskLanches() {
        Thread {
            lanches = LanchesService.getLanches()
            runOnUiThread {
                binding.recyclerLanches?.adapter =
                    LanchesAdapter(lanches) { onClickDisciplina(it) }
            }
        }.start()
    }

    fun onClickDisciplina(lanches: Lanches) {
        Toast.makeText(this, "Funcionou", Toast.LENGTH_LONG).show()

      //  var it = Intent(this, DisciplinaActivity::class.java)
      //  it.putExtra("disciplina", disciplina)

      //  startActivity(it)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_lanches -> {
                Toast.makeText(this, "Lanches", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sobre -> {
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                Toast.makeText(this, "Localização", Toast.LENGTH_SHORT).show()
            }
        }
        binding.layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    private fun configuraMenuLateral() {
        var toggle = ActionBarDrawerToggle(
            this,
            binding.layoutMenuLateral,
            binding.toolbarInclude.toolbar,
            R.string.abrir,
            R.string.fechar
        )
        binding.layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        binding.menuLateral.setNavigationItemSelectedListener(this)
    }

}