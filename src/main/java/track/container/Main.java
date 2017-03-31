package track.container;

import track.container.beans.Car;
import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

import java.io.File;
import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        /*

        ПРИМЕР ИСПОЛЬЗОВАНИЯ

         */

//        // При чтении нужно обработать исключение
        ConfigReader reader = new JsonConfigReader();
        List<Bean> beans = null;
        try {
            beans = reader.parseBeans(new File("src/main/resources/config.json"));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Container container = new Container(beans);
        try {
            Car car = (Car) container.getByClass("track.container.beans.Car");
            car = (Car) container.getById("carBean");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }


    }
}
