package org.as.devtechsolution.gallery.api.album.mapper;

import org.as.devtechsolution.gallery.api.album.bean.AlbumResBean;
import org.as.devtechsolution.gallery.api.album.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumMapper {

	AlbumMapper INSTANCE= Mappers.getMapper(AlbumMapper.class);
	
	AlbumResBean toAlbumResFromAlbum(Album album);
	
	AlbumResBean toAlbumFromAlbumRes(AlbumResBean albumBean);
	
}
