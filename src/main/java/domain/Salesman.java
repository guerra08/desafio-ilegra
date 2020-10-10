package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Salesman {

    private String CNPJ;
    private String name;
    private double salary;

}
