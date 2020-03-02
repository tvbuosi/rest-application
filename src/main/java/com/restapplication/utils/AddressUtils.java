package com.restapplication.utils;

import com.restapplication.dto.AddressTO;
import com.restapplication.entity.Address;

public class AddressUtils {

    public static Address fromDTO(AddressTO addressTO) {
        return new Address(addressTO.getId(), addressTO.getStreetName(),
                addressTO.getNumber(), addressTO.getComplement(), addressTO.getNeighbourhood(),
                addressTO.getCity(), addressTO.getState(), addressTO.getCountry(),
                addressTO.getZipcode(), addressTO.getLatitude(),addressTO.getLongitude());
    }

    public static void addressUpdate(Address newAddress, Address address) {

        newAddress.setId(address.getId());
        newAddress.setStreetName(address.getStreetName());
        newAddress.setNumber(address.getNumber());
        newAddress.setComplement(address.getComplement());
        newAddress.setNeighbourhood(address.getNeighbourhood());
        newAddress.setCity(address.getCity());
        newAddress.setState(address.getState());
        newAddress.setCountry(address.getCountry());
        newAddress.setZipcode(address.getZipcode());
        newAddress.setLatitude(address.getLatitude());
        newAddress.setLongitude(address.getLongitude());
    }
}
