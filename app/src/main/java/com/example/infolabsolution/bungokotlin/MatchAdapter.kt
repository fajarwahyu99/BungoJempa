package com.example.infolabsolution.bungokotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private val match: List<MatchDetail>, private val listener: (MatchDetail) -> Unit): RecyclerView.Adapter<MatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = match.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val matchDate: TextView = itemView.find(R.id.date)
        private val homeTeam: TextView = itemView.find(R.id.homeTeam)
        private val homeScore: TextView = itemView.find(R.id.homeScore)
        private val awayTeam: TextView = itemView.find(R.id.awayTeam)
        private val awayScore: TextView = itemView.find(R.id.awayscore)

        fun bindItem(schedule: MatchDetail, listener: (MatchDetail) -> Unit){
            val date = strToDate(schedule.eventDate)
            matchDate.text = changeFormatDate(date)
            @SuppressLint("SimpleDateFormat")
            fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date {
                val format = SimpleDateFormat(pattern)
                val date = format.parse(strDate)

                return date
            }

            @SuppressLint("SimpleDateFormat")
            fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
                SimpleDateFormat("EEE, dd MMM yyy").format(this)
            }
            homeTeam.text = schedule.homeTeam
            homeScore.text = schedule.homeScore

            awayTeam.text = schedule.awayTeam
            awayScore.text = schedule.awayScore

            itemView.onClick {
                listener(schedule)
            }
        }
    }

    class MatchUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    margin = dip(4)
                    radius = 4f
                }

                verticalLayout {

                    textView("Minggu, 18 Agustus 2018"){
                        id = R.id.date
                    }.lparams(width = wrapContent, height = wrapContent){
                        gravity = Gravity.CENTER
                        margin = dip(8)
                    }

                    relativeLayout {

                        textView("Chelsea"){
                            id = R.id.homeTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.RIGHT
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.homeScore)
                            rightMargin = dip(10)
                        }

                        textView("3"){
                            id = R.id.homeScore
                            textSize = 12f
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.vs)
                        }

                        textView("VS"){
                            id = R.id.vs
                            textSize = 10f
                        }.lparams(width = wrapContent, height = wrapContent){
                            centerInParent()
                            leftMargin = dip(6)
                            rightMargin = dip(6)
                        }

                        textView("2"){
                            id = R.id.awayscore
                            textSize = 12f
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.vs)
                        }

                        textView("Arsenal"){
                            id = R.id.awayTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.LEFT
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.awayscore)
                            leftMargin = dip(10)
                        }

                    }.lparams(width = matchParent, height = wrapContent)

                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottomMargin = dip(8)
                }
            }
        }

    }


}