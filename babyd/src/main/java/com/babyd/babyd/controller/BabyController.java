package com.babyd.babyd.controller;

import com.babyd.babyd.models.Baby;
import com.babyd.babyd.models.BabyFullInfo;
import com.babyd.babyd.services.BabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/baby")
public class BabyController {

    @Autowired
    BabyService babyService;

    @GetMapping("/get_my_babies")
    public ResponseEntity<List<Baby>> fetchAllBabies(HttpServletRequest request)
    {
        UUID parent_id = UUID.fromString((String) request.getAttribute("parent_id"));
        List<Baby> babies = babyService.fetchAllBabies(parent_id);
        return new ResponseEntity<>(babies, HttpStatus.OK);
    }

    @DeleteMapping("/remove_baby")
    public ResponseEntity<Map<String, String>> removeBaby(HttpServletRequest request, @RequestBody UUID baby_id)
    {
        UUID parent_id = UUID.fromString((String) request.getAttribute("parent_id"));
        babyService.removeBaby(parent_id, baby_id);
        Map<String,String> map = new HashMap<>();
        map.put("Remove baby:", baby_id.toString());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/add_baby")
    public ResponseEntity<Map<String, String>> addBaby(HttpServletRequest request,
                                                       @RequestBody Map<String, Object> babyMap)
    {
        UUID parent_id = UUID.fromString((String) request.getAttribute("parent_id"));
        String first_name = (String) babyMap.get("first_name");
        String last_name = (String) babyMap.get("last_name");
        int food_type = (Integer) babyMap.get("food_type");
        String birth_day = (String) babyMap.get("baby_birth_day");
        double weight = Double.parseDouble((String) babyMap.get("weight"));

        Baby baby = babyService.addBaby(parent_id, first_name, last_name, food_type, birth_day, weight);
        Map<String,String> map = new HashMap<>();
        map.put("successfuly add baby", baby.getId().toString());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/get_baby_by_id")
    public ResponseEntity<Baby> findById(HttpServletRequest request,
                                                       @RequestBody Map<String, Object> babyMap)
    {
        UUID parent_id = UUID.fromString((String) request.getAttribute("parent_id"));
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        Baby baby = babyService.fetchBabyById(parent_id, baby_id);
        return new ResponseEntity<>(baby, HttpStatus.OK);
    }

    @GetMapping("/get_baby_full_info_for_today")
    public ResponseEntity<List<BabyFullInfo>> getBabyFullInfo(HttpServletRequest request, @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        String date = (String) babyMap.get("date");
        List<BabyFullInfo> babyFullInfo = babyService.getBabyFullInfoForDate(baby_id, date);
        return new ResponseEntity<>(babyFullInfo, HttpStatus.OK);
    }

    @PostMapping("/set_weight")
    public ResponseEntity<String> setWeight(HttpServletRequest request,
                                            @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        double weight = Double.parseDouble((String) babyMap.get("weight").toString());
        String measure_date = (String) babyMap.get("date");
        babyService.setBabyWeight(baby_id, weight, measure_date);
        return new ResponseEntity<>("Baby weight set successfully", HttpStatus.OK);
    }

    @PostMapping("/set_diaper")
    public ResponseEntity<String> setBabyDipper(HttpServletRequest request, @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        String measure_date = (String) babyMap.get("date");
        String measure_time = (String) babyMap.get("time");
        Boolean wet_diaper = (Boolean) babyMap.get("wet");
        Boolean dirty_diaper = (Boolean) babyMap.get("dirty");

        babyService.setDiaper(baby_id, measure_date, measure_time, wet_diaper, dirty_diaper);
        return new ResponseEntity<>("Dipper set successfully", HttpStatus.OK);
    }

    @PostMapping("/set_formula")
    public ResponseEntity<String> setBabyFormula(HttpServletRequest request, @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        String measure_date = (String) babyMap.get("date");
        String measure_time = (String) babyMap.get("time");
        int amount = Integer.parseInt((String) babyMap.get("amount"));
        String feed_type = (String) babyMap.get("feed_type");

        babyService.setFormula(baby_id, measure_date, measure_time, amount, feed_type);
        return new ResponseEntity<>("Feeding set successfully", HttpStatus.OK);
    }

    @PostMapping("/set_breast")
    public ResponseEntity<String> setBabyBreast(HttpServletRequest request, @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        String measure_date = (String) babyMap.get("date");
        String measure_time = (String) babyMap.get("time");
        String breast_side = (String) babyMap.get("breast_side");
        int breast_feeding_time_length = Integer.parseInt((String) babyMap.get("breast_feeding_time_length"));
        String feed_type = (String) babyMap.get("feed_type");

        babyService.setBreast(baby_id, measure_date, measure_time, breast_side, breast_feeding_time_length, feed_type);
        return new ResponseEntity<>("Dipper set successfully", HttpStatus.OK);
    }


    @PostMapping("/set_sleep")
    public ResponseEntity<String> setBabySleep(HttpServletRequest request, @RequestBody Map<String, Object> babyMap)
    {
        UUID baby_id = UUID.fromString((String) babyMap.get("baby_id"));
        String measure_date = (String) babyMap.get("date");
        String measure_time = (String) babyMap.get("time");
        int sleeping_time = (int) babyMap.get("sleeping_time");

        babyService.setSleepingTime(baby_id, measure_date, measure_time, sleeping_time);
        return new ResponseEntity<>("Dipper set successfully", HttpStatus.OK);
    }
}


