import java.util.ArrayList;
import java.util.List;

public abstract class Modelo {
    protected Object data;
    protected String id;

    public Object getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    private final List<Vista> vistas = new ArrayList<>();

    public void addView(Vista vista){
        if (!vistas.contains(vista)) {
            this.vistas.add(vista);
        }
    }

    public void removeView(Vista vista){
        vistas.remove(vista);
    }

    public void updateViews(){
        for (Vista o : vistas) {
            o.update(this);
        }
    }

    public final void serviceNotification(Object data){
        service(data);
        updateViews();
    }

    public abstract void service(Object data);

}
