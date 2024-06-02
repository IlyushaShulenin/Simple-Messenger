package ru.shulenin.simple_messanger.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ACTIVE, NOT_ACTIVE;

    @Override
    public String getAuthority() {
        return name();
    }
}
