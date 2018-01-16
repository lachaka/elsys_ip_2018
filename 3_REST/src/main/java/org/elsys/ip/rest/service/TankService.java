package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Tank;
import org.elsys.ip.rest.repository.TankRepository;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TankService {

    private TankRepository tankRepository = new TankRepository();

    public List<Tank> getTankList() {
        return tankRepository.getTankList();
    }

    public Tank getTankById(Integer id) {
        return tankRepository.getTankById(id).orElse(null);
    }

    public List<Tank> getTanksById(MultivaluedMap<String, String> queryParams) {
        List<Tank> result = new ArrayList<>();

        for (String id : queryParams.get("id")) {
            result.add(getTankList().stream()
                    .filter(tank -> tank.getId() == Integer.valueOf(id))
                    .findFirst().get());
        }

        return result;
    }

    public List<Tank> getTanksByFilter(MultivaluedMap<String, String> queryParams) {
        List<Tank> tankList = filterQueries(queryParams);

        return tankList;
    }

    private List<Tank> filterQueries(MultivaluedMap<String, String> queryParams) {
        List<Tank> tankList = getTankList();
        for (String key : queryParams.keySet()) {
            switch (key) {
                case "name":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getName()
                                    .equals(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "manufacturer":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getManufacturer()
                                    .equals(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "height":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getHeight() == Float.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "length":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getLength() == Float.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "weight":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getWeight() == Float.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "width":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getWidth() == Float.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "crew":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getCrew() == Integer.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "speed":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getSpeed() == Float.valueOf(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "type":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getType().equals(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;

                case "country":
                    tankList = tankList.stream()
                            .filter(tank -> tank.getCountry().equals(queryParams.getFirst(key)))
                            .collect(Collectors.toList());
                break;
            }
        }
        return tankList;
    }

    public Tank saveTank(Tank tank) {
        return tankRepository.saveTank(tank);
    }

    public Tank updateTank(Integer id, Tank tank) {
        return tankRepository.updateTank(id, tank);
    }

    public void deleteTank(Integer id) {
        tankRepository.deleteTank(id);
    }
}
