
package com.example.projectmobile_aima3tib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TutorialAdapter(
    private val titles: List<String>,
    private val descriptions: List<String>,
    private val images: List<Int>
) : RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder>() {

    class TutorialViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val image: ImageView =
            itemView.findViewById(R.id.imgTutorial)

        val title: TextView =
            itemView.findViewById(R.id.tvTutorialTitle)

        val description: TextView =
            itemView.findViewById(R.id.tvTutorialDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutorial, parent, false)

        return TutorialViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TutorialViewHolder,
        position: Int
    ) {
        holder.image.setImageResource(images[position])
        holder.title.text = titles[position]
        holder.description.text = descriptions[position]
    }

    override fun getItemCount(): Int = titles.size
}
