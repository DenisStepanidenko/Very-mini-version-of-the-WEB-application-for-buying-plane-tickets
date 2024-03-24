# ***Описание репозитория*** :book:
Здесь будет реализовано WEB-приложение в рамках междисциплинарного проекта "Разработка приложений в среде системы управления базами данных".
# ***Техническое задание***
Здесь будет реализовано WEB-приложение, которое представляет из себя интерфейс для покупки билетов на авиаперелёты. Приложение будет использовать Servlet, JSP, Lombook, PostgreSQL
# ***Техническое описание***



## DAO
## 1) public interface Dao<K, T>
*Данный интерфейс является базовым интерфейсом для всех Dao классов. T - тип сущности, который хранится в БД, K - тип поля id данной сущности.*
## Методы
1) ***List<T> findAll()***  
*Данный метод возвращает список всех сущностей T из БД*  
2) ***Optional<T> findById(K id)***  
Данный метод возвращает сущность по id в БД  
***
## 2) public class FlightDao implements Dao<Long, Flight>
*Данный Dao класс содержит методы для работы с БД flight. Также реализован паттерн singleton.*  
## Методы
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
***
## 3) public class TicketDao implements Dao<Long, Ticket>
*Данный Dao класс содержит методы для работы с БД ticket. Также реализован паттерн singleton.*  
## Методы
1) ***public List<Ticket> findAllByFlightId(Long flightId)***  
*Данный метод ищет все билеты по id полёта*
2) ***private Ticket buildTicket(ResultSet resultSet)***  
*Метод, который из текущего resultSet создаёт объект класса Ticket*
3) ***public List<Ticket> findAllByNameOfPerson(String name)***  
*Данный метод ищет все билеты пользователя с именем name (пока что в БД имя уникальное)*
4) ***public void saveTicket(Long id, String seatNo, String name)***
*Метод, который сохраняет билет, купленный пользователем*
***
## 4) public class UserDao implements Dao<Integer, User>
*Данный Dao класс содержит методы для работы с БД users. Также реализован паттерн singleton.* 
## Методы
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



