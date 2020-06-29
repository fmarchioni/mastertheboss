package com.mastertheboss.jaxrs.service;


import com.mastertheboss.jaxrs.model.Item;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/tutorial")
public class SimpleRESTService {


	@GET
	@Path("helloname/{name}")
	public String hello(@PathParam("name") final String name) {
		return "Hello " +name;
	}

	@GET
	@Path("item")
	@Produces(MediaType.APPLICATION_XML)
	public Item  getItem() {

		Item item = new Item("computer",2500);

		return item;
	}

	@GET
	@Path("itemArray")
	@Produces(MediaType.APPLICATION_XML)
	public Item[]  getItemArray() {
		Item item[] = new Item[2];
		item[0] = new Item("computer",2500);
		item[1] = new Item("chair",100);

		return item;
	}

	@GET
	@Path("itemList")
	@Produces(MediaType.APPLICATION_XML)
	public List<Item> getCollItems() {
		List list = new ArrayList();
		Item item1 = new Item("computer",2500);
		Item item2 = new Item("chair",100);
		Item item3 = new Item("table",200);

		list.add(item1);
		list.add(item2);
		list.add(item3);

		return list;
	}

    @GET
    @Path("itemListJson")
    @Produces("application/json")
    public List<Item>  getJSONItems() {
        ArrayList list = new ArrayList();
        Item item1 = new Item("computer",2500);
        Item item2 = new Item("chair",100);
        Item item3 = new Item("table",200);

        list.add(item1);
        list.add(item2);
        list.add(item3);

        return list;
    }
}
