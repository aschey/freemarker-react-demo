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
        var numRequests = (Integer)session.getAttribute("numRequests");
        if (carList == null) {
            carList = new ArrayList<>();
            numRequests = 0;
            carList.add(new Car("Honda", "Civic"));
            carList.add(new Car("Toyota", "Camry"));
            carList.add(new Car("Nissan", "Altima"));
            session.setAttribute("carList", carList);
            session.setAttribute("numRequests", numRequests);
        }
		model.addAttribute("carList", carList);
        model.addAttribute("numRequests", numRequests);
		return "index";
	}

    @PostMapping(value = "/add")
    public String addCar(@ModelAttribute("car") Car car,  HttpSession session) {
        if (null != car && null != car.getMake() && null != car.getModel() && !car.getMake().isEmpty() && !car.getModel().isEmpty()) {
            var carList = (ArrayList<Car>)session.getAttribute("carList");
            var numRequests = (Integer)session.getAttribute("numRequests");
            if (carList == null) {
                carList = new ArrayList<>();
                numRequests = 0;
            }
            carList.add(car);
            numRequests++;
            session.setAttribute("carList", carList);
            session.setAttribute("numRequests", numRequests);
        }
        return "redirect:/";
    }
}
