package pe.edu.upeu.upeuactivity


import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tabx.*
import pe.edu.upeu.upeuactivity.ui.tabs.PageAdapter


class TabxActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabx)
        supportActionBar?.setDisplayHomeAsUpEnabled(true).apply {
            title ="DMP"
            }

        val backArrow = resources.getDrawable(android.R.drawable.ic_menu_camera)
        backArrow.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(backArrow)
        supportActionBar?.setHomeButtonEnabled(true)
        val colores = intArrayOf(-0xdaa887, -0xc18b6e, -0x593f33)
        val g = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colores)
        supportActionBar?.setBackgroundDrawable(g)
       /*supportActionBar?.setBackgroundDrawable(
            GradientDrawable().apply {

                colors = intArrayOf(
                    //Color.parseColor("#FF6347"),
                    Color.parseColor("#DEAA88"),
                    Color.parseColor("#EEE600")
                )
                gradientType = GradientDrawable.LINEAR_GRADIENT

                shape = GradientDrawable.LINE
                orientation = GradientDrawable.Orientation.LEFT_RIGHT
                setStroke(2, Color.parseColor("#CD5700"))
            }
        )*/


        viewPager.adapter=PageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun setActionBar(toolbar: Toolbar?) {

        super.setActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}