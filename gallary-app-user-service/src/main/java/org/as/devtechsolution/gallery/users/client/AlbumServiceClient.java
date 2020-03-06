package org.as.devtechsolution.gallery.users.client;

import java.util.ArrayList;
import java.util.List;

import org.as.devtechsolution.gallery.users.bean.AlbumResBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="albums-ms", fallback=AlbumFallback.class)
public interface AlbumServiceClient {
	
	@GetMapping("/users/{id}/albums")
	public List<AlbumResBean> getAlbums(@PathVariable String id) ;

}

@Component
class AlbumFallback implements AlbumServiceClient{

	@Override
	public List<AlbumResBean> getAlbums(String id) {
		return new ArrayList<>();
	}
	
} 