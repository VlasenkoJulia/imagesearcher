package com.yuliia.vlasenko.imagesearcher.repository;

import com.yuliia.vlasenko.imagesearcher.api.PictureMetaData;
import com.yuliia.vlasenko.imagesearcher.repository.entities.PictureMetaDataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PictureMetaDataRepository {
    private final PictureMetadataJpaRepository jpaRepository;
    private final PictureMetaDataConverter converter;


    public PictureMetaDataRepository(PictureMetadataJpaRepository jpaRepository, PictureMetaDataConverter converter) {
        this.jpaRepository = jpaRepository;
        this.converter = converter;
    }

    public void save(List<PictureMetaData> pictureMetaData) {
        jpaRepository.saveAll(converter.toEntities(pictureMetaData));
    }


    public List<PictureMetaData> findBySearchTerm(String searchTerm) {
        List<PictureMetaDataEntity> entities = jpaRepository.findBySearchTermsContaining(searchTerm);
        return converter.fromEntities(entities);
    }



}
