
package com.mastertheboss.graphql.sample.client;

import com.mastertheboss.graphql.model.Item;
import org.eclipse.microprofile.graphql.Name;

import java.util.List;

public interface ItemApi {

    public Item getItem(@Name("id") Integer id);
    public List<Item> getAllItems();
    public Item createItem(Item item);
    public Item deleteItem(Integer id);
}