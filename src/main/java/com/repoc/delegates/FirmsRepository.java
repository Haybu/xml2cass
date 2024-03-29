package com.repoc.delegates;

import com.repoc.client.Mozenda10Collection;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hmohamed on 3/6/16.
 */
@Repository
public interface FirmsRepository extends CrudRepository<Mozenda10Collection, Integer> {

    @Query("select * from firms where firmId = ?0")
    public Mozenda10Collection getOneFirm(Integer firmId);

}
