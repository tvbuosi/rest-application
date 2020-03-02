package com.restapplication.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.restapplication.entity.Address;
import com.restapplication.repository.AddressRepository;
import com.restapplication.service.exceptions.DataIntegrityException;
import com.restapplication.service.exceptions.ObjectNotFoundException;
import com.restapplication.utils.AddressUtils;
import com.restapplication.utils.Constantes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.restapplication.utils.Constantes.*;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Address findById(Integer id) {

        Optional<Address> adress = addressRepository.findById(id);
        return adress.orElseThrow(() -> new ObjectNotFoundException(
                MENSAGEM_OBJETO_NAO_ENCONTRADO + " Id:" + id + ", Tipo: " + Address.class.getName()));
    }

    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public Address insert(Address address) throws InterruptedException, ApiException, IOException {

        address.setId(null);
        if (null == address.getLongitude() || null == address.getLatitude()){
            getLatitudeLongitude(address);
        }

        return addressRepository.save(address);
    }

    @Transactional
    public void update(Address address) throws InterruptedException, ApiException, IOException {

        Address newAddress = findById(address.getId());
        AddressUtils.addressUpdate(newAddress, address);

        if (null == newAddress.getLongitude() || null == newAddress.getLatitude()){
            getLatitudeLongitude(newAddress);
        }
        addressRepository.save(newAddress);
    }

    @Transactional
    public void delete(Integer id) {

        findById(id);
        try {
            addressRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(MENSAGEM_ERRO_EXCLUSAO);
        }
    }

    private void getLatitudeLongitude(Address address) throws InterruptedException, ApiException, IOException {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY_GOOGLEMAPS)
                .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address.getNumber() + SPACE +
                        address.getStreetName() + SPACE +
                        address.getCity() + COMMA_SPACE +
                        address.getState() + SPACE +
                        address.getZipcode()).await();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        address.setLatitude(gson.toJson(results[0].geometry.location.lat));
        address.setLongitude(gson.toJson(results[0].geometry.location.lng));
    }
}
