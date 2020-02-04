package com.peltops.kugel;

import com.peltops.kugel.entity.Kugel;
import com.peltops.kugel.repository.KugelRepository;
import com.peltops.kugel.service.KugelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KugelServiceTest {

    @Mock
    private KugelRepository kugelRepository;

    private KugelService kugelService;

    @BeforeEach
    void initTest() {
        kugelService = new KugelService(this.kugelRepository);
    }

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
