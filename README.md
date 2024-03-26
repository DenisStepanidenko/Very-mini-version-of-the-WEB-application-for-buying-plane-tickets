# ***Описание репозитория*** :book:
Здесь будет реализовано WEB-приложение в рамках междисциплинарного проекта "Разработка приложений в среде системы управления базами данных".
# ***Техническое задание***
Здесь будет реализовано WEB-приложение, которое представляет из себя интерфейс для покупки билетов на авиаперелёты. Приложение будет использовать Servlet, JSP, Lombook, PostgreSQL
# ***Техническое описание***



## DAO
## 1) public interface Dao<K, T>
*Данный интерфейс является базовым интерфейсом для всех Dao классов. T - тип сущности, который хранится в БД, K - тип поля id данной сущности.*
### Методы
1) ***List<T> findAll()***  
*Данный метод возвращает список всех сущностей T из БД*  
2) ***Optional<T> findById(K id)***  
Данный метод возвращает сущность по id в БД  
## 2) public class FlightDao implements Dao<Long, Flight>
*Данный Dao класс содержит методы для работы с БД flight. Также реализован паттерн singleton.*  
### Методы
1) ***public List<Flight> findAll()***  
*Данный метод возвращает список всех сущностей flight, которые есть в БД*
2) ***private Flight buildFlight(ResultSet resultSet)***   
*Данный метод из текущего resultSet конструирует объект типа Flight*  
3) ***public Optional<Flight> findById(Long id)***  
*Метод, который по id полёта возвращает всю информацию о нём*  
4) ***public String getDescriptionByFlightId(Long flightId)***  
*Данный метод возвратит описание рейса в виде время вылета,Аэропорт - время прилёта,Аэропорт*  
5) ***public String findCountryByAirportCode(String departureAirportCode)***  
*Метод, который возвратит город по коду аэропорта*  
6) ***public String findAircraftModel(Integer aircraftId)***   
*Метод, который возвращает модель самолёта по id*
## 3) public class TicketDao implements Dao<Long, Ticket>
*Данный Dao класс содержит методы для работы с БД ticket. Также реализован паттерн singleton.*  
### Методы
1) ***public List<Ticket> findAllByFlightId(Long flightId)***  
*Данный метод ищет все билеты по id полёта*
2) ***private Ticket buildTicket(ResultSet resultSet)***  
*Метод, который из текущего resultSet создаёт объект класса Ticket*
3) ***public List<Ticket> findAllByNameOfPerson(String name)***  
*Данный метод ищет все билеты пользователя с именем name (пока что в БД имя уникальное)*
4) ***public void saveTicket(Long id, String seatNo, String name)***
*Метод, который сохраняет билет, купленный пользователем*
5) ***public void deleteTicket(Long flightId, String seatNo, String nameOfPerson)***  
*Данный метод удаляет билет пользователя*
## 4) public class UserDao implements Dao<Integer, User>
*Данный Dao класс содержит методы для работы с БД users. Также реализован паттерн singleton.* 
### Методы
1) ***public Optional<User> findByEmailAndPassword(String email, String password)***  
*Данный метод возвращает Optional<User> по email и password*
2) ***private static User getBuild(ResultSet resultSet)***   
*Данный метод конструирует из текущего объекта ResultSet объект типа User*
3) ***public User save(User entity)***    
*Данный метод сохраняет пользователю в БД*
4) ***public Optional<User> findByEmail(String email)***    
*Данный метод ищет объект User по email*
5)  ***public Optional<User> findByName(String name)***    
*Данный метод ищет объект User по имени*
***
 
