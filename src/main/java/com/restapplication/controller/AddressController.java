package com.restapplication.controller;

import com.google.maps.errors.ApiException;
import com.restapplication.dto.AddressTO;
import com.restapplication.entity.Address;
import com.restapplication.service.AddressService;
import com.restapplication.utils.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AddressTO addressTO) throws InterruptedException, ApiException, IOException {

        Address address = AddressUtils.fromDTO(addressTO);
        address = addressService.insert(address);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<AddressTO>> findAll() {

        List<Address> list = addressService.findAll();
        List<AddressTO> listDto = list.stream().map(AddressTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Address> findById(@PathVariable Integer id) {
        Address obj = addressService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AddressTO addressTO, @PathVariable Integer id) throws InterruptedException, ApiException, IOException {
        Address obj = AddressUtils.fromDTO(addressTO);
        obj.setId(id);
        addressService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
