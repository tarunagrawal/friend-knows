package com.system.you.review.core.service;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchService {

	private FullTextSession getSession() {
		FullTextSession session = Search.getFullTextSession(sessionFactory
				.getCurrentSession());
		return session;
	}

	protected <T> SearchQuery<T> searchQuery(Class<T> entity, String fieldName,
			String searchTerm) {
		return new SearchQuery<T>(entity, getSession(), fieldName, searchTerm);
	}

	protected class SearchQuery<T> {

		private SearchQuery(Class<T> entity, FullTextSession fullTextSession,
				String fieldName, String searchTerm) {
			this.entity = entity;
			this.session = fullTextSession;
			this.fieldName = fieldName;
			this.searchTerm = searchTerm;
		}

		public QueryBuilder queryBuilder() {
			QueryBuilder builder = this.session.getSearchFactory()
					.buildQueryBuilder().forEntity(this.entity).get();
			return builder;
		}

		@SuppressWarnings("unchecked")
		public List<T> executeWildCard() {
			Query query = queryBuilder().keyword().wildcard()
					.onField(fieldName).matching(searchTerm + "*")
					.createQuery();
			return this.session.createFullTextQuery(query, entity).list();
		}

		private Class<T> entity;
		private FullTextSession session;
		private String fieldName;
		private String searchTerm;
	}

	@Autowired
	private SessionFactory sessionFactory;
}
