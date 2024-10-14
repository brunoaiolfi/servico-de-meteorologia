package com.example.servico.meteorologia.modules.users.enums;

public enum EnumLocale {
    CRICIUMA("Criciúma"),
    RIO_DE_JANEIRO("Rio de janeiro"),
    SAO_PAULO("São paulo"),
    SALVADOR("Salvador");

    private String name;

    EnumLocale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumLocale getByName(String name) {
        for (EnumLocale locale: EnumLocale.values()) {
            if (locale.getName().contains((name))) {
                return locale;
            }
        }
        throw new IllegalArgumentException(String.format("Localização %s, não encontrada!", name));
    }
}
