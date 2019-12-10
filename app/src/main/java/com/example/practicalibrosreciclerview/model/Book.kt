package com.example.practicalibrosreciclerview.model

import java.io.Serializable

class Book(
        val id: String,
        val isbn: String,
        val title: String,
        val authorFirstName: String,
        val authorLastName: String,
        val published: String,
        val publisher: String,
        val pagesNumber: String,
        val description: String,
        val imageUrl: String,
        val category: String) : Serializable