package com.customclearancecost.entity;

public enum CurrencyOfContract {
    US_DOLLAR("840 Долар США"),
    EUR("978 ЄВРО"),
    POUNDS_STERLING("826 Англійський фунт стерлінг"),
    CANADIAN_DOLLAR("124 Канадський долар"),
    ZLOTY("985 Злотий"),
    SSWISS_FRANC("756 Швейцарський франк"),
    СHESKA_KRONE("203 Чеська крона");

    CurrencyOfContract(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}