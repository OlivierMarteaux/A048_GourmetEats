package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false
        setFavouriteButtonIcon(isFavourite)

        fun toggleFavourite() {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
            if (isFavourite) {
                binding.recipeCard.announceForAccessibility(getString(R.string.recette_ajout_e_aux_favoris))
            } else {binding.recipeCard.announceForAccessibility(getString(R.string.recette_supprim_e_des_favoris))}
        }

        binding.favouriteButton.setOnClickListener {
            toggleFavourite()
        }

        ViewCompat.addAccessibilityAction(
            // View to add accessibility action
            binding.recipeCard,
            // Label surfaced to user by an accessibility service
            getText(R.string.cd_ajouter_aux_favoris),
            ({
                _, _ ->
                // Action to perform when the accessibility action is triggered
                toggleFavourite()
                true
            })
        )

        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        ViewCompat.addAccessibilityAction(
            // View to add accessibility action
            binding.recipeCard,
            // Label surfaced to user by an accessibility service
            getText(R.string.ajouter_la_recette_au_panier),
        ){
            _, _ ->
            // Same method executed when swiping on itemView
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
            true
        }

        binding.recipeCard.setOnClickListener {
            // TODO navigate to recipe screen
        }
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
        }
    }
}
