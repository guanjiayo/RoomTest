package zs.xmx.room_test.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import zs.xmx.room_test.crud.CRUDActivity
import zs.xmx.room_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter
    private val mItemList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initAdapter()
        initData()
        initEvent()
    }

    private fun initData() {
        mItemList.add("增删改查")
        mAdapter.setData(mItemList)
    }

    private fun initEvent() {
        mAdapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, text: String) {
                when (position) {
                    0 -> startActivity(Intent(this@MainActivity, CRUDActivity::class.java))
                }
            }
        })
    }

    private fun initAdapter() {
        mAdapter = MainAdapter()
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = mAdapter
    }

}