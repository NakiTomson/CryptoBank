### Пакет предназначен для Dao базы данных Room.
Стоит помнить что в сложных операциях над бд стоит использовать [транзакции](https://developer.android.com/reference/android/arch/persistence/room/Transaction). 
> Нейминг классов XxxDao

Пример:
```
@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>
}
```
