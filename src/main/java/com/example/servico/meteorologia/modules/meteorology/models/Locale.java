package com.example.servico.meteorologia.modules.meteorology.models;

import com.example.servico.meteorologia.modules.users.enums.EnumUserLocale;

public record Locale(
        Float latitude,
        Float longitude
) {
    public static Locale toEntityLocale(Float latitude, Float longitude) {
        return new Locale(latitude, longitude);
    }

    public static Locale userLocaleToLocale(EnumUserLocale userLocale) {
        // essas latitudes e longitudes eu peguei no https://open-meteo.com/en/docs
        return switch (userLocale) {
            case CRICIUMA -> toEntityLocale(-28.6775f, -49.3697f);
            case RIO_DE_JANEIRO -> toEntityLocale(-22.9064f, -43.1822f);
            case SAO_PAULO -> toEntityLocale(-23.5475f, -46.6361f);
            case SALVADOR -> toEntityLocale(-12.9711f, -38.5108f);
            default -> toEntityLocale(-52.52f, -13.41f); // padrão que vem no https://open-meteo.com/en/docs
        };
    }
}
