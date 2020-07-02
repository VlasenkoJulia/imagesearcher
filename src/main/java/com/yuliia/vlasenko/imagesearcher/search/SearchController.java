package com.yuliia.vlasenko.imagesearcher.search;

import com.yuliia.vlasenko.imagesearcher.api.PictureMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{searchTerm}")
    public List<PictureMetaData> search(@PathVariable String searchTerm) {
        log.info("Search pictures by {}", searchTerm);
        return searchService.search(searchTerm);
    }
}
