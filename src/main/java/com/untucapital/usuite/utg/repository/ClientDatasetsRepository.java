package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.ClientsDatasets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDatasetsRepository extends JpaRepository<ClientsDatasets, String > {


     ClientsDatasets findClientsDatasetsById(Long id);
     List<ClientsDatasets> findByBranch(String branch);
}
