package com.bav.ordermanagementsystem.db.annotations;

public enum GenerationType {
    TABLE,
    SEQUENCE,
    IDENTITY,
    UUID,
    AUTO;

    private GenerationType() {
    }
}
