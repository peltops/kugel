package com.peltops.kugel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peltops.kugel.dto.KugelDto;
import com.peltops.kugel.entity.Kugel;
import com.peltops.kugel.service.KugelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.peltops.kugel.util.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KugelRestController.class)
public class KugelRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KugelService kugelService;

    private KugelDto kugelDto;

    private Kugel createdKugel;

    @Test
    public void shouldReturnOKForVersion() throws Exception {
        mockMvc.perform(get("/api/version").contentType("application/json")).andExpect(status().isOk());
    }

    @BeforeEach
    public void setup() {
        kugelDto = new KugelDto();
        kugelDto.setColor("red");
        kugelDto.setDiameter(2);

        createdKugel = new Kugel();
        createdKugel.setColor("red");
        createdKugel.setDiameter(2);
        createdKugel.setId(100);
    }
    @Test
    public void shouldCreateAKugel() throws Exception {

        when(kugelService.create(any(Kugel.class))).thenReturn(createdKugel);

        MvcResult mvcResult = mockMvc.perform(post("/api/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(this.kugelDto)))
                .andExpect(status().isCreated())
                .andExpect(responseBody().containsObjectAsJson(createdKugel, Kugel.class))
                .andReturn();

        ArgumentCaptor<Kugel> kugelArgumentCaptor = ArgumentCaptor.forClass(Kugel.class);
        verify(kugelService, times(1)).create(kugelArgumentCaptor.capture());
        assertThat(kugelArgumentCaptor.getValue().getDiameter()).isEqualTo(2);
        assertThat(kugelArgumentCaptor.getValue().getColor()).isEqualTo("red");
    }

    @Test
    public void shouldReturnBadRequestWhenDiameterIsNull() throws Exception {
        KugelDto kugelDto = new KugelDto();
        kugelDto.setColor("red");
        mockMvc.perform(post("/api/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(kugelDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsError("diameter","must not be null"));
    }
}
