package com.yuliia.vlasenko.imagesearcher.repository;


import com.yuliia.vlasenko.imagesearcher.repository.entities.PictureMetaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureMetadataJpaRepository extends JpaRepository<PictureMetaDataEntity, String> {
    List<PictureMetaDataEntity> findBySearchTermsContaining(String searchTerm);
}
