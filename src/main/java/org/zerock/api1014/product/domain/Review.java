package org.zerock.api1014.product.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "product")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reviewText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
