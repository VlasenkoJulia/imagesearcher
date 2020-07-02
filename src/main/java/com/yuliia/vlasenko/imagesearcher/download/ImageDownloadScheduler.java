package com.yuliia.vlasenko.imagesearcher.download;

import com.yuliia.vlasenko.imagesearcher.repository.PictureMetaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
public class ImageDownloadScheduler {

    private final ImageDownloader imageDownloader;
    private final PictureMetaDataRepository repository;

    public ImageDownloadScheduler(ImageDownloader imageDownloader, PictureMetaDataRepository repository) {
        this.imageDownloader = imageDownloader;
        this.repository = repository;
    }

    @Scheduled(initialDelayString = "${scheduler.initialDelayString}",
            fixedRateString = "${scheduler.fixedRateString}")
    public void downloadImages() {
        log.info("Start download all images...");
        AuthResponse authToken = imageDownloader.getAuthToken("23567b218376f79d9415");
        downloadAllImages(authToken.getToken());
    }

    private void downloadAllImages(String token) {
        boolean hasMore = true;
        for (int i = 1; hasMore; i++) {
            Images images = imageDownloader.getImages(i, token);
            log.info("Downloaded page {}", i);
            List<String> pictureIds = images.getPictures().stream().map(Picture::getId).collect(toList());
            List<PictureMetaData> pictureMetaData = imageDownloader.getMetaData(pictureIds, token);
            repository.save(pictureMetaData);
            hasMore = images.isHasMore();
        }
    }


}
