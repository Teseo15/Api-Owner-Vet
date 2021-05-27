package com.example.demo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.owner.Owner;
import com.example.demo.vet.Veterinario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OwnerControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetOwners() throws Exception {

        //int NRO_RECORD = 73;
        int ID_FIRST_RECORD = 1;
        this.mockMvc.perform(get("/owners/all"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                //    .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testFindOwnerOK() throws Exception {

        String NAME = "George";
        String LAST="Franklin";
        long ID = 1;


        mockMvc.perform(get("/owners/1"))  // Object must be BASIL
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("George")))
                .andExpect(jsonPath("$.lastname", is("Franklin")));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testFindOwnerKO() throws Exception {

        mockMvc.perform(get("/owners/666"))
                .andExpect(status().isNotFound());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testCreateOwner() throws Exception {

        String NAME = "Luke";
        String LAST = "Skywalker";
        String  address = "110 W. Liberty St.";
        String city= "Madison";
        String telephone= "6085551023";
        Owner newPet = new Owner(NAME,LAST,address,city,telephone);
        mockMvc.perform(post("/owners/add")
                .content(om.writeValueAsString(newPet))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is(NAME)))
                .andExpect(jsonPath("$.lastname", is(LAST)));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testDeleteOwner() throws Exception {


        String NAME = "Luke";
        String LAST = "Skywalker";
        String  address = "110 W. Liberty St.";
        String city= "Madison";
        String telephone= "6085551023";
        Owner newPet = new Owner(NAME,LAST,address,city,telephone);

        ResultActions mvcActions = mockMvc.perform(post("/owners/add")
                .content(om.writeValueAsString(newPet))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();

        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/delete/" + id ))
                /*.andDo(print())*/
                .andExpect(status().isOk());
    }
}
