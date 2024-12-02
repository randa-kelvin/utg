package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, String> {
}