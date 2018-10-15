package com.karlespe.pageview.model.event;

/**
 * Enum representing different types of events. Enum properties are used
 * by `EventDao` for properly building update statements.
 */

public enum EventType {

    READ ("Read", "#r", "readValue"),
    DURATION ("Duration", "#d", "durationValue"),
    INFORMATIVE ("Informative", "#i", "informativeValue");

    private final String columnName;
    private final String columnKey;
    private final String valueKey;

    EventType (String columnName, String columnKey, String valueKey) {
        this.columnName = columnName;
        this.columnKey = columnKey;
        this.valueKey = valueKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public String getValueKey() {
        return valueKey;
    }

    public String getSetStatement() { return getColumnKey() + " = " + getColumnKey() + " + :" + getValueKey(); }

}
