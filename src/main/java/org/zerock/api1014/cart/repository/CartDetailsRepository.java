package org.zerock.api1014.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.api1014.cart.domain.CartDetails;
import org.zerock.api1014.cart.dto.CartDetailsListDTO;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {

    @Query("""
        SELECT 
            new org.zerock.api1014.cart.dto.CartDetailsListDTO(
            p.pno, p.name, p.price, count(r), attach.filename,
            cd.qty
            )
            
        FROM 
            MemberEntity m 
            left join Cart c ON c.member = m
            left join CartDetails cd ON cd.cart = c
            join Product p ON p = cd.product
            left join p.attachFiles attach
            left join Review r ON r.product = p
         where m.email = :email
         and attach.ord = 0
         group by p
    """)
    Page<CartDetailsListDTO[]> listOfMember(@Param("email") String email, Pageable pageable);
}
