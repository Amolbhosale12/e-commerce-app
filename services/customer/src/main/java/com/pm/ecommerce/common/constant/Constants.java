package com.pm.ecommerce.common.constant;

import lombok.Getter;

@Getter
public enum Constants {

    LOGIN_SUCCESS("Login Success"),
    LOGIN_FAIL("Login Fail"),
    CUSTOMER_NOT_FOUND("Customer Not Found"),
    CUSTOMER_ALREADY_EXIST("Customer Already Exist"),
    CUSTOMER_CREATED("Customer Created"),
    CUSTOMER_UPDATED("Customer Updated"),
    CUSTOMER_DELETED("Customer Deleted"),
    EMAIL_ALREADY_EXIST("Email Already Exist"),
    FETCH_ALL_CUSTOMERS_SUCCESSFULLY("Fetch All Customers Successfully"),
    CUSTOMER_FETCH_SUCCESSFULLY("Customer Fetch Successfully"),
    ;

    final String value;

    Constants(String value) {
        this.value = value;
    }
}
