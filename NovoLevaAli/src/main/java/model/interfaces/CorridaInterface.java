package model.interfaces;

import java.util.List;
import model.Corrida;
import model.Passageiro;
import model.Piloto;

/**
 *
 * @author Adrielly Sales
 */
public interface CorridaInterface extends Dao<Corrida> {
      List<Corrida> recuperarPorPiloto(Piloto piloto);
      List<Corrida> recuperarPorPassageiro(Passageiro passageiro);
      List<Corrida> recuperarDisponiveis(Corrida corrida);
    
}
