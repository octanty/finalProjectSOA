package org.acme.getting.started;

public enum Currency {

    USD, EUR, RUB;

    public static Currency getDefault() {
        return USD;
    }
}
