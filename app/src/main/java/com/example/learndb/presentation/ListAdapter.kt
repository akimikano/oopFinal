package com.example.learndb.fragment.recyclerview
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.example.learndb.R
import com.example.learndb.data.Person


class PeopleListAdapter(private val onClickDelete: (person: Person) -> Unit): RecyclerView.Adapter<PeopleListAdapter.ViewHolder>() {

    val mDiffer: AsyncListDiffer<Person> = AsyncListDiffer(this, DIFF_CALLBACK)

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val username = view.findViewById<TextView>(R.id.cardName)
        lateinit var person: Person

        fun onBind(person: Person) {
            this.person = person
            username.text = "${person.username} (${person.prof})"
        }

        init {
            val deleteBtn = view.findViewById<ImageButton>(R.id.deleteBtn)
            deleteBtn.setOnClickListener {
                onClickDelete(person)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mDiffer.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun submitList(list: List<Person>) {
        mDiffer.submitList(list)
        notifyDataSetChanged()
    }

    object DIFF_CALLBACK: DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            Log.e("ITEMS THE SAME CHECK", "CHECK")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            Log.e("CONTENTS THE SAME CHECK", "CHECK")
            return oldItem == newItem
        }
    }
}