package zs.xmx.room_test.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import zs.xmx.room_test.model.User
import zs.xmx.room_test.db.UserDatabase.Companion.LATEST_VERSION


@Database(
    version = LATEST_VERSION,
    entities = [User::class],
    /*autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(
            from = 2,
            to = 3,
            spec = AutoMigrations.Schema2to3::class
        ),
    ],*/
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {

        const val LATEST_VERSION = 1

        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_3_4)
                    .build()
            }
            return instance!!
        }
    }


}