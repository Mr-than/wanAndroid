package com.example.wanandroid.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.databean.applicationdata.Article

class SquareAdapter(val list:ArrayList<Article>):RecyclerView.Adapter<SquareAdapter.BaseViewHolder>(){
   private var hasData:Boolean=false
    private val mList=ArrayList<Article>()
    init {
        mList.addAll(list)
        mList.add(Article(-1,"","","","","","","","", fresh = false, top = false))
        mList.add(Article(-1,"","","","","","","","", fresh = false, top = false))
    }

    abstract inner class BaseViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    inner class FlooderViewHolder(itemView: View) : BaseViewHolder(itemView)
    inner class EmptyViewHolder(itemView: View) : BaseViewHolder(itemView)



    inner class SquareNewArticleViewHolder(itemView: View) :BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.just_red_item_author)
        val data:TextView=itemView.findViewById(R.id.just_red_item_time)
        val content:TextView=itemView.findViewById(R.id.just_red_item_content)
        val kind:TextView=itemView.findViewById(R.id.just_red_item_kinds)
        val so:TextView=itemView.findViewById(R.id.just_red_item_source)
    }

    inner class SquareNoArticleViewHolder(itemView: View) :BaseViewHolder(itemView){
        val au: TextView =itemView.findViewById(R.id.none_item_author)
        val data: TextView =itemView.findViewById(R.id.none_item_time)
        val content: TextView =itemView.findViewById(R.id.none_item_content)
        val kind: TextView =itemView.findViewById(R.id.none_item_kinds)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            0->{
                SquareNewArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.just_red_item,parent,false))
            }
            2-> FlooderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flooer_item,parent,false))
            3-> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.empty_item,parent,false))
            else->{
                SquareNoArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.none_color_item,parent,false))
            }

        }
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder){
            is FlooderViewHolder ->{}
            is EmptyViewHolder ->{}
            is SquareNewArticleViewHolder->{
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text="${mList[position].getChapterName()}/${mList[position].getSuperChapterName()}"
                holder.content.text=mList[position].getContent()
                holder.so.text="新"
            }
            is SquareNoArticleViewHolder->{
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
        }
    }
    override fun getItemCount()=mList.size

    private inner class Diff(val newData:List<Article>,val oldData:List<Article>): DiffUtil.Callback() {

        override fun getOldListSize()=oldData.size

        override fun getNewListSize()=newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int):Boolean{
            val a=newData[newItemPosition]
            val b=oldData[oldItemPosition]
            return a==b
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)=false

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int)=""
    }

    fun update(newList:List<Article>){
        val a=newList.size
        val result:DiffUtil.DiffResult=DiffUtil.calculateDiff(Diff(newList,mList),true)
        mList.clear()
        mList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }


    override fun getItemViewType(position: Int): Int {
        if(position==mList.size-1){
            return 2
        }
        if(position==mList.size-2){
            return 3
        }
        return mList[position].getType()
    }


}