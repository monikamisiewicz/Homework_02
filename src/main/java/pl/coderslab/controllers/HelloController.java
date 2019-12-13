package pl.coderslab.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    public static final Random RANDOM = new Random();

    //4.2 W kontrolerze HelloController utwórz akcję workersAction, dostępną pod adresem /workers,
    //która wyświetli widok o nazwie workers.jsp.

    @RequestMapping("/workers/{randomWorker}")
    public String workersAction(@PathVariable String randomWorker, Model model) {

        int randomNum = getRandom();
        logger.info("random number = " + randomNum);

        randomWorker = findWorker(randomNum);
        logger.info("random worker " + randomWorker);

        model.addAttribute("randomWorker", randomWorker);
        return "workers";
    }

    //wylosuje liczbę od 1 do 30.
    private int getRandom() {
        return RANDOM.nextInt(30) + 1;
    }

    //Otworzy plik Workers.txt
    private List<String> getList() {

        System.out.println("lista wczytana z pliku csv: ");
        Path path = Paths.get("/Users/monikamisiewicz/Desktop/code/repositories/advanced/WRO_JEE_W_06_Advanced/Workers.txt");
        List<String> workerList = new ArrayList<>();

        try {
            workerList = Files.readAllLines(path);
            for (String content : workerList) {
                logger.info(content);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return workerList;
    }

    // znajdzie w nim osobę odpowiadającą wylosowanej liczbie
    private String findWorker(int randomNumber) {

        String formatted;
        if (randomNumber < 10) {
            formatted = String.format("%02d", randomNumber);
            logger.info("with leading zeros: " + formatted);
        } else {
            formatted = String.valueOf(randomNumber);
        }

        List<String> list = getList();


        List<String> newList = list.stream()
                .filter(w -> w.contains(formatted))
                .flatMap(v -> {
                    String[] split = v.split(",");
                    return Arrays.stream(split);
                })
                .collect(Collectors.toList());
        logger.info("new worker list " + newList);


//        String worker = newList.stream()
//                .filter(s -> s.startsWith("Jan"))
//                .findAny()
//                .get();

        String worker = null;
        if(newList.size() == 0) {
            System.out.println("Not found");
        } else {
            worker = list.get(1);
        }

        return worker;
    }


}
