package org.as.devtechsolution.gallery.users.bean;

import java.util.List;

import lombok.Data;

@Data
public class UserResBean {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResBean> albums;
    
	
	
	
}
