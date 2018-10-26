package thanhlongbanh8997.englishforeverybody

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_bar_home.*


/**
 * Created by Andy on 9/7/2018.
 */
abstract class BaseToolbarActivity: AppCompatActivity() {

    abstract var layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)

        initView()

        initData()
    }

    abstract fun initView()

    abstract fun initData()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}