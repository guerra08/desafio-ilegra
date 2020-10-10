package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Salesman {

    private String cnpj;
    private String name;
    private double salary;

}
