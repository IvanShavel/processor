package com.stock.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
//@Entity(name = "companies")
public class Company {

  /*  @Id
    @Field(name = "company_id")
    private String companyId;
*/
    private String name;
    private String logo;
    private String price;
}
