package com.vi.taskapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.vi.taskapplication.adapter.NameAdapter
import com.vi.taskapplication.viewmodels.DataRecordViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var rvNameList:RecyclerView
    private lateinit var imgAdd:ImageView
    private var nameAdapter:NameAdapter?=null
    private  var nameList= ArrayList<TableData>()
    private  var nameDialog:BottomSheetDialog?=null
    private lateinit var dataRecordViewModel: DataRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRecordViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.
        getInstance(application)).get(DataRecordViewModel::class.java)

        initView()
        getData()
        setViewClick()
    }

    private fun setViewClick() {

        imgAdd.setOnClickListener {
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.add_name_bottom_sheet, null)
            nameDialog = BottomSheetDialog(this@MainActivity, R.style.SheetDialog)


            val edtName= view.findViewById<EditText>(R.id.editTextTextPersonName)
            val txtClose:TextView= view.findViewById(R.id.txt_cancel)
            val txtSave:TextView= view.findViewById(R.id.txt_save_name)
            nameDialog!!.setContentView(view)
            nameDialog!!.show()

            txtClose.setOnClickListener {
                nameDialog!!.dismiss()
            }

            txtSave.setOnClickListener {
                val id = 0L
                val record = edtName.text.toString()

                if (record.isBlank() or record.isEmpty()) {
                    Snackbar.make(view, "Empty data is not allowed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                } else {
                    val item = TableData(taskId = id, taskName = record)
                    dataRecordViewModel.insert(item)
                   nameDialog!!.dismiss()
                }
            }
        }
    }

    private fun getData() {
        nameAdapter = NameAdapter(nameList)
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvNameList.layoutManager = mLayoutManager
        rvNameList.itemAnimator = DefaultItemAnimator()
        rvNameList.adapter = nameAdapter
        dataRecordViewModel.allItems.observe(this, Observer { items ->
            items?.let { nameAdapter!!.setItems(it) }
        })
    }

    private fun initView() {

        rvNameList = findViewById(R.id.rv_NameList)
        imgAdd = findViewById(R.id.imageView)
    }
}