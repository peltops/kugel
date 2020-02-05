package com.peltops.kugel.controller;

import com.peltops.kugel.dto.Version;
import com.peltops.kugel.entity.Kugel;
import com.peltops.kugel.service.KugelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class KugelRestController {

    private final KugelService kugelService;

    @GetMapping("/api/version")
    public ResponseEntity<Version> getVersion() {
        Version version = new Version();
        version.setIdentifier("0.0.1");
        version.setName("babybang");
        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @PostMapping("/api/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public com.peltops.kugel.dto.KugelDto create(@Valid @RequestBody com.peltops.kugel.dto.KugelDto kugelDTO) {
        Kugel kugel = new Kugel();
        kugel.setColor(kugelDTO.getColor());
        kugel.setDiameter(kugelDTO.getDiameter());
        kugel = kugelService.create(kugel);
        kugelDTO.setId(kugel.getId());
        return kugelDTO;
    }
}
