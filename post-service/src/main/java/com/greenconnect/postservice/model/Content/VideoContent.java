package com.greenconnect.postservice.model.Content;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VideoContent extends Content {

    private String videoUrl;

    @Override
    public String displayContent() {
        return "<video src='" + this.videoUrl + "' controls></video>";
    }

}

