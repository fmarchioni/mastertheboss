package org.acme;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/hero")
@Produces("application/json")
@Consumes("application/json")

public class HeroResource {


    @GET
    public Hero[] get() {
        Hero hero[] = new Hero[3];
        hero[0] = new Hero("AAAAA");
        hero[1] = new Hero("AAAAA");
        hero[2] = new Hero("AAAAA");
        return hero;
    }
    /*
    @POST
    @Transactional
    public Response create(Hero hero) {
        if (hero.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(hero);
        return Response.ok(hero).status(201).build();
    }

    @PUT
    @Transactional
    public Hero update(Hero hero) {
        if (hero.getId() == null) {
            throw new WebApplicationException("Hero Id was not set on request.", 422);
        }

        Hero entity = entityManager.find(Hero.class, hero.getId());

        if (entity == null) {
            throw new WebApplicationException("Hero with id of " + hero.getId() + " does not exist.", 404);
        }

        entity.setName(hero.getName());
        return entity;
    }

    @DELETE
    @Transactional
    public Response delete(Hero hero) {
        Hero entity = entityManager.find(Hero.class, hero.getId());
        if (entity == null) {
            throw new WebApplicationException("Hero with id of " + hero.getId() + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }
 */
}