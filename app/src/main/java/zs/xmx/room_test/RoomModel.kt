package zs.xmx.room_test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Author: 默小铭
 * Blog:   https://blog.csdn.net/u012792686
 * Desc:
 *
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(defaultValue = "0")
    val age: Int,
    @ColumnInfo(name = "sex", defaultValue = "man")
    val sex: String,
    @ColumnInfo(name = "address")
    val address: String,
)