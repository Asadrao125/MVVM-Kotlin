package com.technado.mvvmkotlin.model

class PostResponseModel {
    var userId: Int = 0
    var id: Int = 0
    var title: String = ""
    var body: String = ""

    constructor(userId: Int, id: Int, title: String, body: String) {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }
}