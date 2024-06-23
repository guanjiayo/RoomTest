## 待实现的功能
拆分测试点
1. 基本的增删改查  列表形式展示
2. 复杂数据处理,如一个字段传对象,联表查询等
3. 数据库版本升级(自动,手动区别) -- 能实现功能的最大程度

## Room 数据迁移(版本升级)

> https://developer.android.google.cn/training/data-storage/room/migrating-db-versions?hl=zh-cn

### 数据库配置build.gradle

```

defaultConfig{
    //...
     javaCompileOptions {
            annotationProcessorOptions {
                arguments += ["room.schemaLocation"  : "$projectDir/schemas".toString(),
                              "room.incremental"     : "true",
                              "room.expandProjection": "true"]
            }
        }
}

dependencies{
    implementation "androidx.room:room-runtime:2.4.3"
    implementation "androidx.room:room-ktx:2.4.3"
    implementation "androidx.room:room-migration:2.4.3"

    kapt "androidx.room:room-compiler:2.4.3"
}

```

### 自动迁移

```

//---------------------数据实体类----------------------

1. 新增列需要 @ColumnInfo(defaultValue = "0") 注解设置默认值

//---------------------数据库配置----------------------

@Database(
    version = LATEST_VERSION,
    entities = [User::class],
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2
        ),
    ],
    exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {

        const val LATEST_VERSION = 2

        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
        
    }

```