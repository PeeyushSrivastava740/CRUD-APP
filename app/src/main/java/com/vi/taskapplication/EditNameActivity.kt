package com.vi.taskapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.vi.taskapplication.viewmodels.DataRecordViewModel

class EditNameActivity : AppCompatActivity() {

    var nameData:String?=null
    var nameId:Long?=null
    lateinit var edtText: EditText
    lateinit var txtUpdate: TextView
    lateinit var txtDel: TextView
    private lateinit var dataRecordViewModel: DataRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_name)
        dataRecordViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            DataRecordViewModel::class.java)
        nameData = intent.getStringExtra("name")
        nameId = intent.getLongExtra("id",0)

        initView()
        setViewClick()
    }

    private fun setViewClick() {

        txtUpdate.setOnClickListener {
            val id = nameId
            val record = edtText.text.toString()

            if (record.isBlank() or record.isEmpty()) {
                Toast.makeText(this, "Empty data is not allowed", Toast.LENGTH_SHORT).show()
            } else {
                val item = TableData(taskId = id!!, taskName = record)
                dataRecordViewModel.update(item)
                finish()
            }
        }

        txtDel.setOnClickListener {
            val id = nameId
            val record = edtText.text.toString()

            val item = TableData(taskId = id!!, taskName = record)
            dataRecordViewModel.delete(item)
            finish()
        }

    }

    private fun initView() {
        edtText = findViewById(R.id.editTextTextPersonName2)
        txtDel = findViewById(R.id.txtDel)
        txtUpdate = findViewById(R.id.txtUpdate)
        edtText.setText(nameData)

    }
}