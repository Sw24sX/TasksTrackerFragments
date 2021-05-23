package com.example.data.datatask

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Task(
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
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun default(type: TaskType): Task {
            return Task("", "", "", "", "", type.toString())
        }
    }
}