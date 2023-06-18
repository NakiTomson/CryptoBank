package com.example.mapper

import com.example.entity.AuthorizationType
import com.example.entity.UserEntity
import com.example.local.entity.UserDb


fun UserDb.toEntity(): UserEntity {
    return UserEntity(
        id,
        email,
        AuthorizationType.getEnumValue(type),
        name,
        avatar,
    )
}
