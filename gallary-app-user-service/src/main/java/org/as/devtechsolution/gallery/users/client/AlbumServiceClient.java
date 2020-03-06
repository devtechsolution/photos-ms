package org.as.devtechsolution.gallery.users.client;

import java.util.List;

import org.as.devtechsolution.gallery.users.bean.AlbumResBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="albums-ms")
public interface AlbumServiceClient {
	
	@GetMapping("/users/{id}/albumsss")
	public List<AlbumResBean> getAlbums(@PathVariable String id) ;

}
