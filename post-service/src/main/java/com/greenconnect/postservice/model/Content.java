package com.greenconnect.postservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String blobPath;
    private String fileName;
    @Transient
    private InputStream inputStream;
    private boolean useBlobStorage;

    public abstract String displayContent();

}
