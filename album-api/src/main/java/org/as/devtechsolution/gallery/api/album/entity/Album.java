package org.as.devtechsolution.gallery.api.album.entity;

import lombok.Data;

@Data
public class Album {
	
	private long id;
    private String albumId;
    private String userId; 
    private String name;
    private String description; 

}
