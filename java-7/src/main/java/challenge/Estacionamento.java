package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Estacionamento {
    List<Carro> carros = new ArrayList<Carro>();
    private int preferentialAge = 55;
    private int minAge = 18;
    private int maxPenaltyPoints = 20;
    private int fullParkingLot = 10;

    public void estacionar(Carro carro) {
        if (carro.getMotorista() == null) {
            throw new EstacionamentoException("Não é permitido carros autônomos");
        } else if (carro.getMotorista().getIdade() < minAge) {
            throw new EstacionamentoException("Motorista fora da idade legal para dirigir");
        } else if (carro.getMotorista().getHabilitacao() == null) {
            throw new EstacionamentoException("Motorista não habilitado");
        } else if (carro.getMotorista().getPontos() > maxPenaltyPoints) {
            throw new EstacionamentoException("Habilitação suspensa");
        }
        if (carros.size() >= fullParkingLot) {
            Optional<Carro> findFirstCar = carros.stream().filter(car -> car.getMotorista().getIdade() < preferentialAge).findFirst();
            if (carros.stream().filter(car -> car.getMotorista().getIdade() > preferentialAge).collect(toList()).size() == fullParkingLot) {
                throw new EstacionamentoException("Estacionamento lotado: Motoristas em idade preferencial");
            } else if (findFirstCar.isPresent()) { //Optional retorna true or false
                carros.remove(findFirstCar.get()); //Recuperar valor Optional necessário get()
                carros.add(carro);
            }
        } else {
            carros.add(carro);
        }
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        if (carros.contains(carro)) {
            return true;
        }
        return false;
    }
}
