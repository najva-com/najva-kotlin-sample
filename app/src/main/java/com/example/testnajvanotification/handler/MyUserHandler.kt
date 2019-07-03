package com.example.testnajvanotification.handler

import com.najva.najvasdk.Class.NajvaUserHandler

class MyUserHandler(val newUser : (String)->Unit) : NajvaUserHandler() {
    override fun najvaUserSubscribed(token: String?) {
        if (token!=null) newUser(token)
    }
}