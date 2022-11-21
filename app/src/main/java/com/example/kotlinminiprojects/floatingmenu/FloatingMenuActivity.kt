package com.example.kotlinminiprojects.floatingmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kotlinminiprojects.R
import kotlinx.android.synthetic.main.activity_floating_menu.*

class FloatingMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_menu)

        registerForContextMenu(btnOpenMenu)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        /**
         * inflate
         */
        menuInflater.inflate(R.menu.floating_context_menu, menu)

    }

    /**
     *  on click listener
     */
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_item1 -> Toast.makeText(this, "Clicked item 1", Toast.LENGTH_SHORT).show()
            R.id.mi_item2 -> Toast.makeText(this, "Clicked item 2", Toast.LENGTH_SHORT).show()
            R.id.mi_item3 -> Toast.makeText(this, "Clicked item 3", Toast.LENGTH_SHORT).show()
            R.id.mi_item4 -> Toast.makeText(this, "Clicked item 4", Toast.LENGTH_SHORT).show()

        }
        return super.onContextItemSelected(item)
    }

}