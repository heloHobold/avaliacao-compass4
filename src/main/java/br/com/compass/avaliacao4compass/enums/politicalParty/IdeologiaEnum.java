package br.com.compass.avaliacao4compass.enums.politicalParty;

public enum IdeologiaEnum {
    DIREITA ("Direita"),
    CENTRO ("Centro"),
    ESQUERDA ("Esquerda");

    private String value;
    IdeologiaEnum(String value) {
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
