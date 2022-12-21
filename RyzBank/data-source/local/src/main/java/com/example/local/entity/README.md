### Пакет предназначен для Entity базы данных Room. 
Сами по себе Entity должно быть иммутабельны (неизменяемые) и не должны содержать внутри себя какую 
либо логику.
> Нейминг классов XxxEntity

Должной практикой в аннотацию ```@Entity``` требуется указывать параметр ```tableName``` 
и именовать таблицу в множественном числе.
Так-же стоит указывать и имя полей, только в ```@ColumnInfo``` параметр ```name```.
> Исключением является параметр id 

Пример:
```
@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
```
