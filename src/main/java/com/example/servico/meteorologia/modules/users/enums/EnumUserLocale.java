package com.example.servico.meteorologia.modules.users.enums;

public enum EnumUserLocale {
    CRICIUMA("Criciúma"),
    RIO_DE_JANEIRO("Rio de janeiro"),
    SAO_PAULO("São paulo"),
    SALVADOR("Salvador");

    private String name;

    EnumUserLocale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumUserLocale getByName(String name) {
        for (EnumUserLocale locale: EnumUserLocale.values()) {
            if (locale.getName().contains((name))) {
                return locale;
            }
        }
        throw new IllegalArgumentException(String.format("Localização %s, não encontrada!", name));
    }
}
