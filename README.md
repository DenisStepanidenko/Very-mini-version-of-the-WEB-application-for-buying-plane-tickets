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






