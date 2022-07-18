package com.alpeshg.turtletest.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Issue(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("body")
    @Expose
    val body: String?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String,
    @SerializedName("closed_at")
    @Expose
    val closedAt: String?,
    @SerializedName("number")
    @Expose
    val number: Int,
    @SerializedName("user_id")
    @Expose
    var userId: Int,
    @Ignore
    @SerializedName("user")
    @Expose
    var user: User
) : Parcelable {
    constructor(
        id: Int,
        title: String,
        body: String?,
        createdAt: String,
        updatedAt: String,
        closedAt: String?,
        number: Int,
        userId: Int
    ) : this(
        id,
        title,
        body,
        createdAt,
        updatedAt,
        closedAt,
        number,
        userId,
        User(0, "", "", "", false)
    )
}

