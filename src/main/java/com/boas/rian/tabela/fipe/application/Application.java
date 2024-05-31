package com.boas.rian.tabela.fipe.application;

import com.boas.rian.tabela.fipe.model.CommomData;
import com.boas.rian.tabela.fipe.model.ModelData;
import com.boas.rian.tabela.fipe.model.VehicleData;
import com.boas.rian.tabela.fipe.service.ConvertData;
import com.boas.rian.tabela.fipe.service.FetchApi;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private ConvertData conversor;
    private FetchApi fetchApi;
    public Application(ConvertData conversor, FetchApi fetchApi){
        this.conversor = conversor;
        this.fetchApi = fetchApi;
    }

    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o tipo de veiculo que deseja buscar: (Carros, Motos, Caminhoes)");

        String vehicleType = scanner.nextLine();

        List<CommomData> brands = searchVehicleType(vehicleType.toLowerCase());

        brands.stream()
                .sorted(Comparator.comparing(CommomData::name, String.CASE_INSENSITIVE_ORDER))
                .forEach(b -> System.out.println("Marca: " + b.name() + ", Codigo: " + b.code()));

        System.out.println("------------------------ Digite o codigo da marca desejada: ------------------------");
        String brandCode = scanner.nextLine();

        ModelData modelData = searchVehicleModels(vehicleType.toLowerCase(), brandCode);

        modelData.models().stream()
                .sorted(Comparator.comparing(CommomData::name, String.CASE_INSENSITIVE_ORDER))
                .forEach(b -> System.out.println("Modelo: " + b.name() + ", Codigo: " + b.code()));

        System.out.println("------------------------ Digite o codigo da marca desejada: ------------------------");
        String modelCode = scanner.nextLine();

        List<CommomData> vehicleYearsByModel = searchVehicleYearsByModel(vehicleType.toLowerCase(), brandCode, modelCode);

        List<VehicleData> vehicleData = vehicleYearsByModel.stream()
                .map(v -> searchVehicleByYear(vehicleType.toLowerCase(), brandCode, modelCode, v.code()))
                .toList();

        vehicleData.forEach(v -> {
            System.out.println(v.brand() + " " + v.model() + " ano: " + v.year() + " valor:" + v.value());
        });
    }

    private List<CommomData> searchVehicleType(String vehicleType){
        var json = fetchApi.fetch(BASE_URL + vehicleType + "/marcas");
        return conversor.convertJsonList(json, CommomData.class);
    }

    private ModelData searchVehicleModels(String vehicleType, String brandCode){
        var json = fetchApi.fetch(BASE_URL + vehicleType + "/marcas/" + brandCode + "/modelos");
        return conversor.convertJson(json, ModelData.class);
    }

    private List<CommomData> searchVehicleYearsByModel(String vehicleType, String brandCode, String model){
        var json = fetchApi.fetch(BASE_URL + vehicleType + "/marcas/" + brandCode + "/modelos/" + model + "/anos");
        return conversor.convertJsonList(json, CommomData.class);
    }

    private VehicleData searchVehicleByYear(String vehicleType, String brandCode, String model, String year){
        var json = fetchApi.fetch(BASE_URL + vehicleType + "/marcas/" + brandCode + "/modelos/" + model + "/anos/" + year);
        return conversor.convertJson(json, VehicleData.class);
    }
}
