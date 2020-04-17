package ua.nure.huzhyn.web.controller;

import org.apache.log4j.Logger;
import ua.nure.huzhyn.exception.IncorrectDataException;
import ua.nure.huzhyn.model.entity.Car;
import ua.nure.huzhyn.model.entity.Train;
import ua.nure.huzhyn.model.entity.enums.CarType;
import ua.nure.huzhyn.services.CarService;
import ua.nure.huzhyn.services.RoutService;
import ua.nure.huzhyn.services.TrainService;
import ua.nure.huzhyn.util.constants.AppContextConstant;
import ua.nure.huzhyn.validator.CarValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@WebServlet("/administrator_add_car")
public class AdministratorAddCarController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdministratorAddCarController.class);
    private RoutService routService;
    private CarService carService;
    private TrainService trainService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarValidator carValidator = new CarValidator();
        Car car = new Car();
        try {

            car.setCarType(CarType.valueOf(request.getParameter("car_type")));
            car.setCarNumber(request.getParameter("car_number"));
            car.setPrice(new BigDecimal(request.getParameter("price")));
            car.setSeats(Integer.valueOf(request.getParameter("seats")));
            String trainId = request.getParameter("train_id");
            car.setTrainId(trainId.equals("TRAIN_NOT_SELECTED") ? null : trainId);
            carValidator.isValidRout(car);
            carService.addCar(car);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Incorrect data entered");
            throw new IncorrectDataException("Incorrect data entered", e);
        }
        response.sendRedirect("administrator_account");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Train> trainList = trainService.getAllTrainList();
        request.setAttribute("trainList", trainList);
        List<CarType> carTypeList = new ArrayList<>(EnumSet.allOf(CarType.class));
        request.setAttribute("carTypeList", carTypeList);

        request.getRequestDispatcher("WEB-INF/jsp/administratorAddCar.jsp").forward(request, response);
    }

    public void init(ServletConfig config) {
        routService = (RoutService) config.getServletContext().getAttribute(AppContextConstant.ROUT_SERVICE);
        trainService = (TrainService) config.getServletContext().getAttribute(AppContextConstant.TRAIN_SERVICE);
        carService = (CarService) config.getServletContext().getAttribute(AppContextConstant.CARS_SERVICE);
    }
}
