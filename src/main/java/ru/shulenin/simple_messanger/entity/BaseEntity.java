package ru.shulenin.simple_messanger.entity;

public interface BaseEntity<T> {
    T getId();
    void setId(T id);
}
