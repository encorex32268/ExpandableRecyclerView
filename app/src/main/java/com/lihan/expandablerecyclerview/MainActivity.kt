package com.lihan.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lihan.expandablerecyclerview.model.GroupItem
import com.lihan.expandablerecyclerview.model.Item

class MainActivity : AppCompatActivity() {

    companion object{
        //datas
        val dumpData : List<GroupItem> = listOf(
            GroupItem(
                title = "Green",
                items = arrayListOf()
//                listOf(
//                    Item(id = 0, name = "草"),
//                    Item(id = 1, name = "森林"),
//                    Item(id = 2, name = "綠檸檬"),
//                    Item(id = 3, name = "椪柑"),
//                    Item(id = 4, name = "綠燈"),
//                )
            ),
            GroupItem(
                title = "Yellow",
                items = listOf(
                    Item(id = 0, name = "鳳梨"),
                    Item(id = 1, name = "檸檬"),
                    Item(id = 2, name = "香蕉"),
                    Item(id = 3, name = "計程車"),
                    Item(id = 4, name = "蛋塔"),
                )
            ),
            GroupItem(
                title = "Red",
                items = arrayListOf()
//                listOf(
//                    Item(id = 0, name = "蘋果"),
//                    Item(id = 1, name = "火星"),
//                    Item(id = 2, name = "血"),
//                    Item(id = 3, name = "紅寶石"),
//                    Item(id = 4, name = "小火龍"),
//                )
            ),
            GroupItem(
                title = "Blue",
                items = arrayListOf()
//                listOf(
//                    Item(id = 0, name = "天空"),
//                    Item(id = 1, name = "海"),
//                    Item(id = 2, name = "超五籃"),
//                    Item(id = 3, name = "藍寶石"),
//                    Item(id = 4, name = "藍色"),
//                )
            ),

        )
    }

    private lateinit var recyclerView : RecyclerView

    private val concatAdapter = ConcatAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        setAdapter(dumpData)

    }

    private fun setAdapter(data : List<GroupItem>){
        data.forEach {
            val mAdapter = ExpandableAdapter(it)
            mAdapter.onClick={
                Log.d("TAG", "setAdapter: ${it.title} ${it.items}")
            }
            concatAdapter.addAdapter(mAdapter)
        }
        recyclerView.adapter = concatAdapter
    }
}