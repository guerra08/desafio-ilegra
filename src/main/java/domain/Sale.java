package domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Sale {

    private String saleId;
    private BigDecimal salePrice;
    private String salesmanName;

    public Sale(String saleId, String soldProducts, String salesmanName){
        this.saleId = saleId;
        this.salesmanName = salesmanName;
        this.salePrice = generateSalePrice(soldProducts);
    }

    /**
     * Generates the sale price based on the string of products in the .dat file
     * @param soldProducts The String of products
     * @return double
     */
    private BigDecimal generateSalePrice(String soldProducts){
        BigDecimal total = new BigDecimal("0.00");
        String[] products = soldProducts.replaceAll("[\\[\\](){}]","").split(",");
        for(String product : products){
            String[] productInfo = product.split("-");
            total = total.add(BigDecimal.valueOf(Integer.parseInt(productInfo[1]) * Double.parseDouble(productInfo[2])));
        }
        return total;
    }

}
