package dao;

import entity.Gender;
import entity.Role;
import entity.User;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String SAV_SQL = "INSERT INTO users(name, birthday, email, password, role, gender ,image) values (?,?,?,?,?,?,?)";

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM users WHERE email = ? and password = ?";
    private static final String GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String GET_BY_NAME = "SELECT * FROM users WHERE name = ?";

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


    /**
     * Данный метод возвращает Optional<User> по email и password
     */
    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getBuild(resultSet);

            }
            return Optional.ofNullable(user);
        }

    }

    /**
     * Данный метод конструирует из текущего объекта ResultSet объект типа User
     */
    @SneakyThrows
    private static User getBuild(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .image(resultSet.getObject("image", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }


    /**
     * Данный метод сохраняет пользователю в БД
     */
    @Override
    @SneakyThrows
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAV_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());
            preparedStatement.setObject(7, entity.getImage());
            preparedStatement.executeUpdate();


            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
        return null;
    }


    /**
     * Данный метод ищет объект User по email
     */
    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getBuild(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }


    /**
     * Данный метод ищет объекта User по имени
     */
    @SneakyThrows
    public Optional<User> findByName(String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = getBuild(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }
}
