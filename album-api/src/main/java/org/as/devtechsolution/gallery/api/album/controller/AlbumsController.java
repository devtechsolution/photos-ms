package org.as.devtechsolution.gallery.api.album.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.as.devtechsolution.gallery.api.album.bean.AlbumResBean;
import org.as.devtechsolution.gallery.api.album.entity.Album;
import org.as.devtechsolution.gallery.api.album.mapper.AlbumMapper;
import org.as.devtechsolution.gallery.api.album.service.AlbumsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {

	@Autowired
	AlbumsService albumsService;
	
	private final AlbumMapper albumMapper;

	@Autowired
	public AlbumsController(AlbumMapper albumMapper) {
		this.albumMapper = albumMapper;
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping( 
            produces = { 
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
            })
    public List<AlbumResBean> userAlbums(@PathVariable String id) {

        List<AlbumResBean> returnValue = new ArrayList<>();
        
        List<Album> albumsEntities = albumsService.getAlbums(id);
        
        if(albumsEntities == null || albumsEntities.isEmpty())
        {
            return returnValue;
        }
        
        returnValue=albumsEntities.stream()
        .map(album-> albumMapper.toAlbumResFromAlbum(album))
        .collect(Collectors.toList());
        //Type listType = new TypeToken<List<AlbumResBean>>(){}.getType();
 
        //returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }
}
