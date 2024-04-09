package kholmurodov.idmoil

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast as WToast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import java.text.DecimalFormat;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import android.graphics.Color;
import android.graphics.drawable.Drawable


class MainActivity : AppCompatActivity() {
    private var textViewFirstOperand: TextView? = null
    private var textViewSecondOperand: TextView? = null
    private var textViewOperator: TextView? = null
    private var editTextResult: EditText? = null
    private lateinit var buttonStart: Button
    private lateinit var textViewAllExample: TextView
    private lateinit var buttonRight: Button
    private lateinit var buttonWrong: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewFirstOperand = findViewById<TextView>(R.id.txtFirstOperand)
        textViewSecondOperand = findViewById<TextView>(R.id.txtTwoOperand)
        textViewOperator = findViewById<TextView>(R.id.txtOperation)
        editTextResult = findViewById<EditText>(R.id.editValue)
        buttonStart = findViewById<Button>(R.id.btnStart)
        buttonStart.setOnClickListener(View.OnClickListener { generateMathProblem() })
        textViewAllExample = findViewById(R.id.txtAllExample)
        buttonRight = findViewById(R.id.btnRight)
        buttonWrong = findViewById(R.id.btnWrong)
        buttonRight.setOnClickListener { onRightButtonClick() }
        buttonWrong.setOnClickListener { onWrongButtonClick() }
    }
    private var correctAnswer: Double = 0.0 // Переменная для хранения правильного ответа
    private fun generateMathProblem() {
        val random = Random()
        val firstOperand = random.nextInt(90) + 10 // Генерация числа от 10 до 99
        val secondOperand = random.nextInt(90) + 10 // Генерация числа от 10 до 99
        val operator = random.nextInt(4) // Генерация числа от 0 до 3
        textViewFirstOperand!!.text = firstOperand.toString()
        textViewSecondOperand!!.text = secondOperand.toString()
        var result = 0.0
        when (operator) {
            0 -> {
                textViewOperator!!.text = "*"
                result = (firstOperand * secondOperand).toDouble()
            }
            1 -> {
                textViewOperator!!.text = "/"
                result = if (secondOperand != 0) {
                    firstOperand.toDouble() / secondOperand
                } else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return // Выход из метода, чтобы избежать деления на ноль
                }
            }
            2 -> {
                textViewOperator!!.text = "-"
                result = (firstOperand - secondOperand).toDouble()
            }
            3 -> {
                textViewOperator!!.text = "+"
                result = (firstOperand + secondOperand).toDouble()
            }

        }
        val df = DecimalFormat("#.##")
        // Генерация случайного числа для определения, будет ли ответ правильным или нет
        val chance = random.nextInt(2)
        if (chance == 0) {
            // С вероятностью 50% ответ будет правильным
            editTextResult!!.setText(df.format(result))
            correctAnswer = result // Сохраняем правильный ответ
        } else {
            // С вероятностью 50% ответ будет случайным числом в заданном диапазоне
            val incorrectAnswer = random.nextInt(90) + 10 // Генерация случайного числа от 10 до 99
            editTextResult!!.setText(incorrectAnswer.toString())
            correctAnswer = result // Сохраняем правильный ответ, но не показываем его пользователю
        }
    }
}
