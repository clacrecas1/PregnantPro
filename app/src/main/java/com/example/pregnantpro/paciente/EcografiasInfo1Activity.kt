package com.example.pregnantpro.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.example.pregnantpro.R

class EcografiasInfo1Activity : AppCompatActivity() {

    private lateinit var tvInfo: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecografias_info1)

        // Inicializar las vistas después de setContentView
        tvInfo = findViewById(R.id.tvInfo)
        imageView = findViewById(R.id.imageView)

        val infoText = """
            <b>Las ecografías son imágenes médicas</b> que utilizan ondas sonoras de alta frecuencia para crear imágenes en tiempo real del interior del cuerpo. Durante el embarazo, las ecografías se realizan para monitorear el desarrollo del feto. Aquí hay algunos datos interesantes sobre las ecografías en el embarazo:

            <ul>
                <li><b>Las ecografías son seguras y no invasivas.</b></li>
                <li><b>Proporcionan información sobre el tamaño y la posición del feto.</b></li>
                <li><b>Se pueden realizar ecografías en diferentes etapas del embarazo.</b></li>
                <li><b>Ayudan a confirmar el embarazo y estimar la fecha de parto.</b></li>
                <li><b>Permiten a los padres ver y obtener imágenes del bebé antes del nacimiento.</b></li>
            </ul>

            Recuerda siempre discutir cualquier pregunta o inquietud sobre las ecografías con tu profesional de la salud.
        """.trimIndent()

        tvInfo.text = Html.fromHtml(infoText, Html.FROM_HTML_MODE_COMPACT)
        imageView.setImageResource(R.mipmap.ic_ultrasonido)
    }
}
