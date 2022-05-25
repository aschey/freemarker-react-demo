package com.dfs.freemarkerDemo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import com.dfs.freemarkerDemo.model.Car;

@Controller
public class CarsController {
    @GetMapping(value = "/")
	public String init(@ModelAttribute("model") ModelMap model, HttpSession session) {
        var carList = (ArrayList<Car>)session.getAttribute("carList");
        if (carList == null) {
            carList = new ArrayList<>();
            carList.add(new Car("Honda", "Civic"));
            carList.add(new Car("Toyota", "Camry"));
            carList.add(new Car("Nissan", "Altima"));
            session.setAttribute("carList", carList);
        }
		model.addAttribute("carList", carList);
		return "index";
	}

    @PostMapping(value = "/add")
    public String addCar(@ModelAttribute("car") Car car,  HttpSession session) {
        if (null != car && null != car.getMake() && null != car.getModel() && !car.getMake().isEmpty() && !car.getModel().isEmpty()) {
            var carList = (ArrayList<Car>)session.getAttribute("carList");
            if (carList == null) {
                carList = new ArrayList<>();
            }
            carList.add(car);
            session.setAttribute("carList", carList);
        }
        return "redirect:/";
    }
}
