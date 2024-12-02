package com.untucapital.usuite.utg.dto.client;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private int totalFilteredRecords;
    private List<Client> pageItems;
}
