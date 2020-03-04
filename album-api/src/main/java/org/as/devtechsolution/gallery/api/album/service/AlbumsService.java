package org.as.devtechsolution.gallery.api.album.service;


import java.util.List;

import org.as.devtechsolution.gallery.api.album.entity.Album;

public interface AlbumsService {
    List<Album> getAlbums(String userId);
}
