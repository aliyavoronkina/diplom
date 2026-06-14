package ru.iteco.fmhandroid.data;

public class TestData {

    // Позитивные тесты
    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";

    // Негативные тесты (должны падать)
    public static final String INVALID_LOGIN = "wrong_user";
    public static final String INVALID_PASSWORD = "wrong_password";

    // Ожидаемые сообщения
    public static final String ERROR_MESSAGE = "Wrong login or password";

    // Данные для новости
    public static final String NEWS_TITLE = "Test News";
    public static final String NEWS_DESCRIPTION = "Test Description";
}