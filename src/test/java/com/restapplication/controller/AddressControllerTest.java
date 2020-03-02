package com.restapplication.controller;

import com.restapplication.entity.Address;
import com.restapplication.repository.AddressRepository;
import org.junit.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapplication.service.AddressService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class AddressControllerTest {

    public static final MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;
    private List<Address> addressList;

    @MockBean
    private AddressService addressService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        createAddressList();
    }

    @Test
    @Ignore
    public void testFindAll() throws Exception {

        when(addressService.findAll()).thenReturn(addressList);

        this.mockMvc.perform(get("/addresses").contentType(JSON_CONTENT_TYPE)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].streetName", is("Rua Abilio Jorge Cury")))
                .andExpect(jsonPath("$[0].zipcode", is("15054130")))
                .andExpect(jsonPath("$[3].streetName", is("Avenida Almirante Maximiano Fonseca")))
                .andExpect(jsonPath("$[3].zipcode", is("96204040")));

        verify(addressService, times(1)).findAll();
        verifyNoMoreInteractions(addressService);
    }

    private void createAddressList() {

        Address address1 = new Address(null, "Rua Abilio Jorge Cury", 50, null, "Jardim Nazareth",
                "São José do Rio Preto", "São Paulo", "Brasil", "15054130", null, null);

        Address address2 = new Address(null, "Rua da Imprensa", 100, null, "Monte Castelo",
                "Campo Grande", "Mato Grosso do Sul", "Brasil", "79002290", null, null);

        Address address3 = new Address(null, "Rua Paracatu", 150, null, "Parque Imperial",
                "São Paulo", "São Paulo", "Brasil", "04302021", null, null);

        Address address4 = new Address(null, "Avenida Almirante Maximiano Fonseca", 200, null, "Zona Portuária",
                "Rio Grande", "Rio Grande do Sul", "Brasil", "96204040", null, null);

        addressList.addAll(Arrays.asList(address1,address2, address3, address4));
    }
}
