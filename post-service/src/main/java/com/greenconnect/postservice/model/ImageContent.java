package com.greenconnect.postservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ImageContent extends Content {

    private String imageUrl;

    @Override
    public String displayContent() {
        return "<img src='" + this.imageUrl + "' alt='Image Content'>";
    }

}
