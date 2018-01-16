package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Tank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TankRepository {
  private static List<Tank> tankList = new ArrayList<>(
    Arrays.asList(
        new Tank(1, "kv1", "ussr", "kirovfactory",
                "heavy", 45000, 6.75F, 3.32F,
                2.71F, 5, 35F),
        new Tank(2, "su100", "ussr", "kirovfactory",
                "destroyer", 31600, 9.45F, 3F,
                2.25F, 4, 48F),
        new Tank(3, "tigerI", "nazigermany", "henschel&son",
                "heavy", 54000, 6.316F, 6.316F,
                3.0F, 5, 45.4F),
        new Tank(4, "tigerII", "nazigermany", "henschel&son",
                "heavy", 685000, 7.38F, 3.755F,
                3.09F, 5, 41.5F)
    ));

  public List<Tank> getTankList() {
    return tankList;
  }

  public Optional<Tank> getTankById(Integer id) {
    return tankList.stream().filter(tank -> tank.getId() == id).findFirst();
  }

  public Tank saveTank(Tank tank) {
    tankList.add(tank);
    return tank;
  }

  public Tank updateTank(Integer id, Tank tank) {
    int realId = id - 1;
    tankList.set(realId, tank);
    return tank;
  }

  public void deleteTank(Integer id) {
    tankList.removeIf(it -> it.getId() == id);
  }
}
