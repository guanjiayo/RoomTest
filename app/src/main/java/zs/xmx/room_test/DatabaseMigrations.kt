package zs.xmx.room_test

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//--------------------------自动迁移-------------------------------------

/*
   按需组合使用一下注解
   @DeleteTable(deletedTableName = "Album")
   @RenameTable(fromTableName = "Singer", toTableName = "Artist")
   @RenameColumn(
       tableName = "Song",
       fromColumnName = "songName",
       toColumnName = "songTitle"
    )
   @DeleteColumn(fromTableName = "Song", deletedColumnName = "genre")
   class MyExampleAutoMigration : AutoMigrationSpec {
       @Override
       override fun onPostMigrate(db: SupportSQLiteDatabase) {
           // Invoked once auto migration is done
       }
    }

 */
class AutoMigrations {

    @RenameColumn(
        tableName = "user",
        fromColumnName = "sex",
        toColumnName = "gender"
    )
    class Schema2to3 : AutoMigrationSpec
}


//--------------------------手动迁移-------------------------------------

/*
    // Migration from 2 to 3, Room 2.2.0.
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
                CREATE TABLE new_Song (
                    id INTEGER PRIMARY KEY NOT NULL,
                    name TEXT,
                    tag TEXT NOT NULL DEFAULT ''
                )
                """.trimIndent())
        database.execSQL("""
                INSERT INTO new_Song (id, name, tag)
                SELECT id, name, tag FROM Song
                """.trimIndent())
        database.execSQL("DROP TABLE Song")
        database.execSQL("ALTER TABLE new_Song RENAME TO Song")
    }
}
 */
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
//        // 创建新表
//        database.execSQL("CREATE TABLE IF NOT EXISTS `user_temp` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `age` INTEGER NOT NULL DEFAULT 0, `sex` TEXT NOT NULL DEFAULT 'man')")
//
//        // 复制旧数据到新表
//        database.execSQL("INSERT INTO user_temp (id,name,age,sex) SELECT id,name,age,gender FROM user")
//
//        // 删除旧表
//        database.execSQL("DROP TABLE user")
//
//        // 重命名新表
//        database.execSQL("ALTER TABLE user_temp RENAME TO user")

        database.execSQL("ALTER TABLE user ADD COLUMN address TEXT")
    }
}
