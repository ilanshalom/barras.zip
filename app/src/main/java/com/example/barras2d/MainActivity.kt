package com.example.barras2d

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.barras2d.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var star1: Int = 0
    var star2: Int = 0
    var star3: Int = 0
    var star4: Int = 0
    var star5: Int = 0
    var sum: Int = 0
    var avg: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val btnsobre = binding.btnsobre
        btnsobre.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
    }

    //este método será chamado ao clicar no botão 'Gráfico barras', por causa do
    //android:onClick="newGraphic" definido no arquivo activity_main.xml:
    fun newGraphic(v: View) {
        calculate()
        setValues()
        //a seguir, com o método setParams da classe BarGraphic,
        //enviamos os valores relacionados com as 5 barras:
        (findViewById(R.id.canvas) as BarGraphic).setParams(
            arrayOf(
                star1,
                star2,
                star3,
                star4,
                star5
            )
        )
    }

    private fun calculate() {
        star1 = getNumber((findViewById(R.id.star1) as TextView).text.toString())
        star2 = getNumber((findViewById(R.id.star2) as TextView).text.toString())
        star3 = getNumber((findViewById(R.id.star3) as TextView).text.toString())
        star4 = getNumber((findViewById(R.id.star4) as TextView).text.toString())
        star5 = getNumber((findViewById(R.id.star5) as TextView).text.toString())
        sum = star1 + star2 + star3 + star4 + star5
        avg = (star1 + star2 * 2 + star3 * 3 + star4 * 4 + star5 * 5) / (sum * 1f)
    }

    private fun setValues() {
        (findViewById(R.id.total) as TextView).text = sum.toString()
        (findViewById(R.id.avg) as TextView).text = String.format("%.1f", avg)
    }

    private fun getNumber(text: String): Int {
        return if (text == null || text.isEmpty()) 0 else text.toInt()
    }
}
