package zs.xmx.room_test.crud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import zs.xmx.room_test.databinding.ActivityCrudBinding
import zs.xmx.room_test.db.UserDao
import zs.xmx.room_test.db.UserDatabase
import zs.xmx.room_test.model.User

class CRUDActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityCrudBinding

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCrudBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        userDao = UserDatabase.getInstance(this).userDao()

        initEvent()
    }

    private fun initEvent() {
        mBinding.actionInsert.setOnClickListener {
            lifecycleScope.launch {
                //userDao.addUser(User(name = "张三"))
                //1到2 新增 age
                //userDao.addUser(User(name = "张三", age = 10))
                //2到3 新增 sex,默认值为man,数据库的列名改成gender
                //userDao.addUser(User(name = "张三", age = 23, gender = "female"))
                //3到4 手动迁移添加address
                userDao.addUser(User(name = "张三", age = 23, sex = "man", address = "你过来啊"))
            }
        }
    }
}