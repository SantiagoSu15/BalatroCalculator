package com.example.BalatroCalculator.Controller;

import com.example.BalatroCalculator.Service.BalatroService;
import com.example.BalatroCalculator.Service.Request.EvaluarManoRequest;
import com.example.BalatroCalculator.Service.Response.MejorJugadaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balatro")
@CrossOrigin(origins = "*")
public class BalatroController {

    @Autowired
    private BalatroService balatroService;

    @PostMapping("/evaluar-mano")
    public ResponseEntity<MejorJugadaResponse> evaluarMano(@RequestBody EvaluarManoRequest request) {
        try {
            MejorJugadaResponse response = balatroService.evaluarMejorJugada(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
