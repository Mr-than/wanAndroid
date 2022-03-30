package com.example.wanandroid.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wanandroid.R
import com.example.wanandroid.base.APP
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MyBanner {
    private var isFirst:Boolean=true
    private lateinit var viewPager2: ViewPager2


    fun setBanner(viewPager2: ViewPager2, img:List<String>,context:Context){

        if(!isFirst){
            return
        }

        isFirst=false

        this.viewPager2=viewPager2

        val im= mutableListOf<String>()


        for (i in 0..1000){
            im.addAll(img)
        }
        val mViewPagerAdapter=InnerAdapter(im,context)
        viewPager2.adapter = mViewPagerAdapter

        viewPager2.setCurrentItem(img.size*100, false)

        Thread {

            while (true) {

                Thread.sleep(5000)

                Observable.create(ObservableOnSubscribe<Any> {
                    t->
                    t.onComplete()
                }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Any> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: Any) {
                        }

                        override fun onError(e: Throwable) {
                        }

                        override fun onComplete() {

                            val thisCon=viewPager2.currentItem
                            viewPager2.setCurrentItem(thisCon+1,true)


                        }
                    })
            }
        }.start()
    }


    private class InnerAdapter(val list: List<String>,val context: Context): RecyclerView.Adapter<InnerAdapter.InnerViewHolder>() {

        private inner class InnerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            val imageView:ImageView=itemView.findViewById(R.id.banner_iv)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
            return InnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_layout,parent,false))
        }

        override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
            Glide.with(context).load(list[position]).into(holder.imageView)
        }

        override fun getItemCount(): Int {
            return Int.MAX_VALUE
        }
    }



   inner class Dots : View {
        private lateinit var paint: Paint
        private lateinit var viewPager2: ViewPager2

        constructor(context: Context?) : super(context){initContent()}
        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){initContent()}
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){initContent()}
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){initContent()}

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
        }

        private fun initContent() {
            paint = Paint()
            viewPager2=this@MyBanner.viewPager2


            val path= Path()


        }
    }


}