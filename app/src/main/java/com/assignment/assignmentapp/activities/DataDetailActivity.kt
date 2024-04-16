package com.assignment.assignmentapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.assignment.assignmentapp.R
import com.assignment.assignmentapp.databinding.ActivityDataDetailBinding
import com.assignment.assignmentapp.databinding.ActivityDataListBinding
import com.assignment.assignmentapp.models.Data

class DataDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataDetailBinding
    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.extras?.getParcelable("data")

        binding.txtUserId.text = data?.userId.toString()
        binding.txtId.text = data?.id.toString()
        binding.txtTitle.text = data?.title.toString()
        binding.txtBody.text = data?.body.toString()

    }
}