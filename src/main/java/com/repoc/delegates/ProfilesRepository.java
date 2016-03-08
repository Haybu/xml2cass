package com.repoc.delegates;

import com.repoc.client.Mozenda10Item;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by hmohamed on 3/6/16.
 */
@Repository
public interface  ProfilesRepository extends CrudRepository<Mozenda10Item, Integer> {

    @Query("select * from profiles where firmId=?0 and profileId = ?1")
    public Mozenda10Item findByFirmIdAndItemId(int firmId, int profileId);

    @Query("select * from profiles where firmId=?0")
    public Iterable<Mozenda10Item> findByFirmId(int firmId);
}
