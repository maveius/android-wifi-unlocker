package pl.maveius.wifiunlocker.io;


import android.content.res.Resources;
import android.os.Environment;
import android.util.SparseArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import pl.maveius.wifiunlocker.R;
import pl.maveius.wifiunlocker.model.Network;

public class NetworkConfigFileReader {

    private String networkConfigurationSource;
    private String networkConfiguration;

    private NetworkConfigFileLineFilter fileLineFilter = new NetworkConfigFileLineFilter();


    public NetworkConfigFileReader(Resources resources) {
        this.networkConfigurationSource = resources.getString(R.string.network_config_file);
        this.networkConfiguration = Environment.getExternalStorageDirectory() + "/tmp/.conf";
    }


    public SparseArray<Network> read() {

        copyNetworkConfigurationForRead();
        File networkConfigFile = new File(networkConfiguration);

        return fetchNetworkListFrom(networkConfigFile);
    }


    private void copyNetworkConfigurationForRead() {

        String[] command = new String[] {
                "su","-c","cp", networkConfigurationSource, networkConfiguration
        };

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }



    private SparseArray<Network> fetchNetworkListFrom(File file) {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            do {
                line = bufferedReader.readLine();
                fileLineFilter.filter(line);
            } while (line != null);

            bufferedReader.close();

            return fileLineFilter.getNetworkData();

        } catch (IOException e) {
            fileLineFilter.put("Unexpected exception: "+e.getMessage(), e.getMessage());
            return fileLineFilter.getNetworkData();
        }

    }

}
