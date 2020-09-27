// ******************************************************************************
//  Copyright (c) 2019 IBM Corporation and others.
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  which accompanies this distribution, and is available at
//  http://www.eclipse.org/legal/epl-v10.html
//
//  Contributors:
//  IBM Corporation - initial API and implementation
// ******************************************************************************

package com.mastertheboss.graphql.sample;

import com.mastertheboss.graphql.model.Item;
import org.eclipse.microprofile.graphql.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@ApplicationScoped
public class ItemService {

    List<Item> items = new ArrayList<>();

    @Query("item")
    @Description("Get a single Item by id")
    public Item getItem(@Name("id") Integer id){
        return findItem(id);
    }

    @Query("allItems")
    @Description("Get all Items")
    public List<Item> getAllItems() {
        return items;
    }

    @Mutation
    public Item createItem(@Name("item") Item item) {
        items.add(item);
        return item;
    }
    @Mutation
    public Item deleteItem(Integer id) {
        Item item = findItem(id);
        items.removeIf(e -> e.getId().equals(id));
        return item;
    }

    public Item findItem(Integer id) {
        Item item = items.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
        return item;
    }
 }
