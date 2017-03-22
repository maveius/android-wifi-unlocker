package pl.maveius.wifiunlocker.model;

import java.util.ArrayList;
import java.util.List;

public class Network {

    public String name;
    public final List<String> details = new ArrayList<>();

    public Network(String name) {
        this.name = name;
    }
}
