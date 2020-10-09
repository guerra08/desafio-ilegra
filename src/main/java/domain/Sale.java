package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Sale {

    private String saleId;
    private double salePrice;
    private String salesmanName;

}
