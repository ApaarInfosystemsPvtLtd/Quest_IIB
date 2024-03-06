package com.pmli.iib.repo;

import com.pmli.iib.model.log.QuestIibState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestStateRepo extends MongoRepository<QuestIibState,String> {
}
