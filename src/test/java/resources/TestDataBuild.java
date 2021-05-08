package resources;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address)
    {

        List<String> typesList=new ArrayList<>();
        typesList.add("Shoes");
        typesList.add("Boxes");

        AddPlace p=new AddPlace();
        Location l=new Location();
        l.setLat(12345);
        l.setLng(345.33);

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setLocation(l);
        p.setName(name);
        p.setTypes(typesList);
        p.setPhone_number("123456789");
        p.setWebsite("www.a.com");
        return p;

    }

    public DeletePlace deletePlacePayload(String place_id){

        DeletePlace d=new DeletePlace();
        d.setPlace_id(place_id);
        return d;

    }

}
