package com.yuliia.vlasenko.imagesearcher.download;

import lombok.Data;

import java.util.List;

@Data
public class Images {
    private List<Picture> pictures;
    private int page;
    private int pageCount;
    private boolean hasMore;

}
