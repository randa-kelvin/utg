package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class ClientsFilteredResponse {
    Long totalFilteredRecords;
    ClientPageItem[] pageItems;
}
