package abrorbek.uz.imthon


import abrorbek.Adapters.ContactAdapte
import abrorbek.Models.User
import abrorbek.uz.imthon.databinding.ActivityMainBinding
import abrorbek.uz.imthon.databinding.MyDialogBinding
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import abrorbek.db.My_Db_Browser
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var myDbHelper: My_Db_Browser
    private lateinit var myEmCustAdapter: ContactAdapte
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        myDbHelper = My_Db_Browser(binding.root.context)
        myEmCustAdapter = ContactAdapte(myDbHelper.getAllContacts())
        binding.rv.adapter = myEmCustAdapter

        binding.apply {

            btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialogBinding = MyDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialogBinding.root)
                itemDialogBinding.tvSave.setOnClickListener {

                    val myCostumer = User(
                        itemDialogBinding.edtName.text.toString()
                    )
                    dialog.cancel()
                    myDbHelper.addContact(myCostumer)
                }
                dialog.show()
                itemDialogBinding.tvCancel.setOnClickListener {
                dialog.cancel()
                }
            }

        }

    }

//    fun createNewDatabase() {
//        val url = "jdbc:sqlite:sample.db"
//        var conn: Connection? = null
//        try {
//            conn = DriverManager.getConnection(url)
//            println("Databaza yaratildi!")
//        } catch (e: SQLException) {
//            println(e.message)
//        } finally {
//            conn?.close()
//        }
//    }
//    fun createNewTable() {
//        val url = "jdbc:sqlite:sample.db"
//        var conn: Connection? = null
//        var stmt: Statement? = null
//        try {
//            conn = DriverManager.getConnection(url)
//            stmt = conn.createStatement()
//            val sql = """
//            CREATE TABLE IF NOT EXISTS users (
//                id INTEGER PRIMARY KEY,
//                name TEXT NOT NULL,
//                age INTEGER
//            );
//        """.trimIndent()
//            stmt.execute(sql)
//            println("Jadval yaratildi!")
//        } catch (e: SQLException) {
//            println(e.message)
//        } finally {
//            stmt?.close()
//            conn?.close()
//        }
//    }
//    fun insertUser(name: String, age: Int) {
//        val url = "jdbc:sqlite:sample.db"
//        var conn: Connection? = null
//        var stmt: Statement? = null
//        try {
//            conn = DriverManager.getConnection(url)
//            stmt = conn.createStatement()
//            val sql = "INSERT INTO users (name, age) VALUES ('$name', $age);"
//            stmt.executeUpdate(sql)
//            println("Foydalanuvchi qo'shildi!")
//        } catch (e: SQLException) {
//            println(e.message)
//        } finally {
//            stmt?.close()
//            conn?.close()
//        }
//    }
//    fun selectAllUsers() {
//        val url = "jdbc:sqlite:sample.db"
//        var conn: Connection? = null
//        var stmt: Statement? = null
//        try {
//            conn = DriverManager.getConnection(url)
//            stmt = conn.createStatement()
//            val sql = "SELECT * FROM users;"
//            val rs =stmt.executeQuery(sql)
//
//            while (rs.next()) {
//                val id = rs.getInt("id")
//                val name = rs.getString("name")
//                val age = rs.getInt("age")
//                println("ID = $id, Name = $name, Age = $age")
//            }
//        } catch (e: SQLException) {
//            println(e.message)
//        } finally {
//            stmt?.close()
//            conn?.close()
//        }
//    }
}