package org.zerock.api1014.product.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"attachFiles","tags"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String name;

    private int price;

    @ElementCollection
    private Set<AttachFile> attachFiles = new HashSet<>();

    @ElementCollection
    private Set<String> tags = new HashSet<>();


    public void addFile(String filename) {
        attachFiles.add(new AttachFile(attachFiles.size(),filename));

    }

    public void clearFiles(){
        attachFiles.clear();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void clearTags(){
        tags.clear();
    }

}
