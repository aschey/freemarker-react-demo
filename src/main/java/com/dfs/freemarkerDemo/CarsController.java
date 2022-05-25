package com.dfs.freemarkerDemo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import com.dfs.freemarkerDemo.model.Car;

@Controller
public class CarsController {
    private static List<Car> carList = new ArrayList<Car>();

    static {
        carList.add(new Car("Honda", "Civic"));
        carList.add(new Car("Toyota", "Camry"));
        carList.add(new Car("Nissan", "Altima"));
    }
    
    @GetMapping(value = "/")
	public String init(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("carList", carList);
		return "index";
	}

    @PostMapping(value = "/add")
    public String addCar(@ModelAttribute("car") Car car) {
        if (null != car && null != car.getMake() && null != car.getModel() && !car.getMake().isEmpty() && !car.getModel().isEmpty()) {
            carList.add(car);
        }
        return "redirect:/";
    }
}
