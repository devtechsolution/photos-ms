```curl -X GET \
  http://localhost:9090/gallary-api-cofig-server/native \
  -H 'cache-control: no-cache' \
  -H 'postman-token: f62e0bb3-a93a-b32c-bfd5-32e14ff61443'
  
```
```  
  {
    "name": "gallary-api-cofig-server",
    "profiles": [
        "native"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "file:///C:/Users/Aditya/Desktop/aditya/dev/gallary-api-cofig-server.properties",
            "source": {
                "gateway.ip": "192.168.43.244",
                "token.expiration_time": "86400000",
                "token.secret": "hfgry463hf746hf573ydh475fhy57392222",
                "loginurl.path": "/users/login"
            }
        }
    ]
}

```

```
    Rename the gallary-api-cofig-server file to application.properties to access by all the MS.
    For microservice specific we will use projectname.properties file, for example user-ms.properties
```
  

```
curl -X GET \
  http://localhost:9090/users-ms/default \
  -H 'cache-control: no-cache' \
  -H 'postman-token: c22e9a01-9b32-fca8-1d2a-76b6c04e58e1'
  
  
  {
    "name": "users-ms",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "file:///C:/Users/Aditya/Desktop/aditya/dev/users-ms.properties",
            "source": {
                "loginurl.path": "/users/login"
            }
        },
        {
            "name": "file:///C:/Users/Aditya/Desktop/aditya/dev/application.properties",
            "source": {
                "gateway.ip": "192.168.43.244",
                "token.expiration_time": "86400000",
                "token.secret": "hfgry463hf746hf573ydh475fhy57392222",
                "loginurl.path": "/users/login"
            }
        }
    ]
}
```

```
curl -X GET \
  http://localhost:9090/gallary-api-cofig-server/default \
  -H 'cache-control: no-cache' \
  -H 'postman-token: e4bafb9f-b92d-5f91-212b-1bcc95dcbe5e'
  
  {
    "name": "gallary-api-cofig-server",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "file:///C:/Users/Aditya/Desktop/aditya/dev/application.properties",
            "source": {
                "gateway.ip": "192.168.43.244",
                "token.expiration_time": "86400000",
                "token.secret": "hfgry463hf746hf573ydh475fhy57392222",
                "loginurl.path": "/users/login"
            }
        }
    ]
}
```

```
http://localhost:8765/actuator/beans
http://localhost:8765/actuator/routes
http://localhost:8765/actuator/health
http://localhost:8765/users-ms/actuator/mappings

```
## users-ms.properties
'''
    loginurl.path=/users/login
    ## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
    spring.datasource.url = jdbc:mysql://localhost:3306/photo_app?useSSL=false
    spring.datasource.username = root
    spring.datasource.password = root
'''

## application.properties
'''
    gateway.ip=192.168.43.244
    token.expiration_time = 86400000
    token.secret = hfgry463hf746hf573ydh475fhy57392222
    loginurl.path=/users/login
'''

