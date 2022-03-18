package com.example.wanandroid.ui.adapter

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid.R
import com.example.wanandroid.model.MyBanner
//import com.example.wanandroid.model.Test
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(private val list:ArrayList<String>) : RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    val header:Int=0
    val photoItem:Int=1
    val commendItem:Int=2
    val justRedItem:Int=3
    val justBlueItem:Int=4
    val firstTopItem:Int=5
    val foolder:Int=6
    val empty:Int=7
    val redAndBlueItem:Int=8

    val aList= ArrayList<String>()
    init {
        aList.add("")
        aList.addAll(list)
        aList.add("")
        aList.add("")
    }

    val a = MyBanner()

    var vp:ViewPager2?=null


    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class HeaderHolder(itemView: View) : BaseViewHolder(itemView){
        var vp2:ViewPager2?
        init {
            if(vp==null){
                 vp=itemView.findViewById(R.id.vp_2)
            }
            vp2=vp
        }
    }



    inner class FlooderViewHolder(itemView: View) : BaseViewHolder(itemView)
    inner class EmptyViewHolder(itemView: View) : BaseViewHolder(itemView)

    inner class CommendHolder(itemView: View): BaseViewHolder(itemView)
    inner class FirstTopViewHolder(itemView: View): BaseViewHolder(itemView)
    inner class RedAndBlueViewHolder(itemView: View): BaseViewHolder(itemView)
    inner class JustRedViewHolder(itemView: View):BaseViewHolder(itemView)
    inner class JustBlueViewHolder(itemView: View) : BaseViewHolder(itemView)
    inner class PhotoViewHolder(itemView: View) : BaseViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            header-> HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.vp,parent,false))
            foolder-> FlooderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flooer_item,parent,false))
            empty-> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.empty_item,parent,false))
            photoItem-> PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false))
            justRedItem-> JustRedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.just_red_item,parent,false))
            justBlueItem-> JustBlueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.just_blue_item,parent,false))
            firstTopItem-> FirstTopViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.first_item,parent,false))
            redAndBlueItem-> RedAndBlueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.red_blue_item,parent,false))
            else-> CommendHolder(LayoutInflater.from(parent.context).inflate(R.layout.none_color_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when(holder){
            is HeaderHolder->{
                a.setBanner(holder.vp2!!, listOf(R.drawable.test, R.drawable.test1, R.drawable.test2))
            }
            is FlooderViewHolder->{}
            is EmptyViewHolder->{}
            is CommendHolder->{

            }
            is PhotoViewHolder->{

            }
            is JustRedViewHolder->{

            }
            is JustBlueViewHolder->{

            }
            is FirstTopViewHolder->{

            }
            is RedAndBlueViewHolder->{

            }
        }
    }


    override fun getItemCount()=aList.size

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return header
        }
        if(position==aList.size-1){
            return empty
        }
        if(position==aList.size-2){
            return foolder
        }

        return commendItem
    }


    private inner class UpData( var oldList:List<String>,var newList:List<String>): DiffUtil.Callback() {


        override fun getOldListSize(): Int {
            return  oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            val a=oldList[oldItemPosition]
            val b=newList[newItemPosition]
            return (a==b)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return false
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {

            val newItem = newList[newItemPosition]

            val bundle= Bundle()
            bundle.putString("2333",newItem)
            return bundle
        }

    }


    fun update(newList:List<String>){

        val result:DiffUtil.DiffResult=DiffUtil.calculateDiff(UpData(aList,newList),true)
        aList.clear()
        aList.addAll(newList)
        result.dispatchUpdatesTo(this)

    }






    /*
    override fun onItemMove(from: Int, to: Int) {
        val aaa=ArrayList<String>()
        aaa.addAll(aList)
        Collections.swap(aaa,from,to)
        update(aaa)
    }
*/

}