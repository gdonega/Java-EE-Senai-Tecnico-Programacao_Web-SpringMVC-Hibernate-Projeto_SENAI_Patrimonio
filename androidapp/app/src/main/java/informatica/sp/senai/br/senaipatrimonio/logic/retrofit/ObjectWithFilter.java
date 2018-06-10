package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import io.felipepoliveira.jserializer.json.JfoObject;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ObjectWithFilter<T> {
    private T t;
    private JfoObject filter;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public JfoObject getFilter() {
        return filter;
    }

    public void setFilter(JfoObject filter) {
        this.filter = filter;
    }

    public ObjectWithFilter(T t, JfoObject filter) {
        this.t = t;
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "ObjectWithFilter{" +
                "t=" + t +
                ", filter=" + filter +
                '}';
    }
}
