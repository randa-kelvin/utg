package com.untucapital.usuite.utg.pos.model;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class POSSuplierFileUploads extends AbstractEntity {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_description")
    private String fileDescription;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private POSSupplier supplier;
}
