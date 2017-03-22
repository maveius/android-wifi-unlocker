package pl.maveius.wifiunlocker.utils;

public enum NetworkDataType {
    SSID("ssid="),
    BSSID("bssid="),
    PASSWORD("psk=");

    private String value;

    NetworkDataType(String value) {
        this.value = value;
    }

    public String text(){
        return value;
    }

}
