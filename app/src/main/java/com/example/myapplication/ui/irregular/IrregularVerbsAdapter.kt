package com.example.myapplication.ui.irregular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.IrregularVerb
import com.example.myapplication.databinding.ItemIrregularVerbBinding
import com.example.myapplication.R

class IrregularVerbsAdapter :
    ListAdapter<IrregularVerb, IrregularVerbsAdapter.VerbViewHolder>(DiffCallback) {

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<IrregularVerb>() {
            override fun areItemsTheSame(oldItem: IrregularVerb, newItem: IrregularVerb): Boolean =
                oldItem.infinitive == newItem.infinitive

            override fun areContentsTheSame(oldItem: IrregularVerb, newItem: IrregularVerb): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbViewHolder {
        val binding = ItemIrregularVerbBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VerbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerbViewHolder, position: Int) {
        val verb = getItem(position)
        holder.bind(verb, position)
    }

    class VerbViewHolder(private val binding: ItemIrregularVerbBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(verb: IrregularVerb, position: Int) {
            binding.textInfinitive.text = verb.infinitive
            binding.textSimplePast.text = verb.simplePast
            binding.textPastParticiple.text = verb.pastParticiple
            binding.textTranslation.text = verb.translation

            // Diseño alegre y colorido
            val colors = listOf(
                R.color.verbCard1, R.color.verbCard2, R.color.verbCard3, R.color.verbCard4
            )
            val color = ContextCompat.getColor(
                binding.root.context, colors[position % colors.size]
            )
            binding.cardView.setCardBackgroundColor(color)
        }
    }
}