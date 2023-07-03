package zs.xmx.room_test

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import zs.xmx.room_test.MyDatabase.Companion.LATEST_VERSION


@Database(
    version = LATEST_VERSION,
    entities = [User::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(
            from = 2,
            to = 3,
            spec = AutoMigrations.Schema2to3::class
        ),
    ],
    exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {

        const val LATEST_VERSION = 4

        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_3_4)
                    .build()
            }
            return instance!!
        }
    }


}