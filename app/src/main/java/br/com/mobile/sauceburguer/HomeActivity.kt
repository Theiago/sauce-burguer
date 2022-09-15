package br.com.mobile.sauceburguer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mobile.sauceburguer.databinding.HomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        HomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}