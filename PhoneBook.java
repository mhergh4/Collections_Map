package practice;

import java.util.*;

public class PhoneBook {
    String regexOfPhoneNumber = "[0-9]+";
    String regexOfName = "[А-Я]" + "[а-я]+";
    HashMap<String , TreeSet<String> > contacts = new HashMap<>();

    public boolean checkPhoneNumber(String number) {
        return number.matches(regexOfPhoneNumber);
    }
    public  boolean checkName(String name) {
        return name.matches(regexOfName);
    }
    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if(checkPhoneNumber(phone) && checkName(name)) {
            boolean havePhoneNumber = true;
            for (Map.Entry<String, TreeSet<String>> entry : contacts.entrySet()){
                if(entry.getValue().contains(phone) ) {
                    TreeSet<String> numbers = new TreeSet<>();
                    numbers.addAll(entry.getValue());
                    contacts.remove(entry.getKey());
                    contacts.put(name, numbers);
                    havePhoneNumber = false;
                }
                if(entry.getKey().contains(name)) {
                    TreeSet<String> numbers = new TreeSet<>();
                    numbers.addAll(entry.getValue());
                    numbers.add(phone);
                    contacts.remove(entry.getKey());
                    contacts.put(name, numbers);
                    havePhoneNumber = false;
                }
            }
            if(havePhoneNumber) {
                TreeSet<String> phoneNumbers = new TreeSet<>();
                phoneNumbers.add(phone);
                HashMap<String, TreeSet<String>> newContacts = new HashMap<>(contacts);
                contacts.clear();
                contacts.put(name, phoneNumbers);
                contacts.putAll(newContacts);
                }



        }
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        for (Map.Entry<String, TreeSet<String>> entry : contacts.entrySet()) {
            if(entry.getValue().contains(phone)) {
                return entry.getKey() + " - " + phone;
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        for (Map.Entry<String, TreeSet<String>> entry : contacts.entrySet()) {
            if(entry.getKey().equals(name)) {
                String value = String.valueOf(entry.getValue());
                value = value.substring(1,value.length() - 1);
                return Collections.singleton(name + " - " + value);
            }
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        if(contacts.isEmpty()) {
            return new TreeSet<>();
        } else {
            StringBuilder allContacts = new StringBuilder();
            for (Map.Entry<String, TreeSet<String>> entry : contacts.entrySet()) {
                String value = String.valueOf(entry.getValue());
                value = value.substring(1,value.length() - 1);
                allContacts.append(entry.getKey()).append(" - ").append(value).append(", ");
            }
            allContacts = new StringBuilder(allContacts.substring(0, allContacts.length() - 2));
           return Collections.singleton(allContacts.toString());
        }
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}