package com.yuliia.vlasenko.imagesearcher.repository;

import com.yuliia.vlasenko.imagesearcher.api.PictureMetaData;
import com.yuliia.vlasenko.imagesearcher.repository.entities.PictureMetaDataEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class PictureMetaDataConverter {
    public List<PictureMetaDataEntity> toEntities(List<PictureMetaData> pictureMetaData) {
        return pictureMetaData.stream().map(this::toEntity).collect(toList());
    }

    public List<PictureMetaData> fromEntities(List<PictureMetaDataEntity> entities) {
        return entities.stream().map(this::fromEntity).collect(toList());
    }

    private PictureMetaData fromEntity(PictureMetaDataEntity entity) {
        PictureMetaData pictureMetaData = new PictureMetaData();
        pictureMetaData.setId(entity.getId());
        pictureMetaData.setAuthor(entity.getAuthor());
        pictureMetaData.setCamera(entity.getCamera());
        pictureMetaData.setTags(entity.getTags());
        pictureMetaData.setCroppedPicture(entity.getCroppedPicture());
        pictureMetaData.setFullPicture(entity.getFullPicture());
        return pictureMetaData;
    }

    private PictureMetaDataEntity toEntity(PictureMetaData pictureMetaData) {
        PictureMetaDataEntity entity = new PictureMetaDataEntity();
        entity.setId(pictureMetaData.getId());
        entity.setAuthor(pictureMetaData.getAuthor());
        entity.setCamera(pictureMetaData.getCamera());
        entity.setTags(pictureMetaData.getTags());
        entity.setCroppedPicture(pictureMetaData.getCroppedPicture());
        entity.setFullPicture(pictureMetaData.getFullPicture());
        entity.setSearchTerms(getSearchTerms(pictureMetaData));
        return entity;
    }

    private String getSearchTerms(PictureMetaData pictureMetaData) {
        return (pictureMetaData.getAuthor() +
                " " +
                pictureMetaData.getCamera() +
                " " +
                pictureMetaData.getTags()).toLowerCase();
    }
}
