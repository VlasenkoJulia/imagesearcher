package com.yuliia.vlasenko.imagesearcher.repository.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "picture_metadata")
public class PictureMetaDataEntity {
    @Id
    private String id;
    private String author;
    private String camera;
    private String tags;
    @Column(name = "cropped_picture")
    private String croppedPicture;
    @Column(name = "full_picture")
    private String fullPicture;
    @Column(name = "search_terms")
    private String searchTerms;
}
