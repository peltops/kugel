package com.peltops.kugel.service;

import com.peltops.kugel.entity.Kugel;
import com.peltops.kugel.repository.KugelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KugelService {

    private final KugelRepository kugelRepository;

    public Kugel create(Kugel kugel) {
        return kugelRepository.save(kugel);
    }
}
