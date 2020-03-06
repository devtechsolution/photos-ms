package org.as.devtechsolution.gallery.users.client;

import java.util.ArrayList;
import java.util.List;

import org.as.devtechsolution.gallery.users.bean.AlbumResBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.FeignException;
import feign.hystrix.FallbackFactory;


@FeignClient(name="albums-ms", fallbackFactory=AlbumFallbackFactory.class)
public interface AlbumServiceClient {
	
	@GetMapping("/users/{id}/albumss")
	public List<AlbumResBean> getAlbums(@PathVariable String id) ;

}

@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient>{

	@Override
	public AlbumServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new AlbumServiceClientFallback(cause);
	}
	
} 

class AlbumServiceClientFallback implements AlbumServiceClient{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private final Throwable cause;

	public AlbumServiceClientFallback(Throwable cause) {
		
		this.cause=cause;
	}	

	@Override
	public List<AlbumResBean> getAlbums(String id) {
		// TODO Auto-generated method stub
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getAlbums was called with userId: " + id + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}

		return new ArrayList<>();
	}
	
}

/*
 * @FeignClient(name="albums-ms", fallback=AlbumFallback.class) public interface
 * AlbumServiceClient {
 * 
 * @GetMapping("/users/{id}/albums") public List<AlbumResBean>
 * getAlbums(@PathVariable String id) ;
 * 
 * }
 * 
 * @Component class AlbumFallback implements AlbumServiceClient{
 * 
 * @Override public List<AlbumResBean> getAlbums(String id) { return new
 * ArrayList<>(); }
 * 
 * }
 */