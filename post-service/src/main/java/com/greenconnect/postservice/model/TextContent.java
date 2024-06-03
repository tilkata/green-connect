package com.greenconnect.postservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TextContent extends Content {

    private String text;

    @Override
    public String displayContent() {
        return this.text;
    }
}
