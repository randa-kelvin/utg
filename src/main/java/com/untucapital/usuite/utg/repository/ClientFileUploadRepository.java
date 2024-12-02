package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientFileUploadRepository extends JpaRepository<ClientFileUpload, String> {

    List<ClientFileUpload> findClientFileUploadByUserId(String userId);

    ClientFileUpload findClientFileUploadById(String id);
}
