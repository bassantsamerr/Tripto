package com.example.tripto

public class User {
    class User {
        private var email: String = ""
        var _email: String
            get() = email
            set(value) {
                email = value
            }

        private var age: Int = 0
        var _age: Int
            get() = age
            set(value) {
                age = value
            }

        private var country: String = ""
        var _country: String
            get() = country
            set(value) {
                country = value
            }

        private var username: String = ""
        var _username: String
            get() = username
            set(value) {
                username = value
            }

        private var role_id: Int = 0
        var _role_id: Int
            get() = role_id
            set(value) {
                role_id = value
            }

        private var password: String = ""
        var _password: String
            get() = password
            set(value) {
                password = value
            }
    }

}