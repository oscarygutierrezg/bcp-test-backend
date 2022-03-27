package com.bcp.test.model.enums;

public enum Operation {
	INSERT, UPDATE;
    private final String value;

    Operation() {
        value = toString();
    }

    public String getValue() {
        return value;
    }

    public static Operation parse(final String value) {
    	Operation operation = null;
        for (final Operation op : Operation.values()) {
            if (op.getValue().equals(value)) {
                operation = op;
                break;
            }
        }
        return operation;
    }
}
