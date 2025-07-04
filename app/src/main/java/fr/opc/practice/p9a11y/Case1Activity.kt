package fr.opc.practice.p9a11y

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var quantity = 0

        binding.quantityText.text = "$quantity"
        binding.quantityText.contentDescription = getString(R.string.produits_dans_le_panier, quantity)

        binding.addButton.setOnClickListener {
            quantity++
            binding.addButton.announceForAccessibility(getString(R.string.un_produit_ajout_le_panier_contient_maintenant_produit, quantity))
            binding.quantityText.text = "$quantity"
            binding.quantityText.contentDescription = getString(R.string.produits_dans_le_panier, quantity)
        }

        binding.removeButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.removeButton.announceForAccessibility(getString(R.string.un_produit_supprim_le_panier_contient_maintenant_produit, quantity))
                binding.quantityText.text = "$quantity"
                binding.quantityText.contentDescription = getString(R.string.produits_dans_le_panier, quantity)
            } else {
                Toast.makeText(this, getString(R.string.impossible_d_avoir_une_quantit_n_gative), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
