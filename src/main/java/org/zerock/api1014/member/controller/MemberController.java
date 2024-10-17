package org.zerock.api1014.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zerock.api1014.member.dto.MemberDTO;
import org.zerock.api1014.member.dto.TokenRequestDTO;
import org.zerock.api1014.member.dto.TokenResponseDTO;
import org.zerock.api1014.member.service.MemberService;
import org.zerock.api1014.security.util.JWTUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final JWTUtil jwtUtil;

    @Value("${org.zerock.accessTime}")
    private int accessTime;

    @Value("${org.zerock.refreshTime}")
    private int refreshTime;

    @PostMapping("makeToken")
    public ResponseEntity<TokenResponseDTO> makeToken(@RequestBody @Validated TokenRequestDTO tokenRequestDTO) {

       log.info("Making token");
       log.info("------------------------");

        MemberDTO memberDTO = memberService.authenticate(
                tokenRequestDTO.getEmail(),
                tokenRequestDTO.getPw());

        log.info(memberDTO);
        Map<String, Object> claimMap = Map.of(
                "email", memberDTO.getEmail(),
                "role", memberDTO.getRole() );

        String accessToken = jwtUtil.createToken(claimMap,accessTime);
        String refreshToken = jwtUtil.createToken(claimMap,refreshTime);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);
        tokenResponseDTO.setEmail(memberDTO.getEmail());


        return ResponseEntity.ok(tokenResponseDTO);
    }

    @PostMapping(value = "refreshToken",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity<TokenResponseDTO> refreshToken(
            @RequestHeader("Authorization") String accessToken,
            String refreshToken) {

        //만일 accessToken이 없다면 혹은 refreshToken이 없다면

        //accessToken Bearer (7) 잘라낼 때 문제가 발생한다면

        return null;
    }
}
