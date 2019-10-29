package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import java.util.*

fun User.toUserView() : UserView{
    val nickName = "User"
    val initials ="UU"
    val status = if(lastVisit == null) "ниразу не был" else if (isOnline) "онлайн" else "Последний раз был ${Date().format()}"
   return  UserView(
        id ,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status

    )
}