## DTO  
## 1) public class CreateUserDto
*Данный класс dto предназначается для создания объекта из регистрации*
## 2) public class FlightDto
*Класс для отображения информации о всех полётах, которые доступны на данный момент*
## 3) public class FlightDtoForFullDescription
*Класс, который используется для показа полного описания рейса на страничке данного рейса*
## 4) public class TicketDto
*Класс для отображения всех билетов на странице рейса. Отображает только номер места*
## 5) public class TicketDtoForPerson
*Класс предназначается для отображение в личной кабинете пользователя информации о его билетах* 
## 6) public class UserDto
*Класс, который используется для показа информации о пользователе*
***
## ENTITY
## 1) public class Flight
*Класс, который соответствует сущности FLight в БД*
## 2) public enum Gender
*Enum, в котором содержатся значения для гендера пользователя*
## 3) public enum Role
*Enum, в котором содержатся значения для ролей пользователей*
## 4) public class Ticket
*Класс, который соответствует сущности Ticket в БД*
## 5) public class User
*Класс, который соответствует сущности User в БД*
***
## EXCEPTION
## 1) public class ValidationException extends RuntimeException
*Данный класс обрабатывает исключения, которые возникают при валидации пароля и логина пользователя*
***
## FILTER
## 1) public class CharsetFilter implements Filter
*Данный фильтр перехватывает запрос и как в request, так и в response выставляет кодировку UTF-8*
## 2) public class AuthorizationFilter implements Filter
*Данный фильтр перехватывает запрос и проверяет - есть ли в Session объект User, иными словами проверяется прошёл ли пользователь аутентификацию или проверяет, что страничка на которой он находится - публичная и доступная всем без аутентификации*
***
## MAPPER
## 1) public interface Mapper<F, T>
*Базовый интерфейс для всех mapper, F - изначальный объект, T - объект, который должны получить на выходе*
## 2) public class CreateUserMapper implements Mapper<CreateUserDto, User>
*Данный класс мапит объект CreateUserDto в объект User для дальнейшего сохранения в БД*
## 3) public class UserMapper implements Mapper<User, UserDto>
*Данный класс для преобразования из User в UserDto для показа информации о пользователе на страничке*
***
## SERVICE
## 1) public class FlightService
*Сервис, который занимается бизнес логикой, связанной с БД с полётами. Реализован паттерн singleton*
### Методы
1) ***public List<FlightDto> findAll()***  
*Метод, который возвращает все описания полётов в краткой форме(дата отправления,код аэропорта - дата прибытия, код аэропорта*
2) ***public FlightDtoForFullDescription findFlightById(Long id)***   
*Метод, который возвращает полное описание полёта. (дата отправления, город отправления, дата прибытия, город прибытия, модель самолёта)*
## 2) public class ImageService
*Сервис, который занимается обработкой фотографией*
### Методы
1)  ***public void upload(String imagePath, InputStream imageContent)***     
*Данный метод сохраняет аватарку пользователя на сервер (в моём случае на диск моего компьютер)*
2) ***public Optional<InputStream> get(String imagePath)***   
*Данный метод возвращает поток байт для фотографии по пути imagePath*  
## 3) public class TicketService  
*Сервис, который занимается бизнес логикой, связанной с БД с билетами*  
### Методы
1) ***public List<TicketDto> findAllByFindFlightId(Long flightId)***   
*Метод, который возвращает описание всех билетов по id полёта*      
2) ***public List<TicketDtoForPerson> findAllByName(String name)***   
*Данный метод должен выдать все билеты пользователя с именем name*   
3) ***public List<TicketDto> getFreeSeatNo(List<TicketDto> boughtTickets, Long flightId)***    
*Возвращает свободные места на рейс с переданным id*     
4) ***private boolean isCheck(String seatNo, List<TicketDto> boughtTickets)***   
*Метод, который проверяет есть ли данное место seatNo среди купленных мест*   
5) ***public void saveTicket(Long id, String seatNo, String name)***   
*Метод, который сохраняет билет в БД*
6) ***public void deleteTicket(Long flightId, String seatNo, String nameOfPerson)***  
*Данный метод удаляет билет из БД*
## 4) public class UserService
*Сервис, который занимается бизнес логикой, связанной с БД с пользователями*
### Методы
1) ***public Optional<UserDto> login(String email, String password)***   
*Данный метод пытается найти пользователя по email и password, и вернуть Optional<UserDto> - для загрузки потом в сессию*
2) ***public Integer create(CreateUserDto userDto)***   
*Данный метод сохраняет в dao пользователя, если все данные валидны, если нет - выбрасывает исключение ValidationException*
***
## SERVLET  
## 1) public class BuyingTicketsServlet extends HttpServlet
*Данный сервлет будет заниматься обработкой покупки билетов*
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод возвращает страничку пользователю, где будет храниться полная информация о рейсе и возможность его купить*
2) ***protected void doPost(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод будет записывать билет пользователя в БД и возвращаться на страничку с личным кабинетом*  
## 2) public class FlightServlet extends HttpServlet
*Данный сервлет будет заниматься обработкой показа всех рейсов пользователю*
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод возвращает страничку пользователю, на которой перечислены все полёты в виде краткого описания*
## 3) public class ImageServlet extends HttpServlet
*Сервлет, который по адресу изображения на компьютере возвращает его на страничку в виде потока байт*
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***   
*Принимает путь до фотографии конкретного пользователя и возвращает картинку, которая хранится по адресу, а адрес хранится в поле пользователя*
2) ***private void writeImage(InputStream image, HttpServletResponse resp)***   
*Метод, который записывает в выходной поток байты фотографии*  
## 4) public class LoginServlet extends HttpServlet
*Данный сервлет занимается обработкой login странички*  
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***  
*Данный метод ловит get запрос на страничку логина, и перенаправляет на jsp страничку*  
2) ***protected void doPost(HttpServletRequest req, HttpServletResponse resp)***     
*Данный метод проверят введённые пользователем данные для входа, и если всё успешно - загружает данного пользователя в Session, если нет - возвращает страничку с логином*  
3) ***private void onLoginFail(HttpServletRequest req, HttpServletResponse resp)***     
*Данный метод в случае ошибки при аутентификации перенаправляет пользователя опять на страничку логина*   
4) ***private void onLoginSucess(UserDto user, HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод в случае успеха перенаправляет пользователя в личный кабинет*
## 5) public class LogoutServlet extends HttpServlet
*Данный сервлет занимается тем, что удаляет текущего user из сессии и возвращает на страничку логина*
### Методы  
1) ***protected void doPost(HttpServletRequest req, HttpServletResponse resp)***  
*Данный метод удаляет пользователя из Session и делает редирект на страничку логина*
## 6) public class PersonalProfileServlet extends HttpServlet
*Сервлет, который обрабатывает личную страничку пользователя*  
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод возвращает страничку пользователя с его данными, взятыми из Session*
## 7) public class RegistrationServlet extends HttpServlet
*Сервлет, который обрабатывает регистрацию пользователей*
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***     
*Данный метод возвращает страничку для регистрации*
2) ***protected void doPost(HttpServletRequest req, HttpServletResponse resp)***   
*Получаем запрос с данными от пользователя и сохраняем их в БД, если они валидны*
## 8) public class TicketServlet extends HttpServlet
*Данный сервлет занимается обработкой билетов на рейсы*  
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод возвращает страничку, где есть описание рейса и всевозможные билеты на него*
## 9) public class DeleteTicket extends HttpServlet
*Данный сервлет предназначен для обработки удаления билетов*  
### Методы
1) ***protected void doGet(HttpServletRequest req, HttpServletResponse resp)***  
*Данный метод будет возвращать страничку пользователю, где будет информация о билете, а также возможность его удалить*
2) ***protected void doPost(HttpServletRequest req, HttpServletResponse resp)***   
*Данный метод удаляет билет пользователя*
***
## UTIL
## 1) public class ConnectionManager
*Данный утилитный класс нужен для выдачи соединения с БД*
### Методы
1) ***private static void loadDriver()***   
*Метод, который загружает драйвер для работы с БД*
2) ***public static Connection get()***   
*Метод, который выдаёт соединение с БД*
## 2) public class JspHelper 
*Данный утилитный класс возвращает полное путь до jsp файла*
### Методы
1) ***public static String getPath(String jspName)***  
*Данный метод возвращает полный путь до файла с именем jspName*
## 3) public class LocalDateFormatter
*Класс для форматирования даты из строки в объект LocalDate*
### Методы
1) ***public LocalDate format(String date)***  
*Метод, который по строке выдаёт объект LocalDate*
2) ***public boolean isValid(String date)***  
*Метод, который проверяет правильную валидацию даты*
## 4) public class PropertiesUtil
*Данный утилитный класс возвращает значения из файла application.properties для входа в БД*
### Методы
1) ***private static void loadProperties()***   
*Данный метод загружает в объект типа Properties файл application.properties*
2) ***public static String get(String key)***   
*Возвращает значение из Properties по ключу key*
## 5) public class UrlPath
*Данный утилитный класс хранит в себе поля в виде строк, которые доступны пользователю, который не прошёл аутентификацию(публичные страницы)*
***
## VALIDATOR
## 1) public interface Validator<T>
*Базовый интерфейс для валидаторов, тип T - тип, который мы валидурем*
### Методы
1) ***ValidationResult isValid(T object)***  
*Данный метод валидирует объект T*
## 2) public class ValidationResult
*Класс, где хранится список ошибок, которые возникают при валидации*
### Методы 
1) ***public void add(Error error)***  
*Метод, который добавляет новую ошибку в список*
2) ***public boolean isValid()***    
*Метод, который по факту валидирует, то есть проверяет - пуст ли список ошибок*
## 3) public enum PossibleFormatForImage
*В данном Enum хранятся возможные расширения для аватарок*
## 4) public class Error 
*Класс с ошибкой, где хранится описание ошибки и код ошибки*
## 5) public class CreateUserValidator implements Validator<CreateUserDto>
*Класс для валидации CreateUserDto*
### Методы
1) ***public ValidationResult isValid(CreateUserDto object)***  
*Данный метод валидирует данные с формы для регистрации и возвращает объект ValidationResult, где есть List с ошибками*
2) ***private boolean isFileNameOfPhotoValid(String fileName)***   
*Данный метод смотрит на расширение файла и сверяет, чтобы оно было валидным для нас*
3) ***private boolean isInEnum(String value)***   
*Данный метод проверяет - доступный ли формат для загрузки аватарки*
***
## Базы данных
В проекте использованы 5 различных таблиц
## Flight
```sql
-- flight (рейс)
--        id (номер рейса не уникальный, поэтому нужен id)
--  flight_no (номер рейса)
--  departure_date (дата вылета)
--  departure_airport_code (аэропорт вылета)
--  arrival_date (дата прибытия)
--  arrival_airport_code (аэропорт прибытия)
--  aircraft_id (самолет)
CREATE TABLE flight
(
    id                     BIGSERIAL PRIMARY KEY,
    flight_no              VARCHAR(16)                       NOT NULL,
    departure_date         TIMESTAMP                         NOT NULL,
    departure_airport_code CHAR(3) REFERENCES airport (code) NOT NULL,
    arrival_date           TIMESTAMP                         NOT NULL,
    arrival_airport_code   CHAR(3) REFERENCES airport (code) NOT NULL,
    aircraft_id            INT REFERENCES aircraft (id)      NOT NULL,
);
```
## Ticket
```sql
-- ticket (билет на самолет)
--        id
--  	passenger_name (имя и фамилия пассажира)
--  	flight_id (рейс)
--  	seat_no (номер места в самолете – flight_id + seat-no - unique)
CREATE TABLE ticket
(
    id             BIGSERIAL PRIMARY KEY,
    passenger_name VARCHAR(128)                  NOT NULL,
    flight_id      BIGINT REFERENCES flight (id) NOT NULL,
    seat_no        VARCHAR(4)                    NOT NULL,
);
```
## Airport
```sql
-- airport (аэропорт)
--         code (уникальный код аэропорта)
--              country (страна)
--                      city (город)
CREATE TABLE airport
(
    code    CHAR(3) PRIMARY KEY,
    country VARCHAR(256) NOT NULL,
    city    VARCHAR(128) NOT NULL
);
```
## Aircraft
```sql
-- aircraft (самолет)
--          id
--  	model (модель самолета - unique)
CREATE TABLE aircraft
(
    id    SERIAL PRIMARY KEY,
    model VARCHAR(128) NOT NULL
);
```

