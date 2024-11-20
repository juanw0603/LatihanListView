package app.C14210290.latihanlistview

import android.app.DownloadManager.Query
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var data = mutableListOf<String>()

        data.addAll(listOf("1", "2", "3", "4", "5"))


        val lvAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, data
        )

        val _lv1 = findViewById<ListView>(R.id.lv1)

        _lv1.adapter = lvAdapter

        val _btnTambah = findViewById<Button>(R.id.button_nambahData)

        _btnTambah.setOnClickListener {
            val dtAkhir = Integer.parseInt(data.get(data.size - 1)) + 1
            data.add(dtAkhir.toString())

            lvAdapter.notifyDataSetChanged()
        }

        _lv1.setOnItemClickListener() { parent, view, position, id ->
            Toast.makeText(
                this, "data ke ${data[position]}", Toast.LENGTH_SHORT).show()
        }
        val _btnHapus = findViewById<Button>(R.id.button_hapusData)
        _btnHapus.setOnClickListener {
            data.removeFirst()
            lvAdapter.notifyDataSetChanged()
        }

        val _btnSearch = findViewById<SearchView>(R.id.SeachView1)


        _btnSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var position = p0?.toInt()

                if (position != null) {
                    if (position-1 > data.size || position-1 < 0){
                        Toast.makeText(this@MainActivity, "input tidak boleh kurang dari 1 atau lebih dari panjang data, yaitu ${data.size}", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
    }
}