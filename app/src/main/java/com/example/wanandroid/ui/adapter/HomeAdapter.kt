package com.example.wanandroid.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wanandroid.R
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.model.MyBanner
import com.example.wanandroid.ui.home.HomeViewModel

class HomeAdapter(private val list:ArrayList<Article>,private val context:Context,val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    companion object{
        const val photoItem:Int=1
        const val commendItem:Int=2
        const val justRedItem:Int=3
        const val justBlueItem:Int=4
        const val firstTopItem:Int=5
        const val redAndBlueItem:Int=8
        const val treeColorsItem:Int=9
    }

    private var hasData:Boolean=false
    private val header:Int=0
    private val folder:Int=6
    private val empty:Int=7

    private lateinit var bannerList:List<String>

    private val mList= ArrayList<Article>()
    init {
        mList.add(Article(-1,"","","","","","","","", fresh = false, top = false))
        mList.addAll(list)
        mList.add(Article(-1,"","","","","","","","", fresh = false, top = false))
        mList.add(Article(-1,"","","","","","","","", fresh = false, top = false))
    }

    private val a = MyBanner()

    var vp:ViewPager2?=null


    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                val position=bindingAdapterPosition
                val url=mList[position].getUrl()
                viewModel.setUrl(url)
            }

        }

    }
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





    inner class CommendHolder(itemView: View): BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.none_item_author)
        val data:TextView=itemView.findViewById(R.id.none_item_time)
        val content:TextView=itemView.findViewById(R.id.none_item_content)
        val kind:TextView=itemView.findViewById(R.id.none_item_kinds)
    }
    inner class FirstTopViewHolder(itemView: View): BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.first_item_author)
        val data:TextView=itemView.findViewById(R.id.first_item_time)
        val content:TextView=itemView.findViewById(R.id.first_item_content)
        val kind:TextView=itemView.findViewById(R.id.first_item_kinds)
    }
    inner class RedAndBlueViewHolder(itemView: View): BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.re_blue_item_author)
        val data:TextView=itemView.findViewById(R.id.re_blue_item_time)
        val content:TextView=itemView.findViewById(R.id.re_blue_item_content)
        val kind:TextView=itemView.findViewById(R.id.re_blue_item_kinds)
        val so:TextView=itemView.findViewById(R.id.re_blue_item_source)
    }
    inner class JustRedViewHolder(itemView: View):BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.just_red_item_author)
        val data:TextView=itemView.findViewById(R.id.just_red_item_time)
        val content:TextView=itemView.findViewById(R.id.just_red_item_content)
        val kind:TextView=itemView.findViewById(R.id.just_red_item_kinds)
        val so:TextView=itemView.findViewById(R.id.just_red_item_source)
    }
    inner class JustBlueViewHolder(itemView: View) : BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.just_blue_item_author)
        val data:TextView=itemView.findViewById(R.id.just_blue_item_time)
        val content:TextView=itemView.findViewById(R.id.just_blue_item_content)
        val kind:TextView=itemView.findViewById(R.id.just_blue_item_kinds)
        val so:TextView=itemView.findViewById(R.id.just_blue_item_source)
    }
    inner class PhotoViewHolder(itemView: View) : BaseViewHolder(itemView){
        val photo:ImageView=itemView.findViewById(R.id.photo_item_photo)
        val au:TextView=itemView.findViewById(R.id.photo_item_author)
        val data:TextView=itemView.findViewById(R.id.photo_item_time)
        val content:TextView=itemView.findViewById(R.id.photo_item_content)
        val kind:TextView=itemView.findViewById(R.id.photo_item_kinds)
        val so:TextView=itemView.findViewById(R.id.photo_item_source)
    }
    inner class TreeColorsViewHolder(itemView: View) : BaseViewHolder(itemView){
        val au:TextView=itemView.findViewById(R.id.tree_colors_author)
        val data:TextView=itemView.findViewById(R.id.tree_colors_time)
        val content:TextView=itemView.findViewById(R.id.tree_colors_content)
        val kind:TextView=itemView.findViewById(R.id.tree_colors_kinds)
        val so:TextView=itemView.findViewById(R.id.tree_colors_item_source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            header->{
                HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.vp,parent,false))
            }

            folder-> FlooderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flooer_item,parent,false))
            empty-> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.empty_item,parent,false))

            treeColorsItem->TreeColorsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tree_colors_item,parent,false))
            photoItem-> PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false))
            justRedItem-> JustRedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.just_red_item,parent,false))
            justBlueItem-> JustBlueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.just_blue_item,parent,false))
            firstTopItem-> FirstTopViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.first_item,parent,false))
            redAndBlueItem-> RedAndBlueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.red_blue_item,parent,false))
            else-> CommendHolder(LayoutInflater.from(parent.context).inflate(R.layout.none_color_item, parent, false))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when(holder){
            is HeaderHolder->{
                a.setBanner(holder.vp2!!, bannerList,context)
            }
            is FlooderViewHolder->{}
            is EmptyViewHolder->{}


            is CommendHolder->{
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
            is PhotoViewHolder->{
                Glide.with(context).load(mList[position].getPhoto()).into(holder.photo)
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
                holder.so.text=mList[position].getSource()
            }
            is JustRedViewHolder->{
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
            is JustBlueViewHolder->{
                holder.so.text=mList[position].getSource()
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
            is FirstTopViewHolder->{
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
            is RedAndBlueViewHolder->{
                holder.so.text=mList[position].getSource()
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }
            is TreeColorsViewHolder->{
                holder.so.text=mList[position].getSource()
                holder.au.text = mList[position].getAuName()
                holder.data.text=mList[position].getShareData()
                holder.kind.text=mList[position].getChapterName()+"/"+mList[position].getSuperChapterName()
                holder.content.text=mList[position].getContent()
            }

        }
    }


    override fun getItemCount()=mList.size

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return header
        }
        if(position==mList.size-1){
            return empty
        }
        if(position==mList.size-2){
            return folder
        }



        return mList[position].getType()
    }


    private inner class UpData( var oldList:List<Article>,var newList:List<Article>): DiffUtil.Callback() {


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

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int)=""

    }


    fun update(newList:List<Article>){
        if(!hasData) {
            hasData=true
            (newList as ArrayList).add(0, mList[0])
        }
        val result:DiffUtil.DiffResult=DiffUtil.calculateDiff(UpData(mList,newList),true)
        mList.clear()
        mList.addAll(newList)
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

    fun setBanner(list:List<String>){
        if(!::bannerList.isInitialized) {
            this.bannerList = list
        }
    }

}