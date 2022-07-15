package br.com.compass.avaliacao4compass.enums.associate;

public enum SexoEnum {
    MASCULINO ("Masculino"),
    FEMININO ("Feminino");

    private String value;

    SexoEnum(String value) {
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
