### Пакет предназначен для Storage которые взаимодействуют с DataStore для хранения Preferences. 
Storage должен предоставлять методы взаимодействия с Preferences (getter, setter), и в нем не должно
быть никакой дополнительной логики.
> Нейминг классов XxxStorage

Пример:
```
class UserStorage @Inject constructor(private val context: Context) {
    private val authTokenKey = stringPreferencesKey("auth_token")

    suspend fun getAuthToken() = context.appDataStore
        .get(authTokenKey)

    suspend fun getAuthTokenFlow() = context.appDataStore.data
        .map { it[authTokenKey] }

    suspend fun setAuthToken(value: String) = context.appDataStore
        .edit { it[authTokenKey] = value }
}
```
