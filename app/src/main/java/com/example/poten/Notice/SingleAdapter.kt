import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.R
import com.example.poten.databinding.ItemCategoryBinding

class SingleAdapter(
    private val context: Context
): RecyclerView.Adapter<SingleAdapter.SelectSingleItemViewHolder>() {

    var list= mutableListOf<String>()
    private var clickState: BooleanArray = BooleanArray(7, {false})

    private lateinit var binding: ItemCategoryBinding
    private var onItemClickListener: OnItemClickListener? = null

    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onClick(v: View, position: Int){
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class SelectSingleItemViewHolder(
        private val binding: ItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: View) {
            binding.tvItemName.text = list[adapterPosition].toString()

            if (selectedPosition == adapterPosition) {
                clickState[adapterPosition] = true
                binding.setChecked()
            } else {
                clickState[adapterPosition] = false
                binding.setUnchecked()
            }

            if (onItemClickListener != null) {
                binding.tvItemName.setOnClickListener {
                    if (selectedPosition != adapterPosition) {
                        binding.setChecked()
                        notifyItemChanged(selectedPosition)
                        selectedPosition = adapterPosition
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSingleItemViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return SelectSingleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectSingleItemViewHolder, position: Int) {
        holder.bind(holder.itemView)
    }

    override fun getItemCount(): Int = list.size

    private fun ItemCategoryBinding.setChecked() {
        tvItemName.setBackgroundResource(R.drawable.round_click)
        tvItemName.setTextColor(Color.WHITE)
    }

    private fun ItemCategoryBinding.setUnchecked() {
        tvItemName.setBackgroundResource(R.drawable.round)
        tvItemName.setTextColor(Color.parseColor("#525252"))
    }

}
