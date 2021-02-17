# Курсовой проект по предмету Информационные системы и базы данных

### Предметная область "Производство и продажа колбасных изделий"

Представим, что нам нужно проследить путь колбасных изделий начиная от цеха по 
производству до прилавок магазинов и разных мясных лавок. 
Что из себя представляет наша предметная область: Есть много цехов где производят 
разные колбасные изделия у каждого цеха разный список продукции и каждый цех имеет 
свои фермы на разных городах и странах. В этих фермах есть разные животные включая 
экзотических животных. В зависимости от страны и города в фермах будут разводится 
разные животные включая экзотических которые доступны только в этой местности. У 
каждого цеха имеется склад в которых хранятся готовые изделия. У 
каждого цеха есть специальные люди (сотрудники цеха) которые будут поставлять разные 
колбасные изделия клиентам, под клиентами подразумевается продавец в магазине или в мясной лавке или еще где ни будь. Есть специальные лавки где продаются только 
экзотическая продукция цеха. У каждого цеха будет свой график, в зависимости от 
графика будут поставляться колбасные изделия.  Сотрудник одновременно может быть 
сотрудников нескольких цехов. В зависимости от груза (колбасных изделий) каждому 
сотруднику будет предоставлена специальная машина для перевозки груза. Город 
разделен на участки, каждый сотрудник может поставлять колбасные изделия только на 
своих участках. Продавцы могут иметь несколько поставщиков из разных цехов. Бывают 
такие случаи, когда колбасные изделия могут иметь дефекты и в этом случае продавец 
может обратно возвратить эти товары поставщикам, и поставщики в свою очередь 
цеху.  Клиент не всегда платежа способен, и поставщики чтобы не потерять клиентов 
могут отдать в долг товары. Клиенты могут сделать специальные заказы которые будут 
обрабатываться отдельно. Для сотрудников цехов и постоянных клиентов будут 
предоставлены скидки и льготные услуги на продукцию. Есть каталог подписок для 
клиентов в зависимости от подписки клиент может составить список продукции, которых 
может заказать. Для VIP клиентов у нас будет VIP подписки заказы которых будут 
обрабатываться отдельно.


