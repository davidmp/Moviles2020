package pe.edu.upeu.calculadoraupeu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),View.OnClickListener {
    internal var valorA=""
    internal var operador=' '
    internal var resultado:Float?=0f

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtResultado=view.findViewById(R.id.txtResultado) as EditText
        val btn7=view.findViewById<Button>(R.id.btn7)
        val btn8=view.findViewById<Button>(R.id.btn8)
        val btn9=view.findViewById<Button>(R.id.btn9)
        val btn4=view.findViewById<Button>(R.id.btn4)
        val btn5=view.findViewById<Button>(R.id.btn5)
        val btn6=view.findViewById<Button>(R.id.btn6)

        val btnSuma=view.findViewById<Button>(R.id.btnSuma)
        val btnIgual=view.findViewById<Button>(R.id.btnIgual)
        /*view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        btn9!!.setOnClickListener(this)
        btn8!!.setOnClickListener(this)
        btn7!!.setOnClickListener(this)
        btn6!!.setOnClickListener(this)
        btn5!!.setOnClickListener(this)
        btn4!!.setOnClickListener(this)
        btnSuma!!.setOnClickListener(this)
        btnIgual!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn9->{ txtResultado.setText(txtResultado.text.toString()+"9")}
            R.id.btn8->{ txtResultado.setText(txtResultado.text.toString()+"8")}
            R.id.btn7->{ txtResultado.setText(txtResultado.text.toString()+"7")}
            R.id.btn6->{ txtResultado.setText(txtResultado.text.toString()+"6")}
            R.id.btn5->{ txtResultado.setText(txtResultado.text.toString()+"5")}
            R.id.btn4->{ txtResultado.setText(txtResultado.text.toString()+"4")}


            R.id.btnSuma->{
                valorA=txtResultado.text.toString()
                operador='+'
                txtResultado.setText("")
            }


            R.id.btnIgual->{
                when(operador){
                    '+'->{resultado=valorA?.toFloat()+txtResultado.text.toString().toFloat()}
                }
                txtResultado.setText(resultado.toString())
            }


        }


    }

}