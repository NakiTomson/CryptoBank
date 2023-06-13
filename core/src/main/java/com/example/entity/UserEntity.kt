package com.example.entity

data class UserEntity(
    val id: String,
    val email: String,
    val type: AuthorizationType,
    val name: String? = null,
    val avatar: String? = null,
)

enum class AuthorizationType(val type: String) {
    FireBase("FireBase"),
    Google("Google"),
    Facebook("Facebook"),
    Null("Null");

    companion object {
        fun getEnumValue(name: String): AuthorizationType {
            return getEnumValueOrNull(name) ?: throw NullPointerException()
        }

        private fun getEnumValueOrNull(name: String): AuthorizationType? {
            return AuthorizationType::class.java.enumConstants?.firstOrNull { it.type == name || it.name.uppercase() == name.uppercase() }
        }
    }
}