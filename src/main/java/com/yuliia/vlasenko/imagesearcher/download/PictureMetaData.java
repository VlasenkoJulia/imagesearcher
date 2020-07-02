package com.yuliia.vlasenko.imagesearcher.download;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PictureMetaData {
    private String id;
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
    @JsonProperty("full_picture")
    private String fullPicture;
}
