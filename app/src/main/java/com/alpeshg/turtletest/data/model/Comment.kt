package com.alpeshg.turtletest.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Comment(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("body")
    @Expose
    val body: String,
    @SerializedName("created_at")
    @Expose
    val createdAt: String,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String,
    @SerializedName("number")
    @Expose
    var number: Int,
    @SerializedName("user_id")
    @Expose
    var userId: Int,
    @Ignore
    @SerializedName("user")
    @Expose
    var user: User
) {
    constructor(
        id: Int,
        body: String,
        createdAt: String,
        updatedAt: String,
        number: Int,
        userId: Int
    ) : this(
        id,
        body,
        createdAt,
        updatedAt,
        number,
        userId,
        User(0, "", "", "", false)
    )
}

