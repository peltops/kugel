package com.peltops.kugel.service;

import com.peltops.kugel.repository.KugelRepository;
import com.peltops.kugel.entity.Kugel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class KugelServiceTest {

    @Mock
    private KugelRepository kugelRepository;

    @InjectMocks
    private KugelService kugelService;

    @Test
    public void shouldCreateAKugel() {
        Kugel notSavedKugel = new Kugel();
        notSavedKugel.setColor("red");
        notSavedKugel.setDiameter(1);
        Kugel savedKugel = new Kugel();
        savedKugel.setId(1);
        savedKugel.setColor("red");
        savedKugel.setDiameter(1);
        when(kugelRepository.save(notSavedKugel)).thenReturn(savedKugel);
        Kugel returnedKugel = kugelService.create(notSavedKugel);
        assertNotNull(returnedKugel.getId());
        assertEquals(savedKugel, returnedKugel);
    }
}
