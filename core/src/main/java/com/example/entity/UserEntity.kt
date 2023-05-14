package com.example.entity

data class UserEntity(
    val id: String,
    val email: String,
    val type: AuthorizationType
)


enum class AuthorizationType(val type: String) {
    FireBase("FireBase"),
    Google("Google"),
    Facebook("Facebook");

    companion object {
        fun getEnumValue(name: String): AuthorizationType {
            return getEnumValueOrNull(name) ?: throw NullPointerException()
        }

        fun getEnumValueOrNull(name: String): AuthorizationType? {
            return AuthorizationType::class.java.enumConstants?.firstOrNull { it.type == name || it.name.uppercase() == name.uppercase() }
        }
    }
}