package com.restapplication.dto;

import com.restapplication.entity.Address;
import com.restapplication.utils.Constantes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AddressTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " rua")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String streetName;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " número")
    private Integer number;
    private String complement;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " bairro")
    private String neighbourhood;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " cidade")
    private String city;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " estado")
    private String state;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " país")
    private String country;

    @NotNull(message = Constantes.MENSAGEM_CAMPO_OBRIGATORIO + " CEP")
    private String zipcode;
    private String latitude;
    private String longitude;

    public AddressTO(Address address) {
        this.id = address.getId();
        this.streetName = address.getStreetName();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.neighbourhood = address.getNeighbourhood();
        this.city = address.getCity();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipcode = address.getZipcode();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }
}
