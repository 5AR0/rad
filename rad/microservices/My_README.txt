

1. Test image-service before
GET localhost:8082/images
RESPONSE
[
    {
        "id": 1,
        "title": "Treehouse of Horror V",
        "url": "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"
    },
    {
        "id": 2,
        "title": "The Town",
        "url": "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"
    },
    {
        "id": 3,
        "title": "The Last Traction Hero",
        "url": "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"
    }
]


2. 1. Test gallery-service before

GET localhost:8081/gallery/44
RESPONSE
{
    "id": 44,
    "images": null
}


3. Add RestTemplate in gallery-service to call image-service
        List<Object> images = restTemplate.getForObject("http://localhost:8082/images", List.class);
        gallery.setImages(images);


4. Create eureka-service - as Eureka Server (port=8761)
<spring-cloud.version>2023.0.2</spring-cloud.version>

5. Set eureka client properties and cloud dependency to gallery and image services
    add app names.


6. Change the name of image service to rest template and add @LoadBalanced to REstTemplate
    Update gallery controller:
   List<Object> images = restTemplate.getForObject("http://IMAGE-SERVICE/images", List.class);
   GET localhost:8081/gallery/2


