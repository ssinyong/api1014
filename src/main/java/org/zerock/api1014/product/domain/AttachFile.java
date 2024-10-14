package org.zerock.api1014.product.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@ToString
@AllArgsConstructor
@Getter
public class AttachFile {

    private int ord;
    private String fileName;

}
