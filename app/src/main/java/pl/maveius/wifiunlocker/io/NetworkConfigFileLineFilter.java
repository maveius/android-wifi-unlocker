package pl.maveius.wifiunlocker.io;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import pl.maveius.wifiunlocker.model.Network;
import pl.maveius.wifiunlocker.utils.NetworkDataType;

import static pl.maveius.wifiunlocker.utils.NetworkDataType.BSSID;
import static pl.maveius.wifiunlocker.utils.NetworkDataType.PASSWORD;
import static pl.maveius.wifiunlocker.utils.NetworkDataType.SSID;

class NetworkConfigFileLineFilter {

    private SparseArray<Network> networkData = new SparseArray<>();
    private String networkName = "";
    private int position = 0;

    void filter(String line) {

        if (line == null) {
            return;
        }

        if (line.contains(SSID.text()) && !line.contains(BSSID.text())) {
            this.networkName = parseLine(line, SSID);
        }

        if (line.contains(PASSWORD.text()) && !networkName.isEmpty()) {
            String password = parseLine(line, SSID);

            Network network = createNetwork(networkName, password);

            networkData.put(position++, network);
            networkName = "";
        }
    }

    @NonNull
    private Network createNetwork(String networkName, String password) {
        Network network = new Network(networkName);
        network.details.add(password);
        return network;
    }

    private String parseLine(String line, NetworkDataType type) {
        return line.substring(type.text().length() + 1).replace("\"", "");
    }


    SparseArray<Network> getNetworkData() {
        return networkData;
    }

    void put(String networkName, String password) {
        Network network = createNetwork(networkName, password);
        networkData.put(position++, network);
    }
}
