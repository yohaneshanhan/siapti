package com.siapti.servicekota.Kota;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface KotaRepository extends MongoRepository<Kota, ObjectId> {

	List<Kota> findByNamaContaining(String kota);

    //Supports native JSON query string
    @Query("{kota:'?0'}")
    Kota findCustomByNama(String domain);

    @Query("{kota: { $regex: ?0 } })")
    List<Kota> findCustomByRegExNama(String domain);
    
}
