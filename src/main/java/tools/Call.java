package tools;

import java.sql.Timestamp;

public class Call {

    private String phoneNumber;
    private Timestamp callDate;

    /**
     * Объект-звонок
     * @param phoneNumber Номер телефона, с которого пришел звонок
     * @param callDate Дата-время звонка
     */
    public Call(String phoneNumber, Timestamp callDate) {
        this.phoneNumber = phoneNumber;
        this.callDate = callDate;
    }

    /**
     * Получение номера телефона, с которого пришел звонок
     * @return Номер телефона
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Получение даты звонка
     * @return Дата звонка
     */
    public Timestamp getCallDate() {
        return callDate;
    }

}
