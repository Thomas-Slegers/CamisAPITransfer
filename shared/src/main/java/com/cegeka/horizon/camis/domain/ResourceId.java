package com.cegeka.horizon.camis.domain;

public record ResourceId(String value) {
    private static String CAMIS_COMPLETED = "-9999999";

    public ResourceId {
        if (!isValid(value)) throw new IllegalArgumentException("Invalid value for ResourceId : " + value);
    }

    private boolean isValid(String value) {
        return value.matches("I?\\d{6}") || value.matches("\\d{7}") || value.matches(CAMIS_COMPLETED);
    }

    public boolean isExternal() {
        return value.startsWith("I");
    }

    @Override
    public String toString() {
        return value;
    }
}
