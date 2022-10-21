package br.com.mobile.sauceburguer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mobile.sauceburguer.databinding.ActivityCadastroLancheBinding

class CadastroLanche : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroLancheBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cadastrarLanche.setOnClickListener {
            val d = Lanches()
            d.nome = binding.inserirNome.text.toString()
            d.preco = binding.inserirPreco.text.toString()
            d.foto = binding.inserirUrlFoto.text.toString()
            d.peso_carne = binding.inserirTamanhoProduto.text.toString()

            Thread {
                LanchesService.saveLanche(d)
                runOnUiThread {
                    finish()
                }
            }.start()
        }

    }
}