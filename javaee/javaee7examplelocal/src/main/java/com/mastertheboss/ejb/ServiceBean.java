package com.mastertheboss.ejb;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mastertheboss.model.SimpleProperty;

@Stateless

public class ServiceBean {

	@Inject
	private Event<SimpleProperty> propEventSrc;

	@Inject
	private EntityManager em;

	public void put(SimpleProperty p) {
		LocalDateTime time = LocalDateTime.now();
		LocalDate date = LocalDate.now();

		p.setDate(date);
		p.setTimestamp(time);

		em.persist(p);
		propEventSrc.fire(p);
	}

	public void delete(SimpleProperty p) {

		Query query = em.createQuery("delete FROM SimpleProperty p where p.key='" + p.getKey() + "'");

		query.executeUpdate();
		propEventSrc.fire(p);

	}

}
