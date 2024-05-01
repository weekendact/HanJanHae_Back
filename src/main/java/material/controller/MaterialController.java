package material.controller;

import material.service.MaterialVERBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("material")
public class MaterialController {

    private final MaterialVERBService materialVERBService;

    public MaterialController(MaterialVERBService materialVERBService) {
        this.materialVERBService = materialVERBService;
    }
}
