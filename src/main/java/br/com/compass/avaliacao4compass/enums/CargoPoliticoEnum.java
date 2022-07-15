package br.com.compass.avaliacao4compass.enums;

import lombok.Data;

public enum CargoPoliticoEnum {
    VEREADOR ("Vereador"),
    PREFEITO ("Prefeito"),
    DEPUTADO_ESTADUAL ("Deputado Estadual"),
    DEPUTADO_FEDERAL ("Deputado Federal"),
    SENADOR ("Senador"),
    GOVERNADOR ("Governador"),
    PRESIDENTE ("Presidente"),
    NENHUM ("Nenhum");

    private String value;
    CargoPoliticoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
