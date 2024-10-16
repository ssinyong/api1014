package org.zerock.api1014.loader;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.zerock.api1014.member.repository.MemberRepository;
@Component
@Log4j2
@RequiredArgsConstructor
@Order(1)
public class MemberLoader implements CommandLineRunner {
    private final MemberRepository memberRepository;
    @Override
    public void run(String... args) throws Exception {
        log.info("member loader.................");
        log.info("member loader.................");
        log.info("member loader.................");
        log.info("member loader.................");
    }
}