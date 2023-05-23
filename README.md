## Приложение для расчета среднего курса валют
### Приложение предоставляет следующие эндпоинты:
1) http://localhost:8087/api/fetch-currencies - запрашивает список имеющихся валют в API НБ РБ с их идентификаторами и прочей информацией.
   
   Возможные параметры:
   - **code** - код валюты (аббревиация) для фильтрации результата. Параметр необязателен.
   
   Пример запроса:
   - http://localhost:8087/api/fetch-currencies?code=USD
   
   Примечания:
   - В разные моменты времени API НБ РБ могло использовать разные идентификаторы для одной и той же валюты, поэтому на запрос может возвращаться более одной сущности даже на одну валюту.


2) http://localhost:8087/api/fetch-rates/CURRENCY_ID - где CURRENCY_ID - идентификатор необходимой валюты (см. пункт 1). Получает курсы через API НБ РБ и добавляет их в локальную базу данных. В качестве ответа выдает JSON с добавленными в базу данных сущностями.

   Обязательные параметры:
   - **startdate** - дата начала запрашиваемого периода 
   - **enddate** - дата окончания запрашиваемого периода

   Пример запроса:

   - http://localhost:8087/api/fetch-rates/451?startdate=2023-01-01&enddate=2023-04-01 - запрашивает курсы Евро за период начиная с 1 января 2023 (включительно) по 1 апреля 2023 (включительно).


3) http://localhost:8087/api/currencies - выдает JSON со всеми типами валют которые есть в базе данных.
   
   Возможные параметры:
   - **code** - код валюты (аббревиация) для фильтрации результата. Параметр необязателен.

   Пример запроса:
   - http://localhost:8087/api/currencies?code=USD


4) http://localhost:8087/api/rates/CURRENCY_ID - где CURRENCY_ID - идентификатор необходимой валюты (см. пункт 3). Выдает JSON со всеми курсами которые есть в базе данных для заданного типа валюты.

   Пример запроса:

   - http://localhost:8087/api/rates/431 - возвращает все имеющиеся в базе данных курсы для доллара США.


5) http://localhost:8087/api/average-rates/CURRENCY_ID - где CURRENCY_ID - идентификатор необходимой валюты (см. пункт 3). Рассчитывает средний курс за месяц по **имеющимся** в базе данных значениям курсов. В качестве ответа выдается JSON со средним курсом и датой указывающей на первый день запрашиваемого месяца и года.

   Обязательные параметры:
   - **year** - необходимый год
   - **month** - порядковый номер необходимого месяца в году

   Пример запроса:
   - http://localhost:8087/api/average-rates/451?year=2023&month=1 - Рассчитывает средний курс Евро за январь 2023 года.
   
   Примечания:
   - Для правильного расчета среднего курса в базе данных должны быть полные данные за требуемый месяц, то есть добавлены с помощью эндпоинта из пункта 2.

### Требования к приложению:
<details>
<summary>Раскрыть</summary>

### Введение:
Проект должен быть реализован с использованием паттерна MVC на основе предоставленного шаблона. Допускается изменять структуру шаблона, рефакторить и оптимизировать код.

Приложение должно иметь адреса запроса (описанных в функциональных требованиях), при обращении на которые будет выдавать JSON ответ с требуемой информацией.

Шаблон проекта использует уже настроенную H2 базу данных, рекомендуется использовать ее для хранения и взаимодействия с данными.

Приложение будет работать с курсами валют, которые публикует [НБ РБ](https://www.nbrb.by/statistics/rates/ratesdaily). 
Предполагается работа с курсами валют в диапазоне с 01.12.2022 по 31.05.2023. Приложение должно уметь: получать курсы через [API НБ РБ](https://www.nbrb.by/apihelp/exrates); выводить полученные курсы; рассчитывать средний курс за месяц.
### Функциональные требования:
1. Добавить ежедневные курсы валют за заданный период в БД. Пользователь указывает тип валюты (например USD), начальную и конечную дату периода. В качестве ответа получает массив курсов валют, которые были добавлены в БД.
   - Опционально: проверка на корректность дат;
   - Опционально: проверка есть ли уже в БД ежедневные курсы валют за выбранный период;
   - Опционально: проверка на корректность типа валюты.
3. Вывести все курсы по заданному типу валюты, имеющиеся в базе данных. Пользователь указывает тип валюты. В качестве ответа получает список курсов валют имеющихся в базе данных.
   - Опционально: проверка на корректность типа валюты.
4. Рассчитать средний курс за месяц. Пользователь указывает тип валюты и порядковый номер месяца. В качестве ответа получает рассчитанный средний курс за месяц.
      Средний курс = среднегеометрическое от ежедневных курсов за заданный месяц исключая выходные дни. Данная методология применяется Национальным Банком (подробнее ознакомиться с ней Вы сможете на сайте НБ РБ).

      Например: если в декабре было бы всего 4 дня (см. табл. 1), то средний курс составил бы 2,43332495337466
     
**табл. 1**

| Дата | Тип валюты | Курс | Выходной |
|:----------:|:----------:|:-------------:|:-----------------:|
| 01.12.2022 | USD | 2,4325 | Нет|
| 02.12.2022 | USD | 2,4336 |Нет|
| 03.12.2022 | USD | 2,4336 |Да |
| 04.12.2022 | USD | 2,4336 |Да |

Календарь выходных дней представлен в базе данных в таблице [WEEKENDS](https://github.com/amelenas/rpa_internship_2023/blob/master/web/src/main/resources/data.sql). Таблица заполняется при помощи файла “web/src/main/resources/data.sql”
 - Опционально: проверка месяца для расчета курса
 - Опционально: проверка на корректность типа валюты
 - Опционально: добавить возможность выбора года

### Результат:
Срок сдачи: **23.05.2023 12:00**

При возникновении недопониманий по бизнес-логике или реализации допускается интерпретировать эти спорные моменты на ваше усмотрение.

Если Вы будете делать свои допущения или интерпретации, опишите их в письме или комментарием в коде, чтобы мы могли учесть их при проверке.

Представление результата: выслать письмо на почту **dubrovskaya@fin.by** со ссылкой на ваш **публичный** репозиторий с решенным тестовым заданием.
-  В репозитории с решением должен быть рабочий проект, который можно запустить после клонирования.
- В репозитории не должно быть лишних файлов, не задействованных в проекте.
- Допускается сдача неполного решения. Например, реализован 1 из трёх адресов запроса.

</details>
