package app.ijp.colorpickerdialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView



class HistoryTabFragment : Fragment() {

    private var colorHistory: (() -> List<Int>?)? = null
    private var  onColorChangedListener: OnColorChangedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history_tab, container, false)
        val rvColorHistory: RecyclerView = view.findViewById(R.id.rv_color_history)
        rvColorHistory.layoutManager = GridLayoutManager(requireContext(),4)
        Log.d("HistoryFab","$colorHistory")
        colorHistory?.let {
            it()?.let {
                val adapter = HistoryRvAdapter(it){color->
                    onColorChangedListener?.colorChanged(color)
                }
                rvColorHistory.adapter = adapter
            }

        }

        return view
    }

    companion object {
        fun newInstance(
            colorHistory: (() -> List<Int>?)?,
            onColorChangedListener: OnColorChangedListener?
        ): HistoryTabFragment {
            val h = HistoryTabFragment()
            h.colorHistory = colorHistory
            h.onColorChangedListener = onColorChangedListener
            return h
        }
    }
}

class HistoryRvAdapter(private val colorList: List<Int>, val onColorItemSelected:(Int)->Unit)
    :RecyclerView.Adapter<HistoryRvAdapter.ColorItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_history_single_color_item_layout,parent,false)
        return ColorItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorItemViewHolder, position: Int) {
        val currColor = colorList[position]
        Log.d("HistoryFab","$currColor")
        holder.colorCard.setBackgroundColor(currColor)
        holder.colorCard.setOnClickListener {
            onColorItemSelected(currColor)
        }
    }

    override fun getItemCount(): Int = colorList.size

    class ColorItemViewHolder(view: View):RecyclerView.ViewHolder(view) {
            val colorCard: LinearLayout = view.findViewById(R.id.ll_color_item_card)
        }
    }