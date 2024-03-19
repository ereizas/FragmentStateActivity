package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"
const val CURR_NUM_KEY = "currNum"
class DiceFragment : Fragment() {
    private var sides: Int? = null
    private var currNum : String = "0"
    private lateinit var numberDisplayTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
    }

    private fun changeNum()
    {
        currNum = (Random.nextInt(sides!!) + 1).toString()
        numberDisplayTextView.text = currNum
    }

    private fun changeNum(_currNum:String){
        numberDisplayTextView.text = _currNum
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {

            numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)
            findViewById<Button>(R.id.rollButton).setOnClickListener {
                changeNum()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.run{
            currNum=getString(CURR_NUM_KEY,"0")
        }
        if(currNum=="0")
        {
            changeNum()
        }
        else
        {
            changeNum(currNum)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURR_NUM_KEY,currNum)
        super.onSaveInstanceState(outState)
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}