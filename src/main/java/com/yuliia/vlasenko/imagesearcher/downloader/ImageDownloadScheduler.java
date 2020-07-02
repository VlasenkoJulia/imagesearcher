package com.yuliia.vlasenko.imagesearcher.downloader;

import com.yuliia.vlasenko.imagesearcher.api.ImageApi;
import com.yuliia.vlasenko.imagesearcher.api.Images;
import com.yuliia.vlasenko.imagesearcher.api.Picture;
import com.yuliia.vlasenko.imagesearcher.api.PictureMetaData;
import com.yuliia.vlasenko.imagesearcher.repository.PictureMetaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
public class ImageDownloadScheduler {

    private final ImageApi imageApi;
    private final PictureMetaDataRepository repository;

    public ImageDownloadScheduler(ImageApi imageApi, PictureMetaDataRepository repository) {
        this.imageApi = imageApi;
        this.repository = repository;
    }

    @Scheduled(initialDelayString = "${scheduler.initialDelayString}",
            fixedRateString = "${scheduler.fixedRateString}")
    public void downloadImages() {
        log.info("Start download all images...");
        downloadAllImages();
    }

    private void downloadAllImages() {
        boolean hasMore = true;
        for (int i = 1; hasMore; i++) {
            Images images = imageApi.getImages(i);
            log.info("Downloaded page {}", i);
            List<String> pictureIds = images.getPictures().stream().map(Picture::getId).collect(toList());
            List<PictureMetaData> pictureMetaData = imageApi.getMetaData(pictureIds);
            repository.save(pictureMetaData);
            hasMore = images.isHasMore();
        }
    }


}
