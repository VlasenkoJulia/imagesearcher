package com.yuliia.vlasenko.imagesearcher.search;

import com.yuliia.vlasenko.imagesearcher.api.PictureMetaData;
import com.yuliia.vlasenko.imagesearcher.repository.PictureMetaDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final PictureMetaDataRepository repository;

    public SearchService(PictureMetaDataRepository repository) {
        this.repository = repository;
    }

    public List<PictureMetaData> search(String searchTerm) {
        return repository.findBySearchTerm(searchTerm.toLowerCase());
    }
}
