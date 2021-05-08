package resources;

public enum APIResoruces_Enum {

    addPlaceAPI("/api/place/add/json"),
    getPlaceAPI("/api/place/get/json"),
    deletePlaceAPI("/api/place/delete/json"),
    putPlaceAPI("/api/place/update/json");

    private String resource;

    APIResoruces_Enum(String resource) {
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }

}
