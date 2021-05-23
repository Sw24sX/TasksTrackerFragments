package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class HabitLocal(
    @SerializedName("title")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("count")
    var countExecutions: String,

    @SerializedName("frequency")
    var period: String,

    @SerializedName("priority")
    var priorityPosition: String?,

    @SerializedName("type")
    var type: String,

    @SerializedName("uid")
    var uid: String? = null,

    @SerializedName("date")
    var date: String = "0"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}