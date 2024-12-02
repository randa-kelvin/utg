package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loans {

    private int totalFilteredRecords;
    private List<PageItem> pageItems;
}
