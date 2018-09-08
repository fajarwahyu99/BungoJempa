package com.example.infolabsolution.bungokotlin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchDetail(
        @SerializedName("idEvent")
        var eventId: String?,

        @SerializedName("strEvent")
        var eventName: String?,

        @SerializedName("dateEvent")
        var eventDate: String?,

        @SerializedName("idHomeTeam")
        var homeTeamId: String?,

        @SerializedName("idAwayTeam")
        var awayTeamId: String?,

        @SerializedName("strHomeTeam")
        var homeTeam: String?,

        @SerializedName("strAwayTeam")
        var awayTeam: String?,

        @SerializedName("intHomeScore")
        var homeScore: String?,

        @SerializedName("intAwayScore")
        var awayScore: String?,

        @SerializedName("intHomeShots")
        var homeShots: String?,

        @SerializedName("intAwayShots")
        var awayShots: String?,

        @SerializedName("strHomeFormation")
        var homeFormation: String?,

        @SerializedName("strAwayFormation")
        var awayFormation: String?,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String?,

        @SerializedName("strAwayGoalDetails")
        var awayGoalsDetails: String?,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalKeeper: String?,

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String?,

        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String?,

        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String?,

        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubstitutes: String?,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalKeeper: String?,

        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String?,

        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String?,

        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String?,

        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubstitutes: String?,

        @SerializedName("strTeamBadge")
        var teamBadge: String?
): Parcelable