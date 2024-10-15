package org.zerock.api1014.member.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.api1014.member.dto.TokenRequestDTO;
import org.zerock.api1014.member.dto.TokenResponseDTO;

@RestController
@RequestMapping("/api/v1/member")
@Log4j2
public class MemberController {

    @PostMapping("makeToken")
    public ResponseEntity<TokenResponseDTO> makeToken(@RequestBody @Validated TokenRequestDTO tokenRequestDTO) {

       log.info("Making token");
       log.info("------------------------");

        return null;
    }
}
