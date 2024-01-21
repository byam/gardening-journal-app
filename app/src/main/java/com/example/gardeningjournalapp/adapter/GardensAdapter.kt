package com.example.gardeningjournalapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningjournalapp.databinding.GardenLayoutBinding
import com.example.gardeningjournalapp.db.Plant

class GardensAdapter(private var plants: List<Plant>, private val onClick: (Plant) -> Unit): RecyclerView.Adapter<GardensAdapter.GardenViewHolder>() {
    class GardenViewHolder(val binding: GardenLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val binding = GardenLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GardenViewHolder(binding)
    }
    override fun getItemCount(): Int = plants.size
    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        holder.binding.plantName.text = plants[position].name

        // Set the click listener for the item view
        holder.itemView.setOnClickListener { onClick(plants[position]) }
    }
}