### Frontend
[Frontend](https://github.com/MansurovB-source/CourseProject-ISDB-front_end)
### Backend
[Backend](https://github.com/MansurovB-source/CourseWorkBackEnd_ISDB)
### Даталогическая Модель базы данных 
![модель базы данных](https://github.com/MansurovB-source/CourseProject-ISDB/blob/main/data-model/courseworkDM.png)

### Код на Java-е для массогово заполнения 
[Код на Java](https://github.com/MansurovB-source/CourseProject-ISDB/tree/main/src)

### Скрипты для массогово заполнения
[Скрипты](https://github.com/MansurovB-source/CourseProject-ISDB/tree/main/scripts)

### Сценарии использования
Основные две сценарии использования: это действия от имени поставщика и действия от имени клиента (заказчик). 
Если пользователь действует от имени клиента, он может смотреть на список товаров, на своего поставщика, 
он может покупать конкретные товары, посмотреть на список сделанных заказов и тд. Если пользователь выполняет
действия от имени поставщика он может посмотреть на список своих клиентов, список заказов от клиентов, расписания и тд.

Система автоматически раз в определенный промежуток времени обновляет информацию об цехе.

Также существует режим цеха (администратор) в котором можно делать все. Точнее добавлять, удалять и изменять список товаров, список клиентов, список поставщиков и тд.  



### Описание основных прецедентов (бизнес процессы)
Прецедент             | Добавление нового поставщика 
:-----:               | :---- 
ID                    | 1 
Краткое описание      | В таблицу Providers добавляется новый поставщик 
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | Информация о человеке, фабрике, место поставки должна быть определена. О поставщика должна быть обязательно заполнена следующая информация: ссылка на человека, зарплата, ссылка на фабрику на которой он работает   
Основной поток        | <ol><li>Администратор базы данных вносит информацию об поставщике: `id_human`, `salary`, `id_factory`, `id_delivery_place`</li><li>Создается поставщик с `id_human`, `salary`, `id_factory`, `id_delivery_place`</li><li>И срабатывает триггер который увеличивает счетчик работника в таблице `factories`</li></ol>    


Прецедент             | Добавление нового клиента
:-----:               | :---- 
ID                    | 2
Краткое описание      | В таблицу Clients добавляется новый клиент 
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | Информация о человеке, место поставки, подписки должна быть определена. О поставщика должна быть обязательно заполнена следующая информация: ссылка на человека, ссылка на поставщика и ссылка на подписки   
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `id_human`, `id_delivery_place`, `id_subscription`</li><li>Создается клиент с `id_human`, `id_delivery_place`, `id_subscription`</li><li>И срабатывает триггер который увеличивает счетчик клиентов в таблице `delivery_places`</li></ol>    


Прецедент             | Добавление нового заказа
:-----:               | :---- 
ID                    | 3
Краткое описание      | В таблицу Orders добавляется новый заказ
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | О заказе должна быть обязательно заполнена следующая информация: клиент который заказывает, тип продукции, количество продукции в кг   
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `id_from (client)`, `id_sausage`, `sausages_weight`</li><li>Перед тем как заказ будет принять срабатывает триггер `make_Schedule`. Выбирается поставщик по данному клиенту. Проверяется есть ли на складе этот продукт и если есть хватает ли его нам. Если 1 условие выполнено то изменяется количество продукции в складе. Заказ добавляется в таблицу расписаний. Триггер заполняет недостающие данные</li><li>После принятие заказа срабатывает триггер `payment`. Который добавляет запись в таблице `client_payments` и `provider_payments`</li></ol>

Прецедент             | Погашение долга (оплата) - клиент поставщику
:-----:               | :---- 
ID                    | 4 
Краткое описание      | В таблице `client_payments` изменяется запись  
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | О оплате должна быть обязательно заполнена следующая информация: клиент который платит, сумма 
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `id_client`, `sum`</li><li>Перед тем как изменить запис в таблице срабатывает триггер `client_pay`. Данный триггер заполняет недостающие данные</li></ol>    

Прецедент             | Погашение долга (оплата) - поставщик цеху
:-----:               | :---- 
ID                    | 5 
Краткое описание      | В таблице `provider_payments` изменяется запись  
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | О оплате должна быть обязательно заполнена следующая информация: поставщик который платит, сумма 
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `id_client`, `sum`</li><li>Перед тем как изменить запис в таблице срабатывает триггер `client_pay`. Данный триггер заполняет недостающие данные</li></ol>    
    
Прецедент             | Возврат продукта  - клиент поставщику
:-----:               | :---- 
ID                    | 6
Краткое описание      | В таблице `client_return` добавляется запись  
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | О возврате должна быть обязательно заполнена следующая информация: клиент, продукция, количество в кг 
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `_from (client)`, `id_sausage`, `sausages_weight`</li><li>Перед тем как добавить  в таблице срабатывает триггер `return_client`. Данный триггер заполняет недостающие данные</li></ol>    

Прецедент             | Возврат продукта  - клиент поставщику
:-----:               | :---- 
ID                    | 7 
Краткое описание      | В таблице `provider_return` добавляется запись  
Главные акторы        | Администратор базы данных
Второстепенные акторы | нет
Предусловия           | О возврате должна быть обязательно заполнена следующая информация: поставщик, продукция, количество в кг 
Основной поток        | <ol><li>Администратор базы данных вносит информацию об клиенте: `_from (provider)`, `id_sausage`, `sausages_weight`</li><li>Перед тем как добавить  в таблице срабатывает триггер `return_client`. Данный триггер заполняет недостающие данные</li></ol>    


### Функции:
[Код функций](https://github.com/MansurovB-source/CourseProject-ISDB/blob/main/scripts/triggers.sql)

* `get_workers()` - функция позволяет получить всех своих поставщиков.
* `get_clients_for_provider(id_provider integer)` - функция позволяет получить всех клиентов определенного поставщики.
* `get_client_for_factory` - функция позволяет получить всех клиентов определенного цеха.

### Триггеры:
Для сохранения целостности введем 10 триггера. 

[Код триггера](https://github.com/MansurovB-source/CourseProject-ISDB/blob/main/scripts/triggers.sql)

* `inc_DelClientNum` - триггер срабатывающий при добавлении нового клиента. В области в котором принадлежит клиент, автоматически увеличивается количество клиентов. 
* `dec_DelClientNum` - декремент.
* `inc_WorkerNum` - триггер срабатывающий при добавлении нового поставщика. Автоматически увеличивается количество поставщиков в цехе, в котором, этот поставщики будет работать
* `dec_WorkerNum` - декремент.
* `make_Schedule` - триггер срабатывает при попытке сделать заказ. Чтобы сохранить целостность базы данных, перед тем как принять заказ, нужно дополнить запрос (выбрать поставщика и задать время заказа), проверить есть ли данный продукт в складе и добавить заказ в таблицу расписания заказов. 
* `payment` - триггер срабатывает после принятие заказа. Чтобы сохранить целостность базы данных, после того как заказ была принята надо добавить запись в таблицу client_payment, provider_payment. Запись в этих таблицах показывают оплачен ли заказ или нет.       
* `client_pay` - триггер срабатывает когда клиент, будет оплачивать товар он изменяет таблицу client_payments заполняет поля (поставщик, значения переменной "оплачен" будет true и задать время оплаты).
* `provider_pay` - триггер срабатывает когда поставщик, будет возвращать деньги полученные от клиента цеху. Заполняет поля (цех, значения переменной "оплачен" будет true и задать время оплаты).
* `return_client` - триггер срабатывает когда клиент, захочет возвратить товар. Триггер заполняет нужные поля и выбирает поставщика на имя которого будет записан возврат и время возврата.
* `return_provider` - триггер срабатывает когда поставщик, захочет возвратить "возврат" полученный от клиента  

### Анализ использования созданной БД и создания индексов 
Большое количество сценариев использования БД - с использованием объединений таблиц (получить по данному клиенту список его заказов, получить по данному поставщику список его клиентов). Все это будет соединиться через primary и foreign_key с проверкой на равенство. Вводить тут индексы не имеет смысла, так как postgres автоматически создает индексы для ключей и уникальных значений.

При принятии заказа мы в таблице расписаний (order_schedule) ищем по определенным значениям есть ли запись и изменяем эту запись и поэтому имеет смысл добавить индекс. Поиск идет по 3 значениям (id_provider, id_sausage, del_time). Добавим `btree - index`  

* `CREATE INDEX order_schedule_provider_sausage_deliv_time ON order_schedule using hash (id_provider, id_sausage, del_time);`

**ДО:**

                                                QUERY PLAN

  ----------------------------------------------------------------------------------------------------------

   Seq Scan on order_schedule  (cost=0.00..73.72 rows=1 width=24) (actual time=0.017..0.387 rows=1 loops=1)

     Filter: ((id_provider = 32) AND (id_sausage = 18) AND (del_time = '2020-12-05'::date))

     Rows Removed by Filter: 2897

   Planning time: 0.069 ms

   Execution time: 0.404 ms

  (5 rows)

**После:**

                                                                        QUERY PLAN

 ------------------------------------------------------------------------------------------------------------------------------------------------------------

  Index Scan using order_schedule_provider_sausage_deliv_time on order_schedule  (cost=0.28..8.30 rows=1 width=24) (actual time=0.017..0.018 rows=1 loops=1)

    Index Cond: ((id_provider = 32) AND (id_sausage = 18) AND (del_time = '2020-12-05'::date))

  Planning time: 0.095 ms

  Execution time: 0.041 ms

 (4 rows)

**Вывод:**

Можно увидеть что добавления индекса помогло. В запросе используется индекс и время выполнения запросы в порядок меньше чем в запросе без индекса


Будут запросы, чтобы получить возврат. Скорее всего запрос будет таким получить возврат какой-то продукции в какой, то день от какого, то клиента на имя какого-то поставщика. Поиск будет идти по 4 значениям (_from, _to, id_sausage, ret_time). Добавим `btree - index`

* `CREATE INDEX return_client_from_to_sausage_rel_time ON return_provider (_from, _to, id_sausage, ret_time);`

**ДО:**

                                                                               QUERY PLAN

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 Seq Scan on return_client  (cost=0.00..179.62 rows=1 width=32) (actual time=0.696..0.696 rows=0 loops=1)

   Filter: ((_from = 2150) AND (_to = 50) AND (id_sausage = 1) AND (sausages_weight = '5'::double precision) AND (ret_time = '14:33:33.657577'::time without time zone))

   Rows Removed by Filter: 5983

 Planning time: 0.086 ms

 Execution time: 0.713 ms

(5 rows)


**После:**


                                                                      QUERY PLAN

-------------------------------------------------------------------------------------------------------------------------------------------------------

 Index Scan using return_client_from_to_sausage_rel_time on return_client  (cost=0.28..8.31 rows=1 width=32) (actual time=0.020..0.020 rows=0 loops=1)

   Index Cond: ((_from = 2150) AND (_to = 50) AND (id_sausage = 1) AND (ret_time = '14:33:33.657577'::time without time zone))

   Filter: (sausages_weight = '5'::double precision)

   Rows Removed by Filter: 1

 Planning time: 0.110 ms

 Execution time: 0.042 ms

(6 rows)


**Вывод:**

Можно увидеть что добавления индекса помогло. В запросе используется индекс и время выполнения запросы в порядок меньше чем в запросе без индекса

Аналогично как в предыдущем будет запрос для получения возврата от поставщика для какого то цеха

* `CREATE INDEX return_provider_from_to_sausage_rel_time ON return_client (_from, _to, id_sausage, ret_time);`

**ДО:**

                                                        QUERY PLAN

--------------------------------------------------------------------------------------------------------------------------

 Seq Scan on return_provider  (cost=0.00..165.38 rows=1 width=32) (actual time=0.018..0.697 rows=1 loops=1)

   Filter: ((_from = 259) AND (_to = 3) AND (id_sausage = 18) AND (ret_time = '14:34:45.511686'::time without time zone))

   Rows Removed by Filter: 5999

 Planning time: 0.074 ms

 Execution time: 0.715 ms

(5 rows)

**После:**
   
                                                                        QUERY PLAN

-----------------------------------------------------------------------------------------------------------------------------------------------------------

 Index Scan using return_provider_from_to_sausage_rel_time on return_provider  (cost=0.28..8.31 rows=1 width=32) (actual time=0.007..0.008 rows=1 loops=1)

   Index Cond: ((_from = 259) AND (_to = 3) AND (id_sausage = 18) AND (ret_time = '14:34:45.511686'::time without time zone))

 Planning time: 0.063 ms

 Execution time: 0.024 ms

(4 rows)

**Вывод:**

Как видим с индексом лучше.

Две последние индексы будут использоваться когда запрос будет типа: сортировки по столбцам

* ` select * from return_provider order by _from;`
* ` select * from return_client order by _from;`


                                                                          QUERY PLAN
---------------------------------------------------------------------------------------------------------------------------------------------------------------
 Index Scan using return_client_from_to_sausage_rel_time on return_client  (cost=0.28..398.03 rows=5983 width=32) (actual time=0.080..4.483 rows=5983 loops=1)
 Planning time: 0.099 ms
 Execution time: 5.962 ms
(3 rows)

                                                                            QUERY PLAN
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Index Scan using return_provider_from_to_sausage_rel_time on return_provider  (cost=0.28..398.26 rows=6000 width=32) (actual time=0.105..4.734 rows=6000 loops=1)
 Planning time: 0.072 ms
 Execution time: 6.207 ms
(3 rows)


И еще возможно в будущем у нас будет много видов колбасных изделий и много цехов и будет целесообразно добавить такой индекс:

* `CREATE INDEX storages_sausage_factory ON storages (id_factory, id_sausage);`

### Вызуализация фронт-енда на фигме  
[визуализация](https://www.figma.com/file/79DBSSOA5OGTf3hb8MfHZA/BD?node-id=0%3A1)
