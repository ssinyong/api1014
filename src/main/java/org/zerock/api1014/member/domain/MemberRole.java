package org.zerock.api1014.member.domain;

public enum MemberRole {

    USER("USEER"),ADMIN("ADMIN");

    String role;

    MemberRole(String role) {
        this.role = role;
    }
}
