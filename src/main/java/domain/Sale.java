package domain;

import lombok.Data;

@Data
public class Sale {

    private String saleId;
    private double salePrice;
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
    private double generateSalePrice(String soldProducts){
        String[] products = soldProducts.replaceAll("[\\[\\](){}]","").split(",");
        double total = 0.00;
        for(String product : products){
            String[] productInfo = product.split("-");
            total += Integer.parseInt(productInfo[1]) * Double.parseDouble(productInfo[2]);
        }
        System.out.println(total);
        return total;
    }

}