## Users
```sql
create table users
(
    id       serial primary key,
    name     varchar(124)  not null,
    birthday date          not null,
    email    varchar(124)  not null unique,
    image    varchar(1024) not null,
    password varchar(32)   not null,
    role     varchar(32)   not null,
    gender   varchar(16)   not null
);
```
***
<br/>  

# Демонстрация работы WEB-приложения
*Стартовая страничка начинается с аутентификации пользователя по почте и паролю.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/89c554fe-6a9c-4065-b4a4-b6882d0576f3)
<br/>  
*Если пользователь не зарегистрирован, то можно нажать на ссылочку регистрации и перейти к ней.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/97c436cf-2363-43d9-86bd-f6adad6db917)
<br/>  

*В личном кабинете можно посмотреть аватарку, основную информацию о человеке, а также список купленных билетов и кнопочки для перехода к билетам и выхода из аккаунта.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/434d5174-9181-4733-8ef6-af1cbb96ef47)
<br/>  

*Также если нажать на билет в личном кабинете, то появится страничка, где можно сдать этот билет.* <br/> 
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/b285f7ef-03cc-4cfe-b78d-64ba0d76e720)  
<br/>

*Список всех рейсов выглядит таким образом. Каждый рейс кликабельный, то есть по нажатию на него можно перейти к описанию данного рейса и покупки билетов.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/8e274043-ce13-48e1-a019-485e8a8fac97)
<br/>  

*Описание каждого рейса состоит из даты отправления, города отправления, даты прибытия, города прибытия и модели самолёта. Также здесь собрана информация по занятым и свободным местам. Чтобы купить билет, нужно лишь нажать на свободное место.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/2991a497-08b3-4262-bb62-d8ed9848dd04)
<br/>    

*Если нажать на свободное место, то пользователь попадает на страничку, где также имеется описание рейса, и кнопочка купить билет. По нажатию этой кнопочки - пользователь попадет в свой личный кабинет, где у него уже обновляется информация по купленным билетам.* <br/>  
![image](https://github.com/DenisStepanidenko/Very-mini-version-of-the-WEB-application-for-buying-plane-tickets/assets/110686828/c70d8e52-d768-4469-81ab-82b15ec874a4)












