package com.app.coresamplesourcemvvmkoinkotlin.data.pojos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "Users")
data class User constructor(
    @PrimaryKey
    @SerializedName("id")
    @Expose var id: Int = -1,

    @ColumnInfo
    @SerializedName("name")
    @Expose var name: String? = null,

    @ColumnInfo
    @SerializedName("email")
    @Expose var email: String? = null,

    @ColumnInfo
    @SerializedName("phone")
    @Expose var phone: String? = null,

    @ColumnInfo
    @SerializedName("avatar")
    @Expose var avatar: String? = null,

    @ColumnInfo
    @SerializedName("email_verified_at")
    @Expose var email_verified_at: String?=null,

    @ColumnInfo
    @SerializedName("createdAt")
    @Expose var created_at: String? = null,

    @ColumnInfo
    @SerializedName("updatedAt")
    @Expose var updated_at: String? = null,

    @ColumnInfo
    @SerializedName("token")
    @Expose var token: String? = null

) : Serializable {
    override fun toString(): String {
        return "User(id=$id, name=$name, email=$email, phone=$phone, avatar=$avatar, email_verified_at=$email_verified_at, created_at=$created_at, updated_at=$updated_at, token=$token)"
    }
}