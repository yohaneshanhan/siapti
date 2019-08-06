package com.siapti.servicekota.Kota;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.PatternSyntaxException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class KotaRepo {
	 MongoOperations mongoOperations; 
	 
	 public Collection<Kota> findByNama(String like) {
	        try{
	            Query query = new Query();
	            query.addCriteria(Criteria.where("name").regex(toLikeRegex(like)));
	            return mongoOperations.find(query, Kota.class);
	        } catch(PatternSyntaxException e) {
	            return Collections.emptyList();
	        }
	    }

	    private String toLikeRegex(String source) {
	        return source.replaceAll("\\*", ".*");
	    }
}
