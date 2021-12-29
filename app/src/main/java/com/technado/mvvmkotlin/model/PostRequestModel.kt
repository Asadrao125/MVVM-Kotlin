package com.technado.mvvmkotlin.model

class PostRequestModel {
    var userId: Int = 0
    var title: String = ""
    var body: String = ""

    constructor(userId: Int, title: String, body: String) {
        this.userId = userId
        this.title = title
        this.body = body
    }
}