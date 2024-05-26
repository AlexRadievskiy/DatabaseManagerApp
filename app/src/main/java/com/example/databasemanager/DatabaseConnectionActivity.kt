package com.example.databasemanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class atabaseConnectionActivity : AppCompatActivity() {

    private lateinit var connection: Connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_connection)

        val connectButton: Button = findViewById(R.id.connectButton)
        val hostEditText: EditText = findViewById(R.id.hostEditText)
        val portEditText: EditText = findViewById(R.id.portEditText)
        val dbNameEditText: EditText = findViewById(R.id.dbNameEditText)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        connectButton.setOnClickListener {
            val host = hostEditText.text.toString()
            val port = portEditText.text.toString()
            val dbName = dbNameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            connectToDatabase(host, port, dbName, username, password)
        }
    }

    private fun connectToDatabase(host: String, port: String, dbName: String, username: String, password: String) {
        val url = "jdbc:mysql://$host:$port/$dbName"
        try {
            connection = DriverManager.getConnection(url, username, password)
            Toast.makeText(this, "Connected to database", Toast.LENGTH_LONG).show()
        } catch (e: SQLException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to connect to database", Toast.LENGTH_LONG).show()
        }
    }
}
