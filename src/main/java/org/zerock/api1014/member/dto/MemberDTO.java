package org.zerock.api1014.member.dto;

import lombok.Data;
import org.zerock.api1014.member.domain.MemberRole;

@Data
public class MemberDTO {

    private String email;

    private String pw;

    private String role;
}
