package informatica.sp.senai.br.senaipatrimonio.util.jserializer;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public enum FilerType {

    REQUIRE("require")
    ,EXCLUDE("exclude");

    private String type;

    FilerType(String require) {
        this.type = require;
    }

    public String getTypeName() {
        return type;
    }
}
