package br.com.mobile.sauceburguer

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.mobile.sauceburguer.databinding.ActivityLanchesBinding
import com.squareup.picasso.Picasso


class LanchesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLanchesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var d = intent.extras?.getSerializable("lanches") as Lanches

        Picasso.with(this).load(d.foto).into(binding.imageLanche)

        binding.nomeLanche.text = d.nome
        binding.precoLanche.text = d.preco
        binding.pesoCarne.text = "Tamanho: ${d.peso_carne}"

        supportActionBar?.title = "Smash Cheddar"
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