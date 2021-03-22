import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class Teste {
    @Somar
    public BigDecimal number1;

    @Somar
    public BigDecimal number2;

    @Subtrair
    public BigDecimal number3;

    @Subtrair
    public BigDecimal number4;

    public Teste(BigDecimal number3, BigDecimal number4) {
        this.number3 = number3;
        this.number4 = number4;
    }
}
