package abrorbek.Models

import java.text.SimpleDateFormat
import java.util.*

class User {
    var id:Int? = null
    var name:String? = null
    var date:String? = null

    constructor(
        id: Int?,
        name: String?,
        date: String= SimpleDateFormat("dd.MM.yyyy ").format(Date())
    ) {
        this.id = id
        this.name = name
        this.date = date

    }

    constructor(
        name: String?,
        date: String = SimpleDateFormat("dd.MM.yyyy").format(Date())
    ) {
        this.name = name
        this.date = date
    }
